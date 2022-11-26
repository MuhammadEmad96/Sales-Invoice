
package Project1.Model;

public class InvoiceLine {
    private int invoiceNum;
    private String item;
    private double price;
    private int itemQuantity;
    private InvoiceHeader obj;

    
    
    public InvoiceLine(int invoiceNum, String item, double price, int itemQuantity, InvoiceHeader obj) {
        this.invoiceNum = invoiceNum;
        this.item = item;
        this.price = price;
        this.itemQuantity = itemQuantity;
        this.obj = obj;
    }
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }
    

    public void setPrice(double price) {
        this.price = price;
    }
    
    public double setTotal(double priceOfLine,int items){
    return priceOfLine*items;
    }
    public double getTotal(){
    return setTotal(price,itemQuantity);
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    public String getSaveLine(){
    
    return obj.getInvoiceNum() + "," + item + "," + price + "," + itemQuantity ;
    }
  
    
}
