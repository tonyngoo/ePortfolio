package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * searchListener is a class that is used inside of the GUI class.
 * It's purpose is to complete and calculate the searches required when the user presses the "search" button in the GUI window.
 * The class utilizes both Stock and MutualFunds ArrayLists into the parameter.
 * It takes in 4 inputs from the GUI: symbol, keyword, lower price, higher price.
 * If the inputs are given, then it first searches through all Stocks and checks if the filters apply. If the filters match and apply to the investment,
 * then it will print them accordingly. It will then perform the same thing for all MutualFunds.
 * If it has gone through all of the Investments and no matches are found, then it will print in the Messages box that no matches were found.
 */
public class searchListener implements ActionListener {
    /**
     * actionPerformed is the method where it performs the needed calculattions/processing after the "search" button is clicked
     * It will use .getText() to find the user inputs inside the textfields and use them accordingly to filter through the investments.
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        try {
            String symbol;
            String keyword;
            String price = "";
            String lowPrice;
            String highPrice;
            double upperPrice = 0;
            double lowerPrice = 0;
            Integer wordCounter = 0;
    
            String invName;
            Double invPrice;
       
            boolean symbolMatch;
            boolean priceMatch;
            boolean nameMatch;
            boolean finalTrue;
            boolean noUpper = false;
            boolean noLower = false;
            boolean multipleWord = false;
            boolean noInvFound = true;

            symbol = GUI.searchSymbolField.getText();
            keyword = GUI.searchNameField.getText();
            lowPrice = GUI.lowPriceField.getText();
            highPrice = GUI.highPriceField.getText();
            GUI.resetButton.setVisible(false);

            GUI.searchMessageArea.setText("The following matches were found to the search: \n\n");
            
            if (!keyword.isEmpty()) {
                for (int k = 0; k < keyword.length(); k++) {
                    if (keyword.charAt(k) == ' ') {
                        wordCounter += 1;
                    }
                }
            }
            if (symbol.isEmpty() && keyword.isEmpty() && lowPrice.isEmpty() && highPrice.isEmpty()) { //If no attributes are entered
                for (Investment obj: Portfolio.Investments) {
                    invName = obj.getName();
                    invPrice = Double.parseDouble(obj.getPrice());
                    GUI.searchMessageArea.append("Symbol: " + obj.getSymbol().toUpperCase() + "\nName: " + invName + "\nPrice: $" + invPrice + "\nQuantity: " + obj.getQuantity() + "\nBookvalue: " + obj.getBookValue() + "\n\n");
                }
                return;
            }
            else { //If attributes ARE entered
                if (!keyword.isEmpty()) { //If not empty, check if it's multi or single word
                    if (keyword.contains(" ")) {
                        multipleWord = true;
                    }
                }

                if (!lowPrice.isEmpty() && !highPrice.isEmpty()) {
                    price = lowPrice + "-" + highPrice;
                }
                else if (!lowPrice.isEmpty() && highPrice.isEmpty()) {
                    if (lowPrice.contains("-")) {
                        price = lowPrice;
                    }
                    else {
                        price = lowPrice;
                    }
                }
                else if (lowPrice.isEmpty() && !highPrice.isEmpty()) {
                    if (highPrice.contains("-")) {
                        price = highPrice;
                    }
                    else {
                        price = highPrice;
                    }
                }
                String[] strRange = price.split("-"); //Split the price input to find if an upper price range is given

                if (!price.isEmpty()) { //If price input is not empty
                    if (strRange[0].isEmpty()) { //Check if first index is empty which indicates no price given or no lower limit
                        noLower = true;
                    }
                    if (noLower == false) { //If a lower limit is given then find lower limit
                        lowerPrice = Double.parseDouble(strRange[0]);
                    }
                    if (strRange.length > 1) { //If there is more than one element in the split string array, then intiailize the upperPrice
                        upperPrice = Double.parseDouble(strRange[1]);
                    }
                    else { //If there is only 1 element, then boolean noUpper is true
                        noUpper = true;
                    }
                }
                for (Investment obj: Portfolio.Investments) { //Loop through all stock objects
                    symbolMatch = false;
                    priceMatch = false;
                    nameMatch = false;
                    finalTrue = false;
    
                    invName = obj.getName();
                    invPrice = Double.parseDouble(obj.getPrice());
                    String[] nameWords = invName.split("\\s+");
    
                    if (obj.getSymbol().equalsIgnoreCase(symbol)) { //If symbol of stock object matches inputted symbol, then match is true
                        symbolMatch = true;
                    }
    
                    if (!price.isEmpty()) { //If price range is given
                        if (noUpper == true) { //If upper price limit is not given
                            if (price.contains("-")) { //Check if inputted price contains "-" which indicates price or higher
                                if (lowerPrice <= invPrice) { //If stockPrice is higher than limit from lowerPrice
                                    priceMatch = true;
                                }
                            }
                            else if (!price.contains("-")) { //If price does not contain "-", then check if the price equals stock object price
                                if (lowerPrice == invPrice) {
                                    priceMatch = true;
                                }
                            }
                        }
                        else if (noLower == true) { //Check if lower limit is not given
                            if (price.contains("-")) { //Check if it contains "-" which indicates if a upper limit's given
                                if (upperPrice >= invPrice) { //Check if it fits in range of upperPrice
                                    priceMatch = true;
                                }
                            }
                            else if (!price.contains("-")) { //If it doesn't contain "-" then check if price equals stock price
                                if (upperPrice == invPrice) {
                                    priceMatch = true;
                                }
                            }
                        }
                        else { //Check if the stock object price is in between the lower and upper range
                            if ((lowerPrice <= invPrice) && (upperPrice >= invPrice)) {
                                priceMatch = true;
                            }
                        }
                    }
    
                    for (int i = 0; i < nameWords.length; i++) { //Checking for the keyword and seeing if it's true
                        if (multipleWord == false) { //If only a single word
                            if (nameWords[i].equalsIgnoreCase(keyword)) {
                                nameMatch = true;
                                break;
                            }
                        }
                        if (multipleWord == true) { //if there's multiple words
                            if (keyword.equalsIgnoreCase(obj.getName())) {
                                nameMatch = true;
                            }
                            break;
                        }             
                    }
    
                    if (!symbol.isEmpty()) { //Check if symbol is not empty
                        if (symbolMatch == true) { //If symbol match was found, continue check
                            if (!keyword.isEmpty()) { //Check if keyword/name is not empty
                                if (nameMatch == true) { //If name match was found, do final check for price
                                    if (!price.isEmpty()) { //Check if price is not empty
                                        if (priceMatch == true) {
                                            finalTrue = true;
                                        }
                                    }
                                }
                            }
                            else if (keyword.isEmpty()) { //Check if keyword is not given
                                if (!price.isEmpty()) { //Check if price is not given
                                    if (priceMatch == true) {
                                        finalTrue = true;
                                    }
                                }
                                else if (price.isEmpty()) { //Check if price is empty
                                    finalTrue = true;
                                }
                            }
                        }
                    }
                    if (symbol.isEmpty()) { //Check if symbol is empty
                        if (!keyword.isEmpty()) { //Check if keyword/name is not empty
                            if (nameMatch == true) { //If name match was found, do final check for price
                                if (!price.isEmpty()) { //Check if price is not empty
                                    if (priceMatch == true) {
                                        finalTrue = true;
                                    }
                                }
                                else if (price.isEmpty()) { //If no price is given, then match is true
                                    finalTrue = true;
                                }
                            }
                        }
                        else if (keyword.isEmpty()) { //If keyword input is empty, then check for price input
                            if (!price.isEmpty()) { //If price is not empty, then check if priceMatch was true
                                if (priceMatch == true) {
                                    finalTrue = true;
                                }
                            }
                            else if (price.isEmpty()) { //If price is empty, then match is true
                                finalTrue = true;
                            }
                        }
                    }
    
                    if (finalTrue == true) { //If match is true, then print the attributes of the objects
                        GUI.searchMessageArea.append("Symbol: " + obj.getSymbol().toUpperCase() + "\nName: " + invName + "\nPrice: $" + invPrice + "\nQuantity: " + obj.getQuantity() + "\nBookvalue: " + obj.getBookValue() + "\n\n");
                        noInvFound = false;
                    }
                }
            }
            if (noInvFound == true) {
                GUI.searchMessageArea.setText("There were no investments found according to the search given.\n");
                return;
            }
        }catch (Exception err) {
            GUI.searchMessageArea.setText("An error occurred while trying to search:\n" + err.getMessage());
        }
    }
}