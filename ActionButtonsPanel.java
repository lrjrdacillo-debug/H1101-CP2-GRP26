import javax.swing.*;
import java.awt.*;

public class ActionButtonsPanel extends JPanel {
    
    public ActionButtonsPanel() {
        // We use FlowLayout to put them side-by-side, just like the original file
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); 
        setBackground(new Color(246, 248, 250)); // Matches the Employee Form background

        // The buttons created by Precious
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        // Add buttons to this panel
        add(addBtn); 
        add(updateBtn);
        add(deleteBtn);
        add(clearBtn);
    }
}