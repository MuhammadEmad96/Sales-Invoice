
package Project1.Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceTable extends AbstractTableModel {
    
    private ArrayList<InvoiceHeader> objects;
    private String [] columnsName = {"No","Date","Customer","Total"};
    public InvoiceTable(ArrayList<InvoiceHeader> objects) {
        this.objects = objects;
    }
    
    @Override
    public int getRowCount() {
         return objects.size();
         
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        return columnsName[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = objects.get(rowIndex); 
        switch(columnIndex){
            case 0: return invoice.getInvoiceNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getCustomerName();
            case 3: return invoice.getTotal();
            default: return "";
        }
    }
    
}
