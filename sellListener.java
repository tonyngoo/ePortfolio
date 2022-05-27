package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * The class sellListener is a class that is used inside of the GUI class, specifically when the user presses the "sell" button.
 * Its job is to take the inputs of symbol, quuantity and price and to check if the investment exists.
 * If it exists then it will try to sell the investment depending on if it is partially or fully sold.
 * If the quantity entered exceeds the amount owned, then the investment won't be sold as that is not possible.
 * If the quantity is partially sol,d then it will set a new quantity, price and bookvalue inside the object.
 * If it is fully sold then it will entirely remove the object from the arraylist.
 * Also when it is fully sol,d it will modify the hashtable as the indexes will be adjusted.
 */
public class sellListener implements ActionListener {
    /**
     * When the user presses the "sell" button, then it will call a new sellListener() which will run this method.
     * In this method, it will use getText() to find the user inputs for symbol, quantity and price and use them to calculcate/perform the selling.
     * Afterwards it will adjust the ArrayList and HashMap accordingly.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String symbol;
            String quantity;
            String price;
/*
            String totalQuantity;
            double payment = 0;
            double bookVal = 0;
            double bookValSell = 0;
            Integer quantityAfter = 0;
*/
            symbol = GUI.sellSymbolField.getText();
            quantity = GUI.sellQuantityField.getText();
            price = GUI.sellPriceField.getText();

            boolean emptyFieldFound = false;
            boolean incorrectFieldFound = false;

            GUI.sellMessageArea.setText("");

            if (Portfolio.isNumerical(quantity) == false) {
                if (!quantity.isEmpty()) {
                    GUI.sellMessageArea.append("You must enter a numerical quantity...\n");
                    incorrectFieldFound = true;
                }
            }
            if (Portfolio.isNumerical(price) == false) {
                if (!price.isEmpty()) {
                    GUI.sellMessageArea.append("You must enter a numerical price...\n");
                    incorrectFieldFound = true;
                }
            }
            if (symbol.isEmpty()) {
                GUI.sellMessageArea.append("You must enter a symbol... it cannot be empty\n");
                emptyFieldFound = true;
            }
            if (quantity.isEmpty()) {
                GUI.sellMessageArea.append("You must enter a quantity... it cannot be empty\n");
                emptyFieldFound = true;
            }
            if (price.isEmpty()) {
                GUI.sellMessageArea.append("You must enter a price... it cannot be empty\n");
                emptyFieldFound = true;
            }
            if (incorrectFieldFound == true) {
                return;
            }
            if (emptyFieldFound == true) {
                return;
            }

