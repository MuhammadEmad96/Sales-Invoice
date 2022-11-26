
package Project1.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceLineTable  extends AbstractTableModel{
private ArrayList<InvoiceLine> objects;
private String [] columnsName = {"No","Item Name","Item Price","Count","Item Total"};

    public InvoiceLineTable(ArrayList<InvoiceLine> objects) {
        this.objects = objects;
    }


    @Override
    public int getRowCount() {
        return objects.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return columnsName[column];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = objects.get(rowIndex);
        switch(columnIndex){
            case 0: return line.getInvoiceNum();
            case 1: return line.getItem();
            case 2: return line.getPrice();
            case 3: return line.getItemQuantity();
            case 4: return line.getTotal();
            default: return "";
        }
    }

    public ArrayList<InvoiceLine> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<InvoiceLine> objects) {
        this.objects = objects;
    }
    
}
