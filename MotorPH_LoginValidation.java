import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorPH_GUI extends JFrame {

    // --- Phase 1: Structural Setup ---
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);

    public MotorPH_GUI() {
        // 1. Setup the Window
        setTitle("MotorPH Employee Management System");
        setSize(800, 600); // Slightly taller to fit all teammate fields
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
        JPanel loginBox = new JPanel(new GridLayout(5, 2, 10, 10));

        // Basic Login Components
        loginBox.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        loginBox.add(usernameField);

        loginBox.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(15);
        loginBox.add(passwordField);

        // --- Show Password Checkbox ---
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

        loginBox.add(new JLabel("")); // Empty label for spacing before button
        JButton loginBtn = new JButton("Login");
        loginBox.add(loginBtn);

        // Event Handling: Validate and Switch to Dashboard
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // Proper way to get password

                try {
                    performLogin(username, password);
                    cardLayout.show(mainContainer, "DASHBOARD_SCREEN");
                } catch (IllegalArgumentException ex) {
                    showMessage(panel, ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                } catch (AuthenticationException ex) {
                    showMessage(panel, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    showMessage(panel, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        panel.add(loginBox);
        return panel;
    }

    // --- Phase 3: Main Navigation Frame & App Structure ---
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // --- Top Header ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(230, 230, 230));
        JLabel headerLabel = new JLabel(" MotorPH Employee App", JLabel.LEFT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // The "Logout" Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            // Switch back to Login
            cardLayout.show(mainContainer, "LOGIN_SCREEN");
        });

        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(logoutBtn, BorderLayout.EAST); // Puts it in the top right corner
        panel.add(headerPanel, BorderLayout.NORTH);

        // --- THE STRUCTURED GRID (FOR PRINCESS & PRECIOUS) ---
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adds spacing between components
        gbc.anchor = GridBagConstraints.WEST; // Aligns components to the left

        // Row 0: Search (Your Part)
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Employee ID:"), gbc);
        
        gbc.gridx = 1;
        JTextField searchField = new JTextField(15);
        centerPanel.add(searchField, gbc);
        
        gbc.gridx = 2;
        JButton searchButton = new JButton("Search");
        centerPanel.add(searchButton, gbc);

        searchButton.addActionListener(e -> {
            try {
                validateSearchInput(searchField.getText());
                JOptionPane.showMessageDialog(panel, "Searching for Employee ID: " + searchField.getText(), "Search", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                showMessage(panel, ex.getMessage(), "Search Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Row 1 & 2: PLACEHOLDERS FOR PRINCESS (Employee Form UI)
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("First Name: [Cp2test]"), gbc);
        
        gbc.gridy = 2;
        centerPanel.add(new JLabel("Last Name: [Cp2test]"), gbc);

        // Row 3: PLACEHOLDER FOR PRECIOUS (Buttons)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        centerPanel.add(new JLabel("Actions: (Add/Update/Delete) [Cp2test]"), gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // --- Bottom Footer ---
        JLabel statusArea = new JLabel(" Status: Logged In | System Ready", JLabel.LEFT);
        statusArea.setBorder(BorderFactory.createEtchedBorder());
        panel.add(statusArea, BorderLayout.SOUTH);

        return panel;
    }

    private void performLogin(String username, String password) throws AuthenticationException {
        validateLoginInput(username, password);
        authenticate(username, password);
    }

    private void validateLoginInput(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }

        if (username.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters.");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }
    }

    private void authenticate(String username, String password) throws AuthenticationException {
        if (!"admin".equals(username.trim()) || !"motorph".equals(password)) {
            throw new AuthenticationException("Invalid username or password.");
        }
    }

    private void validateSearchInput(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID is required for search.");
        }

        if (!employeeId.trim().matches("^[A-Za-z0-9_-]+$")) {
            throw new IllegalArgumentException("Employee ID may only contain letters, digits, underscore, or dash.");
        }
    }

    private void showMessage(Component parent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MotorPH_GUI().setVisible(true);
        });
    }

    private static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }
}
