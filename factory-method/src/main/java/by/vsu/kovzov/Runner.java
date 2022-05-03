package by.vsu.kovzov;

import by.vsu.kovzov.factory.*;
import by.vsu.kovzov.io.PersonXmlReader;
import by.vsu.kovzov.models.Person;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Runner {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
        JFrame window = new JFrame("Main window");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 400);
        List<Person> persons = PersonXmlReader.readFromFile(Runner.class.getClassLoader().getResource("persons.xml").getFile());
        PersonTableModel model = new PersonTableModel(persons);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        window.add(scrollPane, BorderLayout.CENTER);
        JPanel chooser = new JPanel(new BorderLayout());
        JComboBox<ComparatorFactory<Person>> comboBox = new JComboBox<>();
        comboBox.addItem(new DateComparatorFactory());
        comboBox.addItem(new WeekDayComparatorFactory());
        comboBox.addItem(new MonthComparatorFactory());
        comboBox.addItem(new CreditCardComparatorFactory());
        chooser.add(comboBox, BorderLayout.CENTER);
        JButton sortButton = new JButton("sort");
        sortButton.addActionListener(new SortButtonListener(comboBox, model));
        chooser.add(sortButton, BorderLayout.EAST);
        window.add(chooser, BorderLayout.NORTH);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}