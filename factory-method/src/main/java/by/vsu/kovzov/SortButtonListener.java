package by.vsu.kovzov;

import by.vsu.kovzov.factory.ComparatorFactory;
import by.vsu.kovzov.models.Person;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JComboBox;

public class SortButtonListener implements ActionListener {
    private JComboBox<ComparatorFactory<Person>> comboBox;
    private PersonTableModel model;

    public SortButtonListener(JComboBox<ComparatorFactory<Person>> comboBox, PersonTableModel model) {
        this.comboBox = comboBox;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ComparatorFactory<Person> factory = (ComparatorFactory<Person>) comboBox.getSelectedItem();
        Collections.sort(model.getPersons(), factory.getComparator());
        model.update();
    }
}
