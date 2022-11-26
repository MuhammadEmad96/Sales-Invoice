
package Project1.Controller;

import Project1.View.Layout;
import Project1.Model.InvoiceHeader;
import Project1.Model.InvoiceLine;
import Project1.Model.InvoiceLineTable;
import Project1.Model.InvoiceTable;
import Project1.View.FileSelector1;
import Project1.View.FileSelector2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class Actions implements ActionListener , ListSelectionListener {
    private String command;
    private Layout layout;
    private FileSelector1 dialog ;
    private FileSelector2 dialog2 ;
    public Actions(Layout design){
    this.layout=design;
    }
        @Override
    public void valueChanged(ListSelectionEvent e) {
        int  selectedInvoice = layout.getInvoiceTable().getSelectedRow() ;
        if(selectedInvoice ==-1){}
            else{
                InvoiceHeader choosenInvoice = layout.getObjects().get(selectedInvoice);
                layout.getInvoiceNumValue().setText(""+ choosenInvoice.getInvoiceNum());
                layout.getInvoiceDate().setText(choosenInvoice.getDate());
                layout.getCustomerName().setText(choosenInvoice.getCustomerName());
                layout.getInvoiceTotalValue().setText("" + choosenInvoice.getTotal());
                InvoiceLineTable lineTable = new InvoiceLineTable(choosenInvoice.getAddedlines());
                layout.getInvoiceItem().setModel(lineTable);
                lineTable.fireTableDataChanged();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    command=e.getActionCommand();
        if(command=="Load File"){
        JFileChooser load=new JFileChooser();
        try{
        int buttonChooser=load.showOpenDialog(layout);
        ArrayList <InvoiceHeader> newCustomer = new ArrayList<>();
            if(buttonChooser==JFileChooser.APPROVE_OPTION){
            File choosenHeaderFile=load.getSelectedFile();
            Path ChoosenHeaderFile=Paths.get(choosenHeaderFile.getAbsolutePath());
            List<String> files=Files.readAllLines(ChoosenHeaderFile);
            try{
            files.stream().map((s) -> s.split(",")).map((invoiceData) -> new InvoiceHeader (Integer.parseInt(invoiceData[0]),
                    invoiceData[1],invoiceData[2])).forEachOrdered((invoice) -> {
                        newCustomer.add(invoice);
                        
            });
            }catch(Exception a){
            JOptionPane.showMessageDialog(layout, "wrong File Format", "Invalid File",
                    JOptionPane.ERROR_MESSAGE);
            }
            }
            buttonChooser=load.showOpenDialog(layout);
            if(buttonChooser==JFileChooser.APPROVE_OPTION){
            File choosenLineFile=load.getSelectedFile();
            Path ChoosenLineFile=Paths.get(choosenLineFile.getAbsolutePath());
            List<String> lines=Files.readAllLines(ChoosenLineFile);
            for(String s:lines){
                try{
                String [] invoiceData= s.split(",");
                InvoiceHeader connectedInvoice=null;
                for(InvoiceHeader invoice:newCustomer){
                    if(invoice.getInvoiceNum()== Integer.parseInt(invoiceData[0])){
                        connectedInvoice=invoice;
                        break;
                }
                }
                InvoiceLine invoiceLine=new InvoiceLine (Integer.parseInt(invoiceData[0]),
                        invoiceData[1],Double.parseDouble(invoiceData[2]),Integer.parseInt(invoiceData[3])
                ,connectedInvoice);
               connectedInvoice.getAddedlines().add(invoiceLine);
                }catch(Exception a){
                                JOptionPane.showMessageDialog(layout, "wrong File Format", "Invalid File",
                    JOptionPane.ERROR_MESSAGE);
                                }}
            }
        layout.setObjects(newCustomer);
        InvoiceTable tableModel = new InvoiceTable(newCustomer);
        layout.setTable1(tableModel);
        layout.getInvoiceTable().setModel(tableModel);
        layout.getTable1().fireTableDataChanged();
        }
        catch(IOException a){
            JOptionPane.showMessageDialog(layout, "wrong File Choosen", "Invalid File",
                    JOptionPane.ERROR_MESSAGE);
        }
        }
        else if(command=="Save File"){
        ArrayList <InvoiceHeader> invoiceFiles = layout.getObjects() ;
        String invoice="";
        String createline="";
        for (InvoiceHeader file : invoiceFiles ){
            String invoicefile =file.getSaveInvoice() ;
            invoice+=invoicefile;
            invoice+="\n";
           
            for (InvoiceLine creaetedline:  file.getAddedlines() ){
                String lineFile = creaetedline.getSaveLine()   ;
                createline += lineFile;
                createline+="\n";
                
            }
        }
        try{
        JFileChooser save=new JFileChooser();
        int buttonChooser=save.showSaveDialog(layout);
        if(buttonChooser==JFileChooser.APPROVE_OPTION){
            File invoiceHeader = save.getSelectedFile();
            FileWriter header = new FileWriter(invoiceHeader);
            header.write(invoice);
            header.flush();
            header.close();
            buttonChooser=save.showSaveDialog(layout);
            
            if(buttonChooser==JFileChooser.APPROVE_OPTION){
                File invoiceLine = save.getSelectedFile();
                FileWriter liner = new FileWriter(invoiceLine);
                liner.write(createline);
                liner.flush();
                liner.close();
            }
        }
        }catch(Exception a){}
        }
        else if(command=="Create New Invoice"){
         dialog = new FileSelector1(layout);
         dialog.setVisible(true);
        
        }
        else if(command=="saveInvoice"){
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        String CustomerName = dialog.getCustomerName().getText();
        String date = dialog.getDate().getText();
        int invoiceNum=layout.getNewNum();
        try{
            format.parse(date);
            InvoiceHeader newCreated = new InvoiceHeader(invoiceNum,date,CustomerName);
            layout.getObjects().add(newCreated);
            layout.getTable1().fireTableDataChanged();
                 dialog.setVisible(false);
                 dialog.dispose();
                 dialog = null;
        }catch(Exception a){
            JOptionPane.showMessageDialog(layout, "Invalid Formate", "Date Invalid",
                    JOptionPane.WARNING_MESSAGE);
        }

        }
        else if(command=="cancelInvoice"){
            dialog.setVisible(false);
            dialog.dispose();
            dialog = null;
        }
        else if(command=="saveLine"){
            try{
            String item = dialog2.getItemName().getText();
            String priceStr = dialog2.getItemPrice().getText();
            double price = Double.parseDouble(priceStr);
            String quantityStr=dialog2.getItemQuantity().getText();
            int quantity = Integer.parseInt(quantityStr);
            int row= layout.getInvoiceTable().getSelectedRow();
            if(row !=-1){
            InvoiceHeader selected = layout.getObjects().get(row);
            InvoiceLine created = new InvoiceLine(selected.getInvoiceNum(),item,price,quantity,selected);
            selected.getAddedlines().add(created);
            InvoiceLineTable modified= new InvoiceLineTable(selected.getAddedlines());
                layout.getInvoiceItem().setModel(modified);
                modified.fireTableDataChanged();
                layout.getTable1().fireTableDataChanged();
            }
            else{

            }
            }catch(Exception a){
            JOptionPane.showMessageDialog(layout, "Invalid Formate", "Invalid Data Entry",
                    JOptionPane.WARNING_MESSAGE);}
            dialog2.setVisible(false);
            dialog2.dispose();
            dialog2 = null;
        }
        else if(command=="cancelLine"){
            dialog2.setVisible(false);
            dialog2.dispose();
            dialog2 = null;
        
        }
        else if(command=="Delete Invoice"){
            int rowIndex = layout.getInvoiceTable().getSelectedRow();
            if(rowIndex==-1)
            {}
            else{
            layout.getObjects().remove(rowIndex);
            layout.getTable1().fireTableDataChanged();

            }
        
        }
        else if(command=="Create New Item"){
                     dialog2 = new FileSelector2(layout);
                     dialog2.setVisible(true);
        
        }
        else{
            int invoiceIndex = layout.getInvoiceTable().getSelectedRow();
            int rowIndex = layout.getInvoiceItem().getSelectedRow();
            if((invoiceIndex&rowIndex)==-1)
            {}
            else{
                InvoiceHeader selectedInvoice=layout.getObjects().get(invoiceIndex);
                selectedInvoice.getAddedlines().remove(rowIndex);
                InvoiceLineTable modified= new InvoiceLineTable(selectedInvoice.getAddedlines());
                layout.getInvoiceItem().setModel(modified);
                modified.fireTableDataChanged(); 
                layout.getTable1().fireTableDataChanged();
            }
        }
    }



}

