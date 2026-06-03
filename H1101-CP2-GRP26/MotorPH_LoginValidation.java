import javax.swing.*;
import java.awt.*;
public class MotorPH_LoginValidation extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);

    public MotorPH_LoginValidation() {
        setTitle("MotorPH Employee Management System");
        setSize(900, 750); // Made slightly larger to fit the new custom panels perfectly
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = createLoginPanel();
        JPanel dashboardPanel = createDashboardPanel();

        mainContainer.add(loginPanel, "LOGIN_SCREEN");
        mainContainer.add(dashboardPanel, "DASHBOARD_SCREEN");

        add(mainContainer);
        cardLayout.show(mainContainer, "LOGIN_SCREEN"); 
    }

    // --- Login Panel (Kept exactly as your original code) ---
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout()); 
        JPanel loginBox = new JPanel(new GridLayout(5, 2, 10, 10));

        loginBox.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(15);
        loginBox.add(usernameField);

        loginBox.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(15);
        loginBox.add(passwordField);

        loginBox.add(new JLabel("")); 
        JCheckBox showPassword = new JCheckBox("Show Password");
        loginBox.add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); 
            } else {
                passwordField.setEchoChar('\u2022'); 
            }
        });

        loginBox.add(new JLabel("")); 
        JButton loginBtn = new JButton("Login");
        loginBox.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); 

            try {
                performLogin(username, password);
                cardLayout.show(mainContainer, "DASHBOARD_SCREEN");
            } catch (IllegalArgumentException ex) {
                showMessage(panel, ex.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            } catch (AuthenticationException ex) {
                showMessage(panel, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                showMessage(panel, "Unexpected error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(loginBox);
        return panel;
    }

    // --- Dashboard Panel (Updated to Connect Teammates' Files) ---
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(230, 230, 230));
        JLabel headerLabel = new JLabel(" MotorPH Employee App", JLabel.LEFT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> cardLayout.show(mainContainer, "LOGIN_SCREEN"));

        headerPanel.add(headerLabel, BorderLayout.CENTER);
        headerPanel.add(logoutBtn, BorderLayout.EAST); 
        panel.add(headerPanel, BorderLayout.NORTH);

        // --- THE INTEGRATED GRID ---
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Search Feature
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Employee ID:"));
        JTextField searchField = new JTextField(15);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        searchButton.addActionListener(e -> {
            try {
                validateSearchInput(searchField.getText());
                JOptionPane.showMessageDialog(panel, "Searching for Employee ID: " + searchField.getText(), "Search", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                showMessage(panel, ex.getMessage(), "Search Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        centerPanel.add(searchPanel, gbc);

        // Row 1: INTEGRATION - Princess's Employee Form UI
        gbc.gridy = 1; 
        centerPanel.add(new EmployeeFormPanel(), gbc);

        // Row 2: INTEGRATION - Precious's Action Buttons
        gbc.gridy = 2;
        centerPanel.add(new ActionButtonsPanel(), gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        // Footer
        JLabel statusArea = new JLabel(" Status: Logged In | System Ready", JLabel.LEFT);
        statusArea.setBorder(BorderFactory.createEtchedBorder());
        panel.add(statusArea, BorderLayout.SOUTH);

        return panel;
    }

    // --- Validation Methods (Kept exactly as your original code) ---
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
        if (!"Tester1".equals(username.trim()) || !"CPH1101".equals(password)) {
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
        SwingUtilities.invokeLater(() -> {
            new MotorPH_LoginValidation().setVisible(true);
        });
    }

    private static class AuthenticationException extends Exception {
        public AuthenticationException(String message) {
            super(message);
        }
    }
}
