/*
 * File: StateChangeable.java
 * Purpose: manage real estate database using GUI
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;


public class Project4 extends JFrame {
	
	private JLabel transNumLabel, addressLabel, bedroomsLabel, squareFootLabel, priceLabel;
	private JTextField transNumTxtField, addressTxtField, bedroomsTxtField, squareFootTxtField, priceTxtField;
	private JButton processButton, changeStatusButton;
	private JComboBox dataBase, propertyStatus;
	private JPanel masterPanel, componentPanel;
	
	
	//TreeMap as database definition with Transaction No. being the key of type Integer
	//and property object as the values
	private static TreeMap<Integer, Property> treeMapDataBase = new TreeMap<Integer, Property>(); 
	
	
	public Project4() {
		
		//master panel into which all subpanels are added to
		masterPanel = new JPanel();
		
	
		//initialize JLabels
		transNumLabel = new JLabel("Transaction No:");
		addressLabel = new JLabel("Address:");
		bedroomsLabel = new JLabel("Bedrooms");
		squareFootLabel = new JLabel("Square Footage");
		priceLabel = new JLabel("Price");
		
		//initialize JTextFields
		transNumTxtField = new JTextField(15);
		addressTxtField = new JTextField(15);
		bedroomsTxtField = new JTextField(15);
		squareFootTxtField= new JTextField(15);
		priceTxtField = new JTextField(15);
		
		//initialize JButtons
		processButton = new JButton("Process");
		changeStatusButton = new JButton("Change Status");
		
		//initialize JComboBoxes
		dataBase = new JComboBox<>();
		dataBase.setModel(new DefaultComboBoxModel<>(new String[] {"Insert", "Delete", "Find"}));
		propertyStatus = new JComboBox<>();
		propertyStatus.setModel(new DefaultComboBoxModel<>(new String[] {"FOR_SALE", "UNDER_CONTRACT", "SOLD"}));
		
		//initialize panel for components to be held
		componentPanel = new JPanel(new GridLayout(7,2,10,10));
		componentPanel.setBorder(new EmptyBorder(0,0,0,0));
		
		
		//add components into componentPanel
		componentPanel.add(transNumLabel);
		componentPanel.add(transNumTxtField);
		componentPanel.add(addressLabel);
		componentPanel.add(addressTxtField);
		componentPanel.add(bedroomsLabel);
		componentPanel.add(bedroomsTxtField);
		componentPanel.add(squareFootLabel);
		componentPanel.add(squareFootTxtField);
		componentPanel.add(priceLabel);
		componentPanel.add(priceTxtField);
		componentPanel.add(processButton);
		componentPanel.add(dataBase);
		componentPanel.add(changeStatusButton);
		componentPanel.add(propertyStatus);
		
		
		//add componentPanel to masterPanel
		masterPanel.add(BorderLayout.CENTER, componentPanel);
		//add masterPanel to JFrame
		add(masterPanel);
		
		//Add event listeners for JButtons
		processButton.addActionListener(new Buttons());
		changeStatusButton.addActionListener(new Buttons());
		
	}
	
	
	
	
	
	/************Nested Classes***************/
	class Buttons implements ActionListener {
		
		//class variables 
		int transactionNum, bedrooms, squareFeet, price;
		String address;
		
		Property prop = new Property(address, bedrooms, squareFeet, price, Property.propStatus);
		

		
		@Override
		public void actionPerformed(ActionEvent e) {
			//action listener instructions for JButton Process 
			if (e.getSource() == processButton) {
				
				
				
				try { 
					//parsing all TextFields into data types
					transactionNum = Integer.parseInt(transNumTxtField.getText());
					address = addressTxtField.getText();
					bedrooms = Integer.parseInt(bedroomsTxtField.getText());
					squareFeet = Integer.parseInt(squareFootTxtField.getText());
					price = Integer.parseInt(priceTxtField.getText());
					
					
					//instructions if insert is chosen
					if (dataBase.getSelectedItem().equals("Insert")) {
							
						//search the database to ensure duplicates aren't added
							if (treeMapDataBase.containsKey(transactionNum)) {
								
								JOptionPane.showMessageDialog(null, "Transaction No." + transactionNum + " is already entered in database.", "Duplicate Entry",JOptionPane.ERROR_MESSAGE);
								
							} else {
								try {
									if (transactionNum < 0 || bedrooms < 0 ||  squareFeet < 0 || price < 0) {
										throw new NumberFormatException();
									} 
									
									if (address.isEmpty()) {
										throw new NumberFormatException();
									} else {
										
										//add key and value to tree map database
										prop = new Property(address, bedrooms, squareFeet, price, Property.propStatus);
										//adding key and value to treemap database
										treeMapDataBase.put(transactionNum, prop);
										JOptionPane.showMessageDialog(null, "Transaction No." + transactionNum + " added to database.", "New Key Entry", JOptionPane.INFORMATION_MESSAGE);
										return;
									}
									
								} catch (NumberFormatException n) {
									JOptionPane.showMessageDialog(transNumTxtField, "Please enter valid info for each text field", "Input Error",JOptionPane.ERROR_MESSAGE);
									return;
								} 
							}
						}
					
					
					
					
					
					
					if (dataBase.getSelectedItem().equals("Delete")) {
						
						if (treeMapDataBase.containsKey(transactionNum) == false) {
							JOptionPane.showMessageDialog(null, "Transaction No." + transactionNum + " does not exist in database.", "New Key Entry", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						//delete entry
						else if (treeMapDataBase.containsKey(transactionNum)) {
							treeMapDataBase.remove(transactionNum, prop);
						}
						JOptionPane.showMessageDialog(null, "Transaction No." + transactionNum + " removed from database.", "Database Key Removal", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					
					
					
					
					
					if (dataBase.getSelectedItem().equals("Find")) {
						if (treeMapDataBase.containsKey(transactionNum) == false) {
							JOptionPane.showMessageDialog(null, "Transaction No." + transactionNum + " does not exist.", "New Key Entry", JOptionPane.INFORMATION_MESSAGE);
							return;
						} else {
							JOptionPane.showMessageDialog(null, "Property Details:\n" + prop.toString(), "Property Summary", JOptionPane.INFORMATION_MESSAGE);
							return;
							}
							
						
					}

					
				
				} 	catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(transNumTxtField, "Please enter valid info for each text field", "Input Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			
			
			
			
			
			//Action Listener instructions for Change Status JButton
			if (e.getSource() == changeStatusButton) {
				transactionNum = Integer.parseInt(transNumTxtField.getText());
							
						
				if (propertyStatus.getSelectedItem().equals("FOR_SALE")) {
					prop = new Property(address, bedrooms, squareFeet, price, Property.propStatus.FOR_SALE);
							
					JOptionPane.showMessageDialog(null, "This property (Transaction No." + transactionNum + ") has been changed to FOR_SALE status", "Property Status Changed",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
					
				if (propertyStatus.getSelectedItem().equals("UNDER_CONTRACT")) {
					prop = new Property(address, bedrooms, squareFeet, price, Property.propStatus.UNDER_CONTRACT);
					JOptionPane.showMessageDialog(null, "This property (Transaction No." + transactionNum + ") has been changed to UNDER_CONTRACT status", "Property Status Changed",JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				if (propertyStatus.getSelectedItem().equals("SOLD")) {
					prop = new Property(address, bedrooms, squareFeet, price, Property.propStatus.SOLD);
					JOptionPane.showMessageDialog(null, "This property (Transaction No." + transactionNum + ") has been changed to SOLD status", "Property Status Changed",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} 
		}	
	} 
	
	//show GUI
	public static void main(String[] args) {
		Project4 p4 = new Project4();
		p4.setTitle("Real Estate Database");
		//width & length
		p4.setSize(400,300);
		p4.setVisible(true);
		p4.setResizable(false);
		p4.setLocationRelativeTo(null);
		p4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
