package by.vsu.kovzov;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JComboBox;

public class SortButtonListener implements ActionListener {
    private JComboBox<String> comboBox;
    private PersonTableModel model;

    public SortButtonListener(JComboBox<String> comboBox, PersonTableModel model) {
        this.comboBox = comboBox;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Collections.sort(model.getPersons(), new PersonComparator((String)comboBox.getSelectedItem()));
        model.update();
    }
}
