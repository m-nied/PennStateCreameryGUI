/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pennstatecreamery;

/**
 *
 * @author Inf
 */
public class DBValues {
    
    private String fName = "", lName = "", custBalance = "";
    
    public void setValuesFromManager(String fname, String lname, String balance){
        fName = fname;
        lName = lname;
        custBalance = balance;
    }

    public String getFirstName(){
        return fName;
    } 
    
    public String getLastName(){
        return lName;
    }   
    
    public String getBalance(){
        return custBalance;
    }       
}
