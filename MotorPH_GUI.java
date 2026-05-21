import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorPH_GUI extends JFrame {

    public MotorPH_GUI() {
        // 1. Setup the Window
        setTitle("MotorPH Employee Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // Simple layout for starters

        // 2. Create Components
        JLabel label = new JLabel("Enter Employee ID:");
        JTextField textField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        // 3. Add Event Handling (The "Action")
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                // This is where you would call your logic from Homework 1
                JOptionPane.showMessageDialog(null, "Searching for Employee ID: " + input);
            }
        });

        // 4. Add components to the frame
        add(label);
        add(textField);
        add(searchButton);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MotorPH_GUI().setVisible(true);
        });
    }
}


