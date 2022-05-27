package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The buyListener class is a class that will be called whenever the user presses the "buy" button and wants to purchase a stock or mutualfund.
 * It will take in the user inputted smyobl, name, quantity and price in the textfields and store it into the Investments object Arraylist.
 * If the object already exists, then it will ask for a new quantity and priceto replace it in the ArrayList.
 * If it doesn't exist then it will just ask for a name, price, quantity and add this new object into the Arraylist as a "new Stock" or "new MutualFund"
 * If the symbol already exists for the other type then it will print in the message box
 * After it finishes adding it to the ArrayList, then it will loop through to update the HashMap which is utilized later inside search. 
 */
public class buyListener implements ActionListener {
    /**
     * When the "buy" action is performed, actionPerformed will run and it will add the properties as either a stock or mutualfund.
     * It will also loop through and update the HashMap accordingly.
     * In order to add the properties, it uses .getText() to find the user inputs in the textfield.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        double bookVal = 0;
        double oldbookVal = 0;
        double bookValComm = 0;
        int newQuantity = 0;


        if (GUI.buyType.equals("s")) {
            try{
                String symbol = GUI.buySymbolField.getText();
                String name = GUI.buyNameField.getText();
                String quantity = GUI.buyQuantityField.getText();
                String price = GUI.buyPriceField.getText();
                boolean emptyFieldFound = false;
                boolean incorrectFieldFound = false;

                GUI.buyMessageArea.setText("");

                //Check if inputs are numerical or not
                if (Portfolio.isNumerical(quantity) == false) {
                    if (!quantity.isEmpty()) {
                        GUI.buyMessageArea.append("You must enter a numerical quantity...\n");
                        incorrectFieldFound = true;
                    }
                }
                if (Portfolio.isNumerical(price) == false) {
                    if (!price.isEmpty()) {
                        GUI.buyMessageArea.append("You must enter a numerical price...\n");
                        incorrectFieldFound = true;
                    }
                }
                if (symbol.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a symbol... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (name.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a name... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (quantity.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a quantity... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (price.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a price... it cannot be empty\n");
                    emptyFieldFound = true;
                }
 
                if (emptyFieldFound == true) {
                    return;
                }
                if (incorrectFieldFound == true) {
                    return;
                }

                for (Investment obj: Portfolio.Investments) { //Loop through all of the objects in the arraylist
                    if (obj.getType().equalsIgnoreCase("stock")) {
                        if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if symbol equals inputted symbol, if it equals then enter new quantity & price
                            if (Integer.parseInt(quantity) <= 0) {
                                GUI.buyMessageArea.setText("Invalid quantity entered...\nPlease enter a quantity greater than 0.\n");
                                return;
                            }
    
                            if (Double.parseDouble(price) <= 0) {
                                GUI.buyMessageArea.setText("Invalid price entered...\nPlease enter a price greater than 0.\n");
                                return;
                            }
    
                            newQuantity = Integer.parseInt(quantity) + (Integer.parseInt(obj.getQuantity()));
                            obj.setQuantity(Integer.toString(newQuantity));
                            obj.setPrice(price); ////If an existing version exists, set new price
            
                            oldbookVal = Double.parseDouble(obj.getBookValue());
                            bookVal = oldbookVal + (Double.parseDouble(quantity) * Double.parseDouble(price));
                            bookValComm = bookVal + 9.99;
                            double rounded = Math.round(bookValComm * 100.0) / 100.0;
                            GUI.buyMessageArea.append("Bookvalue = $" + rounded);
                            obj.setBookValue(Double.toString(bookValComm));
                            GUI.buyMessageArea.append("\nYou bought " + quantity + " more shares of " + symbol.toUpperCase());
                            GUI.buyMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            return;
                        }
                    }
                    else if (obj.getType().equalsIgnoreCase("MutualFund")) {
                        if (obj.getSymbol().equalsIgnoreCase(symbol)) {
                            GUI.buyMessageArea.setText("Invalid: The symbol that you have entered is a symbol being used in Mutual Fund type already.\nPlease enter to the correct type.\n");
                            return;
                        }
                    }
                } //If the loop finishes and an existing symbol isn't found, then enter new attributes for a new investment
                if (Integer.parseInt(quantity) <= 0) {
                    GUI.buyMessageArea.setText("Invalid quantity entered...\nPlease enter a quantity greater than 0.\n");
                    return;
                }
                if (Double.parseDouble(price) <= 0) {
                    GUI.buyMessageArea.setText("Invalid price entered...\nPlease enter a price greater than 0.\n");
                    return;
                }
                bookVal = (Double.parseDouble(quantity) * (Double.parseDouble(price))) + 9.99;
                GUI.buyMessageArea.append("Bookvalue = $" + bookVal);
                Portfolio.Investments.add (new Stock ("Stock", symbol, name, quantity, price, Double.toString(bookVal))); //Add the investment into the arraylist
                GUI.buyMessageArea.append("\nYou bought " + quantity + " shares of " + symbol.toUpperCase());
                GUI.buyMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                Portfolio.hashMap.clear();
                for (Investment obj: Portfolio.Investments) {
                    ArrayList<Integer> values = new ArrayList <Integer>();
                    String [] nameArr = obj.getName().split("\\s+");
                    for (int i = 0; i < nameArr.length; i++) {
                        if (Portfolio.hashMap.get(nameArr[i]) != null) {
                            values = Portfolio.hashMap.get(nameArr[i]);
                        }
                        else {
                            values = new ArrayList <Integer>();
                        }
                        Integer indexAt = Portfolio.getIndex(obj.getName(), Portfolio.hashMap, Portfolio.Investments);
                        values.add(indexAt);
                        Portfolio.hashMap.put(nameArr[i], values);
                    }
                }

            } catch (Exception err) {
                GUI.buyMessageArea.setText("An error occurred when trying to buy:\n\n" + err.getMessage());
            }
        }
        //When a mutualfund is entered, enters this loop
        else {
            try{
                String symbol = GUI.buySymbolField.getText();
                String name = GUI.buyNameField.getText();
                String quantity = GUI.buyQuantityField.getText();
                String price = GUI.buyPriceField.getText();
                boolean emptyFieldFound = false;
                boolean incorrectFieldFound = false;

                GUI.buyMessageArea.setText("");

                //Check if inputs are numerical or not
                if (Portfolio.isNumerical(quantity) == false) {
                    if (!quantity.isEmpty()) {
                        GUI.buyMessageArea.append("You must enter a numerical quantity...\n");
                        incorrectFieldFound = true;
                    }
                }
                if (Portfolio.isNumerical(price) == false) {
                    if (!price.isEmpty()) {
                        GUI.buyMessageArea.append("You must enter a numerical price...\n");
                        incorrectFieldFound = true;
                    }
                }
                if (symbol.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a symbol... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (name.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a name... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (quantity.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a quantity... it cannot be empty\n");
                    emptyFieldFound = true;
                }
                if (price.isEmpty()) {
                    GUI.buyMessageArea.append("You must enter a price... it cannot be empty\n");
                    emptyFieldFound = true;
                }
 
                if (emptyFieldFound == true) {
                    return;
                }
                if (incorrectFieldFound == true) {
                    return;
                }
                for (Investment obj: Portfolio.Investments) { //Loop through all of the objects in the arraylist
                    if (obj.getType().equalsIgnoreCase("MutualFund")) {
                        if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if symbol equals inputted symbol, if it equals then enter new quantity & price
                            if (Integer.parseInt(quantity) <= 0) {
                                GUI.buyMessageArea.setText("Invalid quantity entered...\nPlease enter a quantity greater than 0.\n");
                                return;
                            }
    
                            if (Double.parseDouble(price) <= 0) {
                                GUI.buyMessageArea.setText("Invalid price entered...\nPlease enter a price greater than 0.\n");
                                return;
                            }
    
                            newQuantity = Integer.parseInt(quantity) + (Integer.parseInt(obj.getQuantity()));
                            obj.setQuantity(Integer.toString(newQuantity));
                            obj.setPrice(price); ////If an existing version exists, set new price
            
                            oldbookVal = Double.parseDouble(obj.getBookValue());
                            bookVal = oldbookVal + (Double.parseDouble(quantity) * Double.parseDouble(price));
                            bookValComm = bookVal;
                            double rounded = Math.round(bookValComm * 100.0) / 100.0;
                            GUI.buyMessageArea.append("Bookvalue = $" + rounded);
                            obj.setBookValue(Double.toString(bookValComm));
                            GUI.buyMessageArea.append("\nYou bought " + quantity + " more shares of " + symbol.toUpperCase());
                            GUI.buyMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            return;
                        }
                    }
                    else if (obj.getType().equalsIgnoreCase("Stock")) {
                        if (obj.getSymbol().equalsIgnoreCase(symbol)) {
                            GUI.buyMessageArea.setText("Invalid: The symbol that you have entered is a symbol being used in Stock type already.\nPlease enter for the correct type.\n");
                            return;
                        }
                    }
                } //If the loop finishes and an existing symbol isn't found, then enter new attributes for a new investment
                if (Integer.parseInt(quantity) <= 0) {
                    GUI.buyMessageArea.setText("Invalid quantity entered...\nPlease enter a quantity greater than 0.\n");
                    return;
                }
                if (Double.parseDouble(price) <= 0) {
                    GUI.buyMessageArea.setText("Invalid price entered...\nPlease enter a price greater than 0.\n");
                    return;
                }
                bookVal = (Double.parseDouble(quantity) * (Double.parseDouble(price)));
                GUI.buyMessageArea.append("Bookvalue = $" + bookVal);
                Portfolio.Investments.add (new MutualFund ("MutualFund", symbol, name, quantity, price, Double.toString(bookVal))); //Add the investment into the arraylist
                GUI.buyMessageArea.append("\nYou bought " + quantity + " shares of " + symbol.toUpperCase());
                GUI.buyMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                Portfolio.hashMap.clear();
                for (Investment obj: Portfolio.Investments) {
                    ArrayList<Integer> values = new ArrayList <Integer>();
                    String [] nameArr = obj.getName().split("\\s+");
                    for (int i = 0; i < nameArr.length; i++) {
                        if (Portfolio.hashMap.get(nameArr[i]) != null) {
                            values = Portfolio.hashMap.get(nameArr[i]);
                        }
                        else {
                            values = new ArrayList <Integer>();
                        }
                        Integer indexAt = Portfolio.getIndex(obj.getName(), Portfolio.hashMap, Portfolio.Investments);
                        values.add(indexAt);
                        Portfolio.hashMap.put(nameArr[i], values);
                    }
                }

            } catch (Exception err) {
                GUI.buyMessageArea.setText("An error occurred when trying to buy:\n\n" + err.getMessage());
            }
        }
    }
}