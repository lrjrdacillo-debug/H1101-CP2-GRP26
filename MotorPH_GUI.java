import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorPH_GUI extends JFrame {

    // --- Phase 1: Structural Setup ---
    // These layout managers allow us to switch between the Login and Dashboard screens.
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);

    public MotorPH_GUI() {
        // 1. Setup the Window
        setTitle("MotorPH Employee Management System");
        setSize(800, 500); // Increased size for the wireframe layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Initialize the screens
        JPanel loginPanel = createLoginPanel();
        JPanel dashboardPanel = createDashboardPanel();

        // 3. Add screens to the main container
        mainContainer.add(loginPanel, "LOGIN_SCREEN");
        mainContainer.add(dashboardPanel, "DASHBOARD_SCREEN");

        // 4. Add components to the frame and set default view
        add(mainContainer);
        cardLayout.show(mainContainer, "LOGIN_SCREEN"); // Start at the login screen
    }

    // --- Phase 2: Login / Entry UI ---
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); // Centers the login box
        JPanel loginBox = new JPanel(new GridLayout(4, 2, 10, 10));

        // Basic Login Components
        loginBox.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        loginBox.add(usernameField);

        loginBox.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(15);
        loginBox.add(passwordField);

        // --- New Show Password Checkbox ---
        loginBox.add(new JLabel("")); // Empty label for spacing
        JCheckBox showPassword = new JCheckBox("Show Password");
        loginBox.add(showPassword);

        // Listener to toggle the password visibility
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Reveal text
                } else {
                    passwordField.setEchoChar('\u2022'); // Hide with bullets
                }
            }
        });

        JButton loginBtn = new JButton("Login");
        loginBox.add(new JLabel("")); // Empty label for spacing before button
        loginBox.add(loginBtn);

        // Event Handling: Validate and Switch to Dashboard
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // Proper way to get password

                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    // Show error if fields are blank
                    JOptionPane.showMessageDialog(panel, "Please enter both Username and Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // In a real app, you'd verify the actual credentials here.
                    // For now, allow access if fields are not empty.
                    cardLayout.show(mainContainer, "DASHBOARD_SCREEN");
                }
            }
        });

        panel.add(loginBox);
        return panel;
    }

private JPanel createDashboardPanel() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    
    // --- Top Header ---
    JPanel headerPanel = new JPanel(new BorderLayout());
    JLabel headerLabel = new JLabel("MotorPH Employee App", JLabel.CENTER);
    headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
    
    // The "Logout" Button
    JButton logoutBtn = new JButton("Logout");
    logoutBtn.addActionListener(e -> {
        // Switch back to Login
        cardLayout.show(mainContainer, "LOGIN_SCREEN");
    });

    headerPanel.add(headerLabel, BorderLayout.CENTER);
    headerPanel.add(logoutBtn, BorderLayout.EAST); // Puts it in the top right corner
    panel.add(headerPanel, BorderLayout.NORTH);

    // --- Center Area (Princess & Precious will work here) ---
    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    // Your existing Search components
    JLabel label = new JLabel("Enter Employee ID:");
    JTextField textField = new JTextField(15);
    JButton searchButton = new JButton("Search");

    searchButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Searching for Employee ID: " + textField.getText());
    });

    centerPanel.add(label);
    centerPanel.add(textField);
    centerPanel.add(searchButton);
    
    panel.add(centerPanel, BorderLayout.CENTER);

    // --- Bottom Footer ---
    JLabel statusArea = new JLabel(" Status: Logged In", JLabel.LEFT);
    statusArea.setBorder(BorderFactory.createEtchedBorder());
    panel.add(statusArea, BorderLayout.SOUTH);

    return panel;
}
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MotorPH_GUI().setVisible(true);
        });
    }
}
