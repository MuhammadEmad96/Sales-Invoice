
package Project1.View;

import java.awt.GridLayout;
import javax.swing.*;


public class FileSelector1 extends JDialog  {
    private JTextField insertCustomerName;
    private JTextField insertDate;
    private JLabel customerName;
    private JLabel Date;
    private JButton save;
    private JButton cancel;
     public FileSelector1(Layout layout) {
        customerName = new JLabel("Customer Name:");
        insertCustomerName = new JTextField(20);
        Date = new JLabel("Invoice Date:");
        insertDate = new JTextField(20);
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        
        save.setActionCommand("saveInvoice");
        cancel.setActionCommand("cancelInvoice");
        
        save.addActionListener(layout.getActionHandler());
        cancel.addActionListener(layout.getActionHandler());
        
        GridLayout CreateNewInvoice = new GridLayout(3,2);
        setLayout(CreateNewInvoice);        
        
        add(customerName);
        add(insertCustomerName);
        add(Date);
        add(insertDate);
        add(save);
        add(cancel);
        pack();
    }

    public JTextField getCustomerName() {
        return insertCustomerName;
    }

    public JTextField getDate() {
        return insertDate;
    }


    
}

