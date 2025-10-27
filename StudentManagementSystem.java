import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    String rollNo;

    Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String toString() {
        return "Name: " + name + ", Roll No: " + rollNo;
    }
}

public class StudentManagementSystem extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();

    private JTextField nameField, rollField, searchField;
    private JTextArea displayArea;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(30, 30, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(140, 30, 200, 25);
        add(nameField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(30, 70, 100, 25);
        add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(140, 70, 200, 25);
        add(rollField);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(360, 50, 130, 30);
        add(addButton);

        JButton viewButton = new JButton("View All");
        viewButton.setBounds(50, 120, 100, 30);
        add(viewButton);

        JLabel searchLabel = new JLabel("Search Name:");
        searchLabel.setBounds(180, 120, 100, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(280, 125, 120, 25);
        add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(420, 120, 80, 30);
        add(searchButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 170, 470, 250);
        add(scrollPane);

        // Add Button Action
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            if (!name.isEmpty() && !roll.isEmpty()) {
                students.add(new Student(name, roll));
                displayArea.setText("✅ Student added: " + name);
                nameField.setText("");
                rollField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View Button Action
        viewButton.addActionListener(e -> {
            if (students.isEmpty()) {
                displayArea.setText("📭 No students found!");
            } else {
                StringBuilder sb = new StringBuilder("📋 Student List:\n");
                for (Student s : students) {
                    sb.append(s).append("\n");
                }
                displayArea.setText(sb.toString());
            }
        });

        // Search Button Action
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter a name to search!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            StringBuilder sb = new StringBuilder("🔍 Search Results:\n");
            for (Student s : students) {
                if (s.name.toLowerCase().contains(keyword.toLowerCase())) {
                    sb.append(s).append("\n");
                }
            }
            displayArea.setText(sb.length() == 18 ? "No student found with that name!" : sb.toString());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem().setVisible(true);
        });
    }
}

