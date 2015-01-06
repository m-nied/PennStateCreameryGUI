/*
 * Customer Class
 * Stores Information for a Specific Customer
 */
package pennstatecreamery;

public class Customer {
    private String firstName = "";
    private String lastName = "";
    private String streetName = "";
    private String city = "";
    private String state = "";
    private String phoneNumber = "";
    private double customerBalance = 0;
    private int streetNum = 0;
    private int zipCode = 0;  
    private int userPin = 0;
    
    
    public void Customer (){ }
     

    public void setCustomerDetails(String first, String last, String street, int sNum,
            String cusCity, String cusState, String phone, int zip, int pin){
        firstName = first;
        lastName = last;
        streetName = street;
        streetNum = sNum;
        city = cusCity;
        state = cusState;
        phoneNumber = phone;
        zipCode = zip;
        userPin = pin;
        
    }
    
    public void setUserPin(int pin){
        userPin = pin;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getStreetName(){
        return streetName;
    }
    
    public int getStreetNum(){
        return streetNum;
    }
    
    public String getCity(){
        return city;
    }
    
    public double getBalance(){
        return customerBalance;
    }
    
    public void setBalance(double bal){
        customerBalance = bal;
    }
    
    public String getState(){
        return state;
    }  
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public int getZipCode(){
        return zipCode;
    }   
    
    public int getUserPin(){
        return userPin;
    }  
    
}
