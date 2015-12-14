package cellularautomaton.controller;

import cellularautomaton.controller.locale.StringController;

import javax.tools.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by vspadi on 14.12.15.
 */
public class AutomatonCompiler implements DiagnosticListener{
    private static AutomatonCompiler instance;

    private JavaCompiler compiler;
    private StandardJavaFileManager fileManager;

    static {
        instance = new AutomatonCompiler();
    }
    public static AutomatonCompiler getInstance() {
        return instance;
    }

    private AutomatonCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = this.compiler.getStandardFileManager(this, StringController.getLocale(),null);
    }

    public void compile(File file) {
        Iterable<? extends JavaFileObject> units = this.fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file));
        JavaCompiler.CompilationTask task = this.compiler.getTask(null, this.fileManager,this,null,null, units);
        boolean success = task.call();

        if(success) {
            System.out.println("Compiling done");
        }
    }


    @Override
    public void report(Diagnostic diagnostic) {
        System.out.println(diagnostic);
    }
}
