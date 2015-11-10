package cellularautomaton.view.gui.basicview.components;

import cellularautomaton.controller.locale.StringEnumeration;
import cellularautomaton.view.util.IOwnEnumeration;

import javax.swing.*;

/**
 * Created by vspadi on 09.11.15.
 */
public class CAJButton extends JButton implements IOwnEnumeration{
    private StringEnumeration type;

    public CAJButton(StringEnumeration text) {
        super();
        this.type = text;
        this.setText(text.toString());
        //setBorder(null);
    }

    @Override
    public StringEnumeration getEnumeration() {
        return type;
    }
}


/*

 GameOfLifeAutomaton gol = new GameOfLifeAutomaton(100,100,true);

        gol.randomPopulation();
        this.setColorMapping(gol.getColorMapping());

        this.drawPopulation(gol.getPopulation());
        this.drawPopulation(gol.getPopulation());

 */