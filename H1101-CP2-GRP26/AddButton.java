import javax.swing.*;

public class AddButton {                        
    public static void main(String[] args) {      
        JFrame frame = new JFrame("Welcome");     
       frame.setLayout(new java.awt.FlowLayout()); 

        //
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        frame.add(addBtn); 
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(clearBtn);

        frame.setSize(400, 300);                 
        frame.setLayout(new java.awt.FlowLayout());
        frame.setVisible(true); 
                          
    }
}