            for (Investment obj: Portfolio.Investments) { //Loop through all objects in the stocks arraylist
                String totalQuantity;
                double payment = 0;
                double bookVal = 0;
                double bookValSell = 0;
                Integer quantityAfter = 0;

                if (obj.getType().equalsIgnoreCase("Stock")) {
                    if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if an existing version of the inputted symbol exists
                        if (Integer.parseInt(quantity) <= 0) {
                            GUI.sellMessageArea.setText("The quantity entered was less than or equal to 0...\nPlease re-enter a new quantity.\n");
                            return;
                        }
                        if (Double.parseDouble(price) <= 0) {
                            GUI.sellMessageArea.setText("The price entered was less than or equal to 0....\nPlease re-enter a new price.\n");
                            return;
                        }
                        totalQuantity = obj.getQuantity();
                        bookVal = Double.parseDouble(obj.getBookValue());
                        payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 9.99;
                        if ((Integer.parseInt(totalQuantity)) > (Integer.parseInt(quantity))) { //If there is a higher quantity than being sold (PARTIAL)
                            bookValSell = bookVal * ((Double.parseDouble(quantity)) /  (Double.parseDouble(totalQuantity))); //Find bookValue for selling
                            bookVal = bookVal - bookValSell;
                            quantityAfter = (Integer.parseInt(totalQuantity) - (Integer.parseInt(quantity)));
                            obj.setBookValue(Double.toString(bookVal));
                            obj.setPrice(price); //If an existing version exists, set new price
                            obj.setQuantity(Integer.toString(quantityAfter));
                            double rounded = Math.round(payment * 100.0) / 100.0;
                            GUI.sellMessageArea.append("You have sold " + quantity + " shares of " + symbol.toUpperCase() + " for $" + rounded + "\n"); 
                            GUI.sellMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            return;
                        }
                        else if ((Integer.parseInt(totalQuantity)) == (Integer.parseInt(quantity))) { //If quantity equals (FULLY)
                            payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 9.99;
                            double rounded = Math.round(payment * 100.0) / 100.0;
                            GUI.sellMessageArea.append("You have sold " + quantity + " shares of " + symbol.toUpperCase() + " for $" + rounded + "\n");
                            GUI.sellMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            Portfolio.Investments.remove(obj);
                            Portfolio.hashMap.clear();
                            for (Investment objects: Portfolio.Investments) {
                                ArrayList<Integer> values = new ArrayList <Integer>();
                                String [] nameArr = objects.getName().split("\\s+");
                                for (int i = 0; i < nameArr.length; i++) {
                                    if (Portfolio.hashMap.get(nameArr[i]) != null) {
                                        values = Portfolio.hashMap.get(nameArr[i]);
                                    }
                                    else {
                                        values = new ArrayList <Integer>();
                                    }
                                    Integer indexAt = Portfolio.getIndex(objects.getName(), Portfolio.hashMap, Portfolio.Investments);
                                    values.add(indexAt);
                                    Portfolio.hashMap.put(nameArr[i], values);
                                }
                            }
                            return;
                        }
                        else { //If quantity is not valid as amount entered exceeds amount owned
                            GUI.sellMessageArea.setText("The quantity entered to be sold exceeds the amount that you own...\nPlease re-enter a valid quantity.");
                            return;
                        }
                    }
                }
                else if (obj.getType().equalsIgnoreCase("MutualFund")) {
                    if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if an existing version of the inputted symbol exists
                        if (Integer.parseInt(quantity) <= 0) {
                            GUI.sellMessageArea.setText("The quantity entered was less than or equal to 0...\nPlease re-enter a new quantity.\n");
                            return;
                        }
                        if (Double.parseDouble(price) <= 0) {
                            GUI.sellMessageArea.setText("The price entered was less than or equal to 0....\nPlease re-enter a new price.\n");
                            return;
                        }
                        totalQuantity = obj.getQuantity();
                        bookVal = Double.parseDouble(obj.getBookValue());
                        payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 45.00;

                        if ((Integer.parseInt(totalQuantity)) > (Integer.parseInt(quantity))) { //If there is a higher quantity than being sold (PARTIAL)
                            bookValSell = bookVal * ((Double.parseDouble(quantity)) /  (Double.parseDouble(totalQuantity))); //Find bookValue for selling
                            bookVal = bookVal - bookValSell;
                            quantityAfter = (Integer.parseInt(totalQuantity) - (Integer.parseInt(quantity)));
                            obj.setBookValue(Double.toString(bookVal));
                            obj.setPrice(price); //If an existing version exists, set new price
                            obj.setQuantity(Integer.toString(quantityAfter));
                            double rounded = Math.round(payment * 100.0) / 100.0;
                            GUI.sellMessageArea.append("You have sold " + quantity + " shares of " + symbol.toUpperCase() + " for $" + rounded + "\n");
                            GUI.sellMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            return;
                        }
                        else if ((Integer.parseInt(totalQuantity)) == (Integer.parseInt(quantity))) { //If quantity equals (FULLY)
                            payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 45.00;
                            double rounded = Math.round(payment * 100.0) / 100.0;
                            GUI.sellMessageArea.append("You have sold " + quantity + " shares of " + symbol.toUpperCase() + " for $" + rounded + "\n");
                            GUI.sellMessageArea.append("\n\nPlease select another option from the \"Commands\" menu or press \"Reset\" to buy another.");
                            Portfolio.Investments.remove(obj);
                            Portfolio.hashMap.clear();
                            for (Investment objects: Portfolio.Investments) {
                                ArrayList<Integer> values = new ArrayList <Integer>();
                                String [] nameArr = objects.getName().split("\\s+");
                                for (int i = 0; i < nameArr.length; i++) {
                                    if (Portfolio.hashMap.get(nameArr[i]) != null) {
                                        values = Portfolio.hashMap.get(nameArr[i]);
                                    }
                                    else {
                                        values = new ArrayList <Integer>();
                                    }
                                    Integer indexAt = Portfolio.getIndex(objects.getName(), Portfolio.hashMap, Portfolio.Investments);
                                    values.add(indexAt);
                                    Portfolio.hashMap.put(nameArr[i], values);
                                }
                            }
                            return;
                        }
                        else { //If quantity is not valid as amount entered exceeds amount owned
                            GUI.sellMessageArea.setText("The quantity entered to be sold exceeds the amount that you own...\nPlease re-enter a valid quantity.");
                            return;
                        }
                    }
                }
            }
            GUI.sellMessageArea.setText("The investment " + symbol + " was not found so it could not be sold\nPlease re-enter an existing investment or choose another command");
        }
        catch (Exception err) {
            GUI.sellMessageArea.setText("An error occurred when trying to sell:\n\n" + err.getMessage());
        }
    }
}