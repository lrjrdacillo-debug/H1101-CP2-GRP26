import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeFormPanel extends JPanel {

    // 1. Declare the fields at the class level so your other files can read the data later
    private JTextField employeeIdField;
    private JTextField fullNameField;
    private JTextField positionField;
    private JTextField salaryField;
    private JButton saveButton;

    public EmployeeFormPanel() {
        // Match the HTML body background color (#f6f8fa)
        setBackground(new Color(246, 248, 250)); 
        setLayout(new GridBagLayout()); // Centers the form container on the screen

        // 2. Create the main form container (matches .form-container in HTML)
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 218), 1, true), // border-color
            BorderFactory.createEmptyBorder(24, 24, 24, 24) // padding: 24px
        ));
        
        // 3. Title (matches <h2>Employee Information</h2>)
        JLabel titleLabel = new JLabel("Employee Information");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(new Color(36, 41, 46)); // --primary-color
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formContainer.add(titleLabel);
        formContainer.add(Box.createRigidArea(new Dimension(0, 16))); // Spacing

        // 4. Employee ID Field
        formContainer.add(createLabel("Employee ID"));
        employeeIdField = new JTextField();
        employeeIdField.setToolTipText("e.g., EMP-1042");
        formContainer.add(styleTextField(employeeIdField));
        formContainer.add(Box.createRigidArea(new Dimension(0, 16))); // margin-bottom: 16px

        // 5. Full Name Field
        formContainer.add(createLabel("Full Name"));
        fullNameField = new JTextField();
        fullNameField.setToolTipText("e.g., Jane Doe");
        formContainer.add(styleTextField(fullNameField));
        formContainer.add(Box.createRigidArea(new Dimension(0, 16)));

        // 6. Position Field
        formContainer.add(createLabel("Position"));
        positionField = new JTextField();
        positionField.setToolTipText("e.g., Software Engineer");
        formContainer.add(styleTextField(positionField));
        formContainer.add(Box.createRigidArea(new Dimension(0, 16)));

        // 7. Salary Field
        formContainer.add(createLabel("Monthly Salary (₱)"));
        salaryField = new JTextField();
        salaryField.setToolTipText("e.g., 5000.00");
        formContainer.add(styleTextField(salaryField));
        formContainer.add(Box.createRigidArea(new Dimension(0, 24)));

        // 8. Save Button (matches <button type="submit">)
        saveButton = new JButton("Save Employee");
        saveButton.setBackground(new Color(44, 151, 75)); // #2c974b
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        saveButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        saveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35)); // width: 100%
        
        // Add the action listener for event-driven programming
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });
        formContainer.add(saveButton);

        // Add the completed form container to the main panel
        add(formContainer);
    }

    // --- Helper Methods to keep code clean and match CSS styling ---

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(36, 41, 46));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField styleTextField(JTextField textField) {
        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        return textField;
    }

    // --- Action Handler ---

    private void handleSaveAction() {
        // This validates the inputs just like the "required" attribute in your HTML
        if (employeeIdField.getText().trim().isEmpty() || 
            fullNameField.getText().trim().isEmpty() || 
            positionField.getText().trim().isEmpty() || 
            salaryField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, 
                "Please fill in all required fields.", 
                "Validation Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // This prints to the console to prove the button works
        System.out.println("Saving: " + fullNameField.getText());
        JOptionPane.showMessageDialog(this, "Employee Saved Successfully!");
    }
}