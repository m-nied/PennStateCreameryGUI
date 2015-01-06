/*
 * 
 * Customer Order Class
 * Handles Updating when Creating an Order
 * 
 */

package pennstatecreamery;

public class CustomerOrder {
    
    //Initialize Total Price Variable
    private double totalPrice = 0;
    
    //Initialize Price Variables
    private double iceCreamPrice = 0, containerPrice = 0, toppingPrice = 0;
    
    //Order Selection Placeholder
    private int selection = 1;
    
    //Order Selection Placeholder
    private String currentOrder = "";
    
    //Insert a New Line
    private String newline = System.getProperty("line.separator");
    
    public CustomerOrder(){}
    
    //Get Order Prices
    public void setItemPrices(String cream, String container, String topping){
        iceCreamPrice = Double.parseDouble(cream);
        containerPrice = Double.parseDouble(container);
        toppingPrice = Double.parseDouble(topping);
    }   
    
    //Add a Topping and Update Price
    public void addTopping(String type){
        currentOrder += selection +". "+ type + " " + "\t" + "$" + toppingPrice + newline;  
        selection++;
        totalPrice += toppingPrice;
    }    

    //Add an Ice Cream Scoop and Update Price
    public void addIceCream(String type){
        currentOrder += selection +". "+ type + " " + "\t" + "$" + iceCreamPrice + newline;
        selection++;
        totalPrice += iceCreamPrice;
    }  
    
    //Add a Container and Update Price
    public void addContainer(String type){
        currentOrder += selection +". "+ type + " " + "\t" + "$" + containerPrice + newline;  
        selection++;           
        totalPrice += containerPrice;
    }
    
    //Get Total Price
    public String getTotalPrice(){ 
        return Double.toString(totalPrice);
    }         
    
    //Get the Completed Order
    public String getOrder(){      
        return currentOrder;
    }
}
