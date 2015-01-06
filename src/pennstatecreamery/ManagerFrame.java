/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pennstatecreamery;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author sph5183
 */
public class ManagerFrame extends javax.swing.JFrame {
    
    private String pinNumber = "", itemSelected = "";
    private int stockAmount = 0;
    
    DBValues dbV;
    DataBase db;
    CustomerOrder co;
    Customer c;
    /**
     * Creates new form ManagerFrame
     */
    public ManagerFrame(DataBase db, DBValues dbV, CustomerOrder co, Customer c) {
        initComponents();
        this.db = db;
        this.dbV = dbV;
        this.co = co;
        this.c = c;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        itemSelection = new javax.swing.JComboBox();
        inventoryLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        stockField = new javax.swing.JTextField();
        stockLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        searchLabel = new javax.swing.JLabel();
        pinField = new javax.swing.JTextField();
        pinLabel = new javax.swing.JLabel();
        searchCustomerButton = new javax.swing.JButton();
        balanceLabel = new javax.swing.JLabel();
        balanceField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameField = new javax.swing.JTextField();
        balanceLabel2 = new javax.swing.JLabel();
        firstNameField = new javax.swing.JTextField();
        restockButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        itemSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vanilla", "Chocolate", "Strawberry", "Sprinkles", "Hot-Fudge", "Waffle Cone", "Sugar Cone", "Banana Split", "Sundae" }));
        itemSelection.setToolTipText("Select an Item to view it's stock amount.");
        itemSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSelectionActionPerformed(evt);
            }
        });

        inventoryLabel.setText("Check Inventory");

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        stockField.setEditable(false);
        stockField.setFocusable(false);
        stockField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockFieldActionPerformed(evt);
            }
        });

        stockLabel.setText("Current Stock:");

        searchLabel.setText("Search Customer Details");

        pinField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinFieldActionPerformed(evt);
            }
        });

        pinLabel.setText("Pin:");

        searchCustomerButton.setText("Search");
        searchCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerButtonActionPerformed(evt);
            }
        });

        balanceLabel.setText("Balance:");

        balanceField.setEditable(false);
        balanceField.setFocusable(false);
        balanceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceFieldActionPerformed(evt);
            }
        });

        lastNameLabel.setText("Last Name:");

        lastNameField.setEditable(false);
        lastNameField.setFocusable(false);
        lastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameFieldActionPerformed(evt);
            }
        });

        balanceLabel2.setText("First Name:");

        firstNameField.setEditable(false);
        firstNameField.setFocusable(false);
        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });

        restockButton.setText("Restock Inventory");
        restockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventoryLabel)
                            .addComponent(stockLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stockField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restockButton))
                            .addComponent(itemSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(balanceLabel2)
                                    .addComponent(pinLabel))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pinField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(balanceLabel)
                            .addComponent(lastNameLabel))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchCustomerButton)
                            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventoryLabel)
                    .addComponent(itemSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockLabel)
                    .addComponent(stockField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restockButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pinField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pinLabel)
                    .addComponent(searchCustomerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balanceLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel)
                    .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        setVisible(false);
        OrderFrame orderFrame = new OrderFrame(db, dbV, co, c);
        
        orderFrame.setVisible(true);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void stockFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockFieldActionPerformed

    private void pinFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pinFieldActionPerformed

    private void balanceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceFieldActionPerformed

    private void itemSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSelectionActionPerformed
        itemSelected = itemSelection.getSelectedItem().toString();
        stockAmount = Integer.parseInt(db.checkItemStock(itemSelected));
        
        stockField.setText(Integer.toString(stockAmount));  
        
        if(stockAmount <= 3){
            JOptionPane.showMessageDialog(null,itemSelected + " is low in stock!\nPlease restock!","Low Stock",
                    JOptionPane.WARNING_MESSAGE);
        restockButton.setBackground(Color.red);
        }
    }//GEN-LAST:event_itemSelectionActionPerformed

    private void searchCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerButtonActionPerformed
        //Clear DBValues From Any Previous Entries
        dbV.setValuesFromManager("", "", "");
        pinNumber = pinField.getText().toString();
        
        //Error Check Main Entries Prior to Creating New Customer
        //In the Database        
        if(!pinNumber.matches("[0-9]{4}")){
            pinField.setBackground(Color.red);
            JOptionPane.showMessageDialog(null,"Please enter a valid pin number.","Pin Error", JOptionPane.ERROR_MESSAGE);
            pinField.setBackground(Color.white);
            firstNameField.setText("");
            lastNameField.setText("");
            balanceField.setText("");           
        }else{
            //Pull Existing Customer Balance From Database & DBValues Classes
            db.managerCustomerSearch(dbV, pinNumber);
            firstNameField.setText(dbV.getFirstName());
            lastNameField.setText(dbV.getLastName());
            balanceField.setText(dbV.getBalance());
        }
    }//GEN-LAST:event_searchCustomerButtonActionPerformed

    private void lastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameFieldActionPerformed

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void restockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockButtonActionPerformed
        db.restockItem(itemSelected);
        stockField.setText(db.checkItemStock(itemSelected));
        restockButton.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_restockButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceField;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel balanceLabel2;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JLabel inventoryLabel;
    private javax.swing.JComboBox itemSelection;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField pinField;
    private javax.swing.JLabel pinLabel;
    private javax.swing.JButton restockButton;
    private javax.swing.JButton searchCustomerButton;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField stockField;
    private javax.swing.JLabel stockLabel;
    // End of variables declaration//GEN-END:variables
}
