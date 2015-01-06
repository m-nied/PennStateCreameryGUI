/*
 * 
 * Database Class
 * Controls Manipulation of a Access Database
 * 
 */

package pennstatecreamery;


import java.io.*;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import java.sql.*; /* imported for basic JDBC connectivity */
import java.util.ArrayList;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class DataBase extends Customer{
    private int nextID = 0, stockNum = 0, productNum = 0;
    private int lastCustId = 0, dbCount = 0;
    
    private PreparedStatement sqlFind;
    private Customer customer = new Customer();
    
 
    
    //DataBase Constructor
    public DataBase() throws SQLException, Exception{
        
        this.myErrorHandler = new ErrorHandler(){
            @Override
            public void fatalError(SAXParseException exception) throws SAXException{
                System.err.println("fatalError: " + exception);
            }

            @Override
            public void error(SAXParseException exception) throws SAXException{
                System.err.println("error: " + exception);
            }

            @Override
            public void warning(SAXParseException exception) throws SAXException{
                System.err.println("warning: " + exception);
            }
        };  
    }
    
    //Initialization of DataBase
    public void Initialize() throws SQLException, Exception{
        if(dbCount == 0){
            System.out.println("Creating Database tables...");
            CreateTable();
            dbCount++;
        }
        System.out.println("Loading XML to Database...");
        importCustomerDataFromXML("Customers.xml");
        importProductDataFromXML("Stock.xml");
        importStockDataFromXML("Product.xml");
        System.out.println("XML Loaded to DataBase.");
    }
    
    ErrorHandler myErrorHandler;
    
    //================================================================================
    // Table Creation & XML Reading Methods
    //================================================================================
    
    //Creates Customers, Stock, and Product table in the Access database
    public void CreateTable(){
        try{
            // loads database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection connect = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement statemnt = connect.createStatement();
            // drops the table
            statemnt.execute("DROP TABLE Customers");
            statemnt.execute("DROP TABLE Product");
            statemnt.execute("DROP TABLE Stock");
            // this statement creates a new table named Customers in the database
            statemnt.execute("CREATE TABLE Customers"
                    + "(CustomerID int, FName varchar(255),"
                    + " LName varchar(255),"
                    + " StreetNum int, StreetName varchar (255), City varchar(255),"
                    + " State varchar(255), ZipCode int, Phone varchar(255),"
                    + " PIN int, Balance double)");

            // this statement creates a new table named Product in the database
            statemnt.execute("CREATE TABLE Product"
                    + "(ItemID int, ItemName varchar(255),"
                    + "Price double, ItemType varchar(255))");

            //this statement creates a new table named Stock in the database
            statemnt.execute("CREATE TABLE Stock"
                    + "(StockID int, ItemName varchar(255),"
                    + "Amount int, ItemLimit int, ItemType varchar(255))");

            statemnt.close();
            connect.close();
        }catch(SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException.getMessage(), 
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }catch(ClassNotFoundException classNotFound){
            JOptionPane.showMessageDialog(null,
                    classNotFound.getMessage(), "Driver Not Found",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }
    }
    
    //Imports Customer Data
    public void importCustomerDataFromXML(String fileName) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setValidating(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(myErrorHandler);
            Document document = builder.parse("Customers.xml");
            NodeList list = document.getElementsByTagName("Customers");

            //reads in the customer record from the XML by each attribute
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                String xmlFName = getFName(element);
                String xmlLName = getLName(element);
                String xmlStreetNum = getStreetNum(element);
                String xmlStreetName = getStreetName(element);
                String xmlCity = getCity(element);
                String xmlState = getState(element);
                String xmlZipCode = getZipCode(element);
                String xmlPhone = getPhone(element);
                String xmlPIN = getPIN(element);
                String xmlBalance = getBalance(element);               
               //Store to DataBase
                storeCustomerInfo(i, xmlFName, xmlLName, xmlStreetNum, xmlStreetName, xmlCity, xmlState, xmlZipCode, xmlPhone, xmlPIN, xmlBalance);
                nextID++;
                lastCustId = nextID;
            }
        }
        catch (ParserConfigurationException | SAXException | IOException parserException) {
        }
    } 
    
    //Imports Product Data
    public void importProductDataFromXML(String fileName) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(myErrorHandler);
            builderFactory.setValidating(true);
            Document document = builder.parse("Product.xml");
            NodeList list = document.getElementsByTagName("Product");
            //reads in the product record from the XML by each attribute
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                String xmlItemName = getItemName(element);
                String xmlItemPrice = getItemPrice(element);
                String xmlItemType = getItemType(element);
               //store the information into the database
                storeProductInfo(i, xmlItemName, xmlItemPrice, xmlItemType);
                nextID++;
            }
        }
        catch (ParserConfigurationException | SAXException | IOException parserException) {
        }
    }  
    
    //Imports Stock Data
    public void importStockDataFromXML(String fileName) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(myErrorHandler);
            builderFactory.setValidating(true);
            Document document = builder.parse("Stock.xml");
            NodeList list = document.getElementsByTagName("Stock");
            //reads in the product record from the XML by each attribute
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                String xmlItemName = getItemName(element);
                String xmlItemAmount = getItemAmount(element);
                String xmlItemLimit = getItemLimit(element);
                String xmlItemType = getItemType(element);
               //store the information into the database
                storeStockInfo(i, xmlItemName, xmlItemAmount, xmlItemLimit, xmlItemType);
                nextID++;
            }
        }catch (ParserConfigurationException | SAXException | IOException parserException) {
        }
    }
    
    //================================================================================
    // XML Insertion Methods
    //================================================================================    
    
    //Stores Customer Info into Database from XML
    private void storeCustomerInfo(int uniqueID, String firstName, String lastName, String streetNumber, String streetName,  String city, String state, String zipCode, String phone, String pin, String balance) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection connect = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement statemnt = connect.createStatement();
            //this Insert statement puts customer info in the database
            statemnt.executeUpdate("INSERT INTO Customers VALUES (" + uniqueID + ",'" + firstName + "','" + lastName + "','" + streetNumber + "','" + streetName + "','" + city + "','" + state + "','" + zipCode + "','" + phone + "','" + pin + "','" + balance +"')");
            statemnt.close();
            connect.close();
        }catch ( SQLException sqlException ) {
            JOptionPane.showMessageDialog( null, 
               sqlException.getMessage(), "Database Error",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }catch ( ClassNotFoundException classNotFound ) {
            JOptionPane.showMessageDialog( null, 
               classNotFound.getMessage(), "Driver Not Found",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }
    }

    //Stores Product Info into Database from XML
    private void storeProductInfo(int uniqueID, String itemName, String itemPrice, String itemType) {
        try {
            // load database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:odbc:PSUCreamery"); 
                    Statement statemnt = connect.createStatement()) {
                //this Insert statement puts customer info in the database
                statemnt.executeUpdate("INSERT INTO Product VALUES (" + uniqueID + ",'" + itemName + "','" + itemPrice + "','" + itemType +"')");
                statemnt.close();
                connect.close();
            }
        }catch ( SQLException sqlException ) {
            JOptionPane.showMessageDialog( null, 
               sqlException.getMessage(), "Database Error",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }catch ( ClassNotFoundException classNotFound ) {
            JOptionPane.showMessageDialog( null, 
               classNotFound.getMessage(), "Driver Not Found",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }
    }
    
    //Stores Stock Info into Database from XML
    private void storeStockInfo(int uniqueID, String itemName, String itemAmount, String itemLimit, String itemType) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            try (Connection connect = DriverManager.getConnection("jdbc:odbc:PSUCreamery"); Statement statemnt = connect.createStatement()) {
                //this Insert statement puts customer info in the database
                statemnt.executeUpdate("INSERT INTO Stock VALUES (" + uniqueID + ",'" + itemName + "','" + itemAmount + "','" + itemLimit +"','" + itemType +"')");
                statemnt.close();
                connect.close();
            }
        }catch ( SQLException sqlException ) {
            JOptionPane.showMessageDialog( null, 
               sqlException.getMessage(), "Database Error",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }catch ( ClassNotFoundException classNotFound ) {
            JOptionPane.showMessageDialog( null, 
               classNotFound.getMessage(), "Driver Not Found",
               JOptionPane.ERROR_MESSAGE );
            System.exit( 1 );
         }
    }
    
    //================================================================================
    // getElement Methods
    //================================================================================
    
    //Returns First Name   
    public String getFName(Element parent){ 
        NodeList child = parent.getElementsByTagName("FName");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
   //Returns Last Name 
    public String getLName(Element parent){ 
        NodeList child = parent.getElementsByTagName("LName");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Street Number    
    public String getStreetNum(Element parent){ 
        NodeList child = parent.getElementsByTagName("StreetNum");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Street Name
    public String getStreetName(Element parent){ 
        NodeList child = parent.getElementsByTagName("StreetName");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns City  
    public String getCity(Element parent){ 
        NodeList child = parent.getElementsByTagName("City");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns State   
    public String getState (Element parent){ 
        NodeList child = parent.getElementsByTagName("State");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns ZipCode    
    public String getZipCode(Element parent){ 
        NodeList child = parent.getElementsByTagName("ZipCode");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Phone Number    
    public String getPhone(Element parent){ 
        NodeList child = parent.getElementsByTagName("Phone");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Pin Number    
    public String getPIN(Element parent){ 
        NodeList child = parent.getElementsByTagName("PIN");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Balance Number    
    public String getBalance(Element parent){ 
        NodeList child = parent.getElementsByTagName("Balance");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //returns Item Name   
    public String getItemName(Element parent){ 
        NodeList child = parent.getElementsByTagName("ItemName");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Item Price 
    public String getItemPrice(Element parent){ 
        NodeList child = parent.getElementsByTagName("Price");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Item Type  
    public String getItemType(Element parent){ 
        NodeList child = parent.getElementsByTagName("ItemType");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Item Amount   
    public String getItemAmount(Element parent){ 
        NodeList child = parent.getElementsByTagName("Amount");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //Returns Item Limit  
    public String getItemLimit(Element parent){ 
        NodeList child = parent.getElementsByTagName("ItemLimit");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();  
    }
    
    //================================================================================
    // Database Manipulation Methods
    //================================================================================    

    //Stores a new customer in the Customers table
    public void storeCustomer(Customer c){
        try {
            // load database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // connect to database
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");

            Statement stmt = con.createStatement();
            //this Insert statement puts student info in the database
            stmt.executeUpdate("INSERT INTO Customers VALUES (" + lastCustId + ",'" + c.getFirstName() + "','" + c.getLastName() + "','" + c.getStreetNum() + "','" + c.getStreetName() + "','" + c.getCity() + "','" + c.getState() + "','" + c.getZipCode() + "','" + c.getPhoneNumber() + "','" + c.getUserPin() + "','" + c.getBalance() +"')");
            lastCustId++;
            stmt.close();
            con.close();
           }catch(ClassNotFoundException | SQLException e){}
    }
    
    //Uses a Unique PIN to create an order
    public boolean existingCustomer(Customer c, String pin){
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Pin
            sqlFind = con.prepareStatement("SELECT * from Customers WHERE PIN = ?");
            
            sqlFind.setString( 1, pin );
            ResultSet results = sqlFind.executeQuery();
            
            String fName = "", lName = "", balance = "";
            int pinNumber;
            if(results.next()){
                fName = results.getString("FName");
                lName = results.getString("LName");
                pinNumber = results.getInt("PIN");
                balance = results.getString("Balance");
                
                c.setUserPin(pinNumber);
                JOptionPane.showMessageDialog(null,"Welcome back "+fName+" "+lName+"\n"
                        +"Your current balance is: $"+balance+".","Welcome Back!",
                        JOptionPane.INFORMATION_MESSAGE);
                
                results.close();
                stmt.close();
                con.close();
                return true;   
            }else{
                JOptionPane.showMessageDialog(null,"No Customers found.\nPlease try again.","Invalid Pin", 
                        JOptionPane.ERROR_MESSAGE);
                stmt.close();
                con.close();
                return false;
            }
          
           }catch(ClassNotFoundException | SQLException e){}      
        return false;
    }   

    //Manager Option to Search for customer Details Based on a Unique PIN
    public void managerCustomerSearch(DBValues dbv, String pin){
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Pin
            sqlFind = con.prepareStatement("SELECT * from Customers WHERE PIN = ?");
            
            sqlFind.setString( 1, pin );
            ResultSet results = sqlFind.executeQuery();
            
            String fName = "", lName = "", balance = "";
            if(results.next()){
                fName = results.getString("FName");
                lName = results.getString("LName");
                balance = results.getString("Balance");
                dbv.setValuesFromManager(fName, lName, "$"+balance);
                results.close();
                stmt.close();
                con.close();
            }else{
                JOptionPane.showMessageDialog(null,"No Customers found.\nPlease try again.","Invalid Pin", JOptionPane.ERROR_MESSAGE);
                results.close();
                stmt.close();
                con.close();
            }
           }catch(ClassNotFoundException | SQLException e){}
    }     
    
    //Checks the Stock value of a selected Item
    public String checkItemStock(String item){
        String itemStock = "";
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Selection
            sqlFind = con.prepareStatement("SELECT Amount from Stock WHERE ItemName = ?");
            
            sqlFind.setString( 1, item );
            ResultSet results = sqlFind.executeQuery();
            while(results.next()){
               itemStock = results.getString("Amount"); 
            }
            results.close();
            stmt.close();
            con.close();
           }catch(ClassNotFoundException | SQLException e){}
        return itemStock;
    }
    
    //Checks to make validate a user pin is unique to only one Customer
    public boolean checkUserPin(int pin){
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Selection
            sqlFind = con.prepareStatement("SELECT * from Customers WHERE PIN = ?");
            
            sqlFind.setInt( 1, pin );
            ResultSet results = sqlFind.executeQuery();
            if(results.next()){
                JOptionPane.showMessageDialog(null,"'"+pin+"' is not available.\nPlease try again.","Invalid Pin", 
                        JOptionPane.ERROR_MESSAGE);
                results.close();
                stmt.close();
                con.close();
                return false;
            }
           }catch(ClassNotFoundException | SQLException e){}
        return true;
    }    
    
    //Updates a Customers balance after order creation
    public void updateCustomerBalance(Customer c, int pin){
       try {
           int userPin = pin;
           double currentBalance = 0, newBalance = 0;
            // load database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // connect to database
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");

            Statement stmt = con.createStatement();       
            
            sqlFind = con.prepareStatement("SELECT Balance from Customers WHERE PIN = ?");
            sqlFind.setInt(1, userPin);
            ResultSet results = sqlFind.executeQuery();
            if(results.next()){
                currentBalance = results.getDouble("Balance"); 
            }
            
            newBalance = currentBalance + c.getBalance();
             
            PreparedStatement updateBalance = null;
            String updateStockTable = "UPDATE Customers" + " SET Balance = ? " + " WHERE PIN = ?";
            updateBalance = con.prepareStatement(updateStockTable);
            updateBalance.setDouble(1, newBalance );
            updateBalance.setInt(2, userPin);
            updateBalance.executeUpdate();

            //System.out.println(item + ": Stock: "+ curStock);

            stmt.close();
            con.close();
            
       }catch(ClassNotFoundException | SQLException e){}        
        
    }

    //Removes an Added Item From the Stock Table
    public void removeStock(String item) throws SQLException, ClassNotFoundException {
       try {
           String currentStock = checkItemStock(item);
           int curStock = Integer.parseInt(currentStock);
           
           if(curStock > 0){  
               curStock -= 1;
               if(curStock <= 3 && curStock != 0){
                   JOptionPane.showMessageDialog(null,"There are only "+ curStock +" "+ item + " left in stock!.\nPlease restock!.","Low Stock!", JOptionPane.WARNING_MESSAGE);
               }
           }else{
               JOptionPane.showMessageDialog(null,item + " is out of stock!!.\nPlease restock!.","Out of Stock", JOptionPane.WARNING_MESSAGE);
           }
           
            // load database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // connect to database
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");

            Statement stmt = con.createStatement();       

            PreparedStatement removeItem = null;
            String updateStockTable = "UPDATE Stock" + " SET Amount = ? " + " WHERE ItemName = ?";
            removeItem = con.prepareStatement(updateStockTable);
            removeItem.setString(2, item);
            removeItem.setInt(1, curStock);
            removeItem.executeUpdate();
            
            stmt.close();
            con.close();
            
       }catch(ClassNotFoundException | SQLException e){}
    }
    
    //Queries an items price for order creation
    public void setItemPrices(CustomerOrder co, String item){
        String itemPrice = "";
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Selection
            sqlFind = con.prepareStatement("SELECT Price from Product WHERE ItemType = ?");
            
            sqlFind.setString( 1, item );
            ResultSet results = sqlFind.executeQuery();
            while(results.next()){
                if(item.matches("Ice Cream")){
                    itemPrice = results.getString("Price"); 
                    co.setItemPrices(itemPrice, "0", "0");
                }else if(item.matches("Container")){
                    itemPrice = results.getString("Price"); 
                    co.setItemPrices("0", itemPrice, "0");
                }else{
                    itemPrice = results.getString("Price"); 
                    co.setItemPrices("0", "0", itemPrice);
                }
            }
            results.close();
            stmt.close();
            con.close();  
           }catch(ClassNotFoundException | SQLException e){}
    }
    
    //Queries to check the price of an item
    public String checkItemPrice(String item){
        String itemPrice = "";
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Selection
            sqlFind = con.prepareStatement("SELECT Price from Product WHERE ItemName = ?");
            
            sqlFind.setString( 1, item );
            ResultSet results = sqlFind.executeQuery();
            while(results.next()){
               itemPrice = results.getString("Price"); 
            }
            
            stmt.close();
            con.close();  
           }catch(ClassNotFoundException | SQLException e){}
        return itemPrice;
    }   
    
    //Restocks an item into the Database
    public void restockItem(String item){
        try {
            // load database driver class
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            // connect to database
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");

            Statement stmt = con.createStatement();       

            PreparedStatement reStock = null;
            String updateStockTable = "UPDATE Stock" + " SET Amount = ? " + " WHERE ItemName = ?";
            reStock = con.prepareStatement(updateStockTable);
            reStock.setDouble(1, 25);
            reStock.setString(2, item);
            reStock.executeUpdate();

            stmt.close();
            con.close();
       }catch(ClassNotFoundException | SQLException e){}       
    }
    
    //Updates Customers.xml from Customers Table
    public void updateCustomerXml() throws ClassNotFoundException, SQLException, IOException, TransformerConfigurationException, TransformerException{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
        Statement stmt = con.createStatement();
        String FName ="" , LName ="", StreetNum="" , StreetName ="", City ="", State="",  ZipCode="", Phone="", PIN ="", Balance="";
        int uPin=0, zip=0, sNum=0;
            double bala=0.0;
        File file = new File("Customers.xml");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
        
         ResultSet rs = stmt.executeQuery("SELECT * from Customers");
            StringBuilder sqlXml = new StringBuilder();
            while (rs.next())
            {
              FName = rs.getString("FName");
              LName = rs.getString("LName");
              StreetNum = rs.getString("StreetNum");
              StreetName = rs.getString("StreetName");
              City = rs.getString("City");
              State = rs.getString("State");
              ZipCode = rs.getString("ZipCode");
              Phone = rs.getString("Phone");
              PIN = rs.getString("PIN");
              Balance = rs.getString("Balance");
              sNum=Integer.parseInt(StreetNum);
              uPin=Integer.parseInt(PIN);
              zip=Integer.parseInt(ZipCode);
              bala=Double.parseDouble(Balance);
            sqlXml.append ("\t<Customers>");
            sqlXml.append("\n\t\t<FName>").append(FName).append ("</FName>\t");
            sqlXml.append("\n\t\t<LName>").append(LName).append ("</LName>\t");
            sqlXml.append("\n\t\t<StreetNum>").append(sNum).append ("</StreetNum>\t");
            sqlXml.append("\n\t\t<StreetName>").append(StreetName).append ("</StreetName>\t");
            sqlXml.append("\n\t\t<City>").append(City).append ("</City>\t");
            sqlXml.append("\n\t\t<State>").append(State).append ("</State>\t");
            sqlXml.append("\n\t\t<ZipCode>").append(zip).append ("</ZipCode>\t");
            sqlXml.append("\n\t\t<Phone>").append(Phone).append ("</Phone>\t");
            sqlXml.append("\n\t\t<PIN>").append(PIN).append ("</PIN>\t");
            sqlXml.append("\n\t\t<Balance>").append(bala).append ("</Balance>");
            sqlXml.append ("\n\t</Customers>\n");
            
            }
        
        sqlXml.insert(0,"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sqlXml.insert(39,"<!DOCTYPE customerRecords SYSTEM \"Customers.dtd\">\n");
        sqlXml.insert(89,"<customerRecords>\n");
        sqlXml.insert(sqlXml.length(),"</customerRecords>");
        bw.write(sqlXml.toString());
        
        //System.out.println( sqlXml );
        rs.close();
        bw.close();
        stmt.close();
        con.close();
    }    
    
    //Work in Progress
    public ArrayList getCustomerTable(){
         ArrayList customerList = new ArrayList();
         String customerInfo = "";
        try {
            //Load Database Driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //Database connection
            Connection con = DriverManager.getConnection("jdbc:odbc:PSUCreamery");
            Statement stmt = con.createStatement();
            
            //Search Database Based on Users Selection
            sqlFind = con.prepareStatement("SELECT * from Customers");
            ResultSet results = sqlFind.executeQuery();
            while(results.next()){
              double listSize = customerList.size(); 
              int listSizeInt = (int)Math.sqrt(listSize);
              for(int i = 0; i <= listSizeInt; i++){
                  customerList.add(results.getString("FName") + results.getString("Balance"));
              }
            }
            
            stmt.close();
            con.close();  
           }catch(ClassNotFoundException | SQLException e){}       
        return customerList;
    }
}
