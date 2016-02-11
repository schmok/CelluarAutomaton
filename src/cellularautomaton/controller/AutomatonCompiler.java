package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringController;
import cellularautomaton.view.util.FileHelper;

import javax.tools.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vspadi on 14.12.15.
 */
public class AutomatonCompiler implements DiagnosticListener{
    private static AutomatonCompiler instance;

    private JavaCompiler compiler;
    private StandardJavaFileManager fileManager;
    private ArrayList<Diagnostic> diagnostics;
    private String lastClassName = "";

    public class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        public JavaSourceFromString( String name, String code) {
            super( URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension),Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    static {
        instance = new AutomatonCompiler();
    }
    public static AutomatonCompiler getInstance() {
        return instance;
    }

    private AutomatonCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = this.compiler.getStandardFileManager(this, StringController.getLocale(),null);
        this.diagnostics = new ArrayList<>();
    }

    public boolean compile(File file) {
        Iterable<? extends JavaFileObject> units = this.fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        return runTask(units, null);
    }

    public boolean compile(String test, File dest) {
        String prefix = "public class ";
        int startpos = test.indexOf("public class ");
        int endpos = test.indexOf(" extends");
        String className = test.substring(startpos+prefix.length(), endpos);
        lastClassName = className;
        JavaSourceFromString jsfs = new JavaSourceFromString(className, test);
        Iterable<? extends  JavaFileObject> units = Arrays.asList(jsfs);

        List<String> optionList = new ArrayList<String>();
        optionList.add("-d");
        optionList.add(dest.getParent());
        return runTask(units, optionList);
    }

    private boolean runTask(Iterable<? extends JavaFileObject> units, List<String> optionList) {
        this.diagnostics.clear();
        JavaCompiler.CompilationTask task = this.compiler.getTask(null, this.fileManager,this,optionList,null, units);

        if(task.call()) {
            System.out.println("Compile done");
            return true;
        }
        return false;
    }

    public ArrayList<Diagnostic> getLastDiagnostics() {
        return this.diagnostics;
    }

    public String getLastClassName() {
        return this.lastClassName;
    }

    @Override
    public void report(Diagnostic diagnostic) {
        this.diagnostics.add(diagnostic);
    }
}
