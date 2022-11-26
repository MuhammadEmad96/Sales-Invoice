
package Project1.Model;

import java.util.ArrayList;

public class InvoiceHeader {
    private int    invoiceNum;
    private String date;
    private String customerName;
    private  ArrayList<InvoiceLine> addedlines;

    public InvoiceHeader(int invoiceNum, String date, String customerName) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customerName = customerName;
    }
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getAddedlines() {
        if(addedlines==null){
        addedlines=new ArrayList<>();
        }
        return addedlines;
    }
    public double getTotal(){
        double totalPayments=0.0;
        for(InvoiceLine totalAmountLine:getAddedlines()){
            totalPayments += totalAmountLine.getTotal();
        }
        return totalPayments;
    }
    public void setAddedlines(ArrayList<InvoiceLine> addedlines) {
        this.addedlines = addedlines;
    }
    
    public String getSaveInvoice(){
    
    return invoiceNum + "," + date + "," + customerName + "," ;
    }
  
}