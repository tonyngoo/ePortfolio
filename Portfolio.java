package tngo07_a3.Investments;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class Portfolio is a class that will include the main, which calls the GUI class and runs it.
 * Instructions on how to run the program are specified inside of README.txt, under User Guide (3)
 * The Portfolio class will initialize static ArrayLists and HashMaps which are used inside of other functions.
 * This is so that it does not have to call new ones constantly throughout other files, instead it uses just these ones.
 */
public class Portfolio {

    static Scanner input = new Scanner (System. in);
    protected static ArrayList <Investment> Investments = new ArrayList<Investment>();
    protected static HashMap <String, ArrayList<Integer>> hashMap = new HashMap<String, ArrayList<Integer>>();
    protected static ArrayList <String> symbolList = new ArrayList <String>();
    protected static ArrayList <String> nameList = new ArrayList <String>();

/**
 * In the main method, it will be where the GUI class is run and performed.
 * Main utilizes an ArrayList called Investments which stores all of the Stock and MutualFund investments made.
 * It also includes a HashMap which will find all matching keywords and their indexes, used later inside of the search command.
 * @param args args is not utilized inside of A3, but it its purpose is to get command line inputs
 */
    public static void main(String[] args) { //Main where the program is run
        GUI guiTest = new GUI();
        guiTest.setVisible(true);
    input.close(); //Close scanner
    }

    /**
     * isNumerical is a helper method created which will be used inside of exception handling actionListener methods.
     * It's purpose is to help the program's defensiveness by returning true if it was a numerical input and false
     * if it was not numerical.
     * @param strInput strInput is the string that the user wants to check whether it is numerical or not
     * @return Method will return true if it is numerical, or false if it turns out to be non-numerical
     */
    public static boolean isNumerical (String strInput) {
        try {
            Double.parseDouble(strInput);
            return true;
        }
        catch (NumberFormatException error) {
            return false;
        }
    }

/**
 * getIndex is a helper method that is used inside of buy and sell methods.
 * It should get the index that the word is at and return it.
 * @param str str is used for the name of the keyword.
 * @param hashMap hashMap is used inside of getIndex to store the index
 * @param Investments Investments is used to loop through all of the Investments and find the index of the matched name
 * @return //return will return the index at which the match is found, hence getting its index.
 */
    public static int getIndex (String str, HashMap<String, ArrayList<Integer>> hashMap, ArrayList <Investment> Investments) {
        int index = 0;
        
        for (int i = 0; i < Investments.size(); i++) {
            if (Investments.get(i).getName().equalsIgnoreCase(str)) {
                index = i;
                return index;
            }
        }
        return index;
    }

/**
 * update is a method that utilizes and passes the Investment object ArrayList in its pararmeter.
 * Update begins by looping through all of the Stocks in the investment portfolio and asking for a user input to change the price for each one
 * After finding and changing all of the prices for the Stocks investments, it will then do the same but for MutualFunds.
 * If no investments were found in the portfolio, then it will print that update wasn't possible.
 * If it was successfully updated then it will print likewise.
 * As the method is a void type, it does not return anything.
 * @param Investments Investments will be the object ArrayList utilizes the getter and setter methods inside the method.
 */
    public static void update (ArrayList <Investment> Investments) { //Update command
        String newPrice;
        boolean empty = true;

        for (Investment obj: Investments) {
            if (obj.getType().equalsIgnoreCase("Stock")) {
                System.out.print("Enter a new price for " + obj.getSymbol() + ": $");
                newPrice = input.nextLine(); //Get new price inputs for each object
                obj.setPrice(newPrice); //Set the new price to the object
                empty = false;
            }
            else if (obj.getType().equalsIgnoreCase("MutualFund")) {
                System.out.print("Enter a new price for " + obj.getSymbol() + ": $");
                newPrice = input.nextLine(); //Get new price inputs for each object
                obj.setPrice(newPrice); //Set the new price to the object
                empty = false;
            }
        }
        if (empty == true) {
            System.out.println("\nNo investments can be updated as the portfolio is empty.");
        }
        else {
            System.out.println("\nYour investments have finished being updated.");
        }
    }


/**
 * GetGain is a method that utilizes and passes both the Stocks and MutualFunds arraylists into the parameter
 * GetGain will go through all of the Stocks and MutualFund objects and compute the gain from each and every investment.
 * It should be noted that these are done with accordance to the commission fees (-9.99 for Stocks and -45.00 for MutualFunds).
 * Once the gain of each investment type is calculated, it will combine in totalGain and print this to the user.
 * If the user calls getGain with no investments entered inside the portfolio, then getGain would just give a gain of $0.00,
 * which indicates nothing inside as no gain occurs.
 * As the method is a void type, it will not return anything.
 * @param Investments Investments is an object ArrayList which is required to utilize the getter methods inside of the method.
 */
    public static void getGain(ArrayList <Investment> Investments) { //GetGain command
        try{
            double gainStock = 0;
            double gainMF = 0;
    
            double stockQ = 0;
            double stockPrice = 0;
            double stockBookVal = 0;
            double mfQuantity = 0;
            double mfPrice = 0;
            double mfBookVal = 0;
    
            double totalGain = 0;
            for (Investment obj: Investments) {
                double rounder = 0;
                if (obj.getType().equalsIgnoreCase("Stock")) { //If the current object type is a stock, then it gets the gain for the specific stock
                    String str = obj.getSymbol();
                    stockQ = Double.parseDouble(obj.getQuantity());
                    stockPrice = Double.parseDouble(obj.getPrice());
                    stockBookVal = Double.parseDouble(obj.getBookValue());
                    gainStock = gainStock + ((stockQ * stockPrice) - stockBookVal) - 9.99;
                    rounder = Math.round((((stockQ * stockPrice) - stockBookVal) - 9.99) * 100.0) / 100.0;
                    GUI.gainMessageArea.append("The gain from the Stock investment " + str + " is $" + rounder + "\n");
                }
                else if (obj.getType().equalsIgnoreCase("MutualFund")) { //If the current object type is a MF, then it gets the gain for the specific MF
                    String str = obj.getSymbol();
                    mfQuantity = Double.parseDouble(obj.getQuantity());
                    mfPrice = Double.parseDouble(obj.getPrice());
                    mfBookVal = Double.parseDouble(obj.getBookValue());
                    gainMF = gainMF + ((mfQuantity * mfPrice) - mfBookVal) - 45.00 ;
                    rounder = Math.round((((mfQuantity * mfPrice) - mfBookVal) - 45.00)* 100.0) / 100.0;
                    GUI.gainMessageArea.append("The gain from the Mutual Fund investment " + str + " is $" + rounder + "\n");
                }
            }
            totalGain = gainStock + gainMF; //totalGain calculates all gain from stocks and mutualfunds
            double rounded = Math.round(totalGain * 100.0) / 100.0;
            GUI.gainField.setText("$" + rounded);

        } catch (Exception err) {
            GUI.gainMessageArea.setText("An error occured while trying to calculate the gain:\n" + err.getMessage());
        }
    }

 
/** search is a method that utilizes and passes both the Stocks and MutualFunds Arraylists into the parameter.
 * It begins by taking the 3 inputs: symbol, keyword and price which are used to filter search the existing investments in the portfolio.
 * The program will first check if all 3 inputs are not given. If this is true then it should just print all of the investments as there is no filter.
 * If inputs are given, then it will first go through the Stocks and check the symbol and see if it is empty. If a symbol is given, then it will check
 * if a price and/or keyword is given. Symbol will check if the symbol matches, keyword will check if the name of the investment matches, and price 
 * will check if the investment price matches (which has 3 possible conditions).
 * Once this is completed, it will repeat this process for MutualFunds and print all of the investments that have matched with the filter and their attributes.
 * If no matches are found, then no investments from the portfolio will be printed.
 * @param Investments Investmentsis the Stock object arraylist.
 * @param hashMap hashMap used to filter and search through the keys and find its corressponding indexes.
 */
    public static void search (ArrayList <Investment> Investments, HashMap <String, ArrayList<Integer>> hashMap) { //Search Command
        ArrayList <Integer> matchArr = new ArrayList <Integer>();
        String symbol;
        String keyword;
        String price;
        double upperPrice = 0;
        double lowerPrice = 0;

        String invName;
        Double invPrice;
   
        boolean symbolMatch;
        boolean priceMatch;
        boolean nameMatch;
        boolean finalTrue;
        boolean noUpper = false;
        boolean noLower = false;
    
        System.out.print("Enter symbol: ");
        symbol = input.nextLine();
        System.out.print("Enter keyword: ");
        keyword = input.nextLine();
        System.out.print("Enter price: ");
        price = input.nextLine();

        System.out.println("\nThe following matches were found to the search: ");

        if (symbol.isEmpty() && keyword.isEmpty() && price.isEmpty()) { //If no attributes are entered
            for (Investment obj: Investments) {
                invName = obj.getName();
                invPrice = Double.parseDouble(obj.getPrice());
                System.out.printf("\nSymbol: %s\nName: %s\nPrice: $%.2f\nQuantity: %s\nBookvalue: $%s\n", obj.getSymbol().toUpperCase(), invName, invPrice, obj.getQuantity(), obj.getBookValue());
            }
            return;
        }

        else { //If attributes ARE entered
            if (!keyword.isEmpty()) {
                for (String key: hashMap.keySet()) {
                    if (key.equalsIgnoreCase(keyword)) {
                        matchArr = hashMap.get(key);
                    }
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
            int index = 0;
            for (Investment obj: Investments) { //Loop through all stock objects
                symbolMatch = false;
                priceMatch = false;
                nameMatch = false;
                finalTrue = false;

                invName = obj.getName();
                invPrice = Double.parseDouble(obj.getPrice());

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

                for (int i = 0; i < matchArr.size(); i++) { //Checking for the keyword and seeing if it's true
                    if (matchArr.get(i) == index) {
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
                    System.out.printf("\nSymbol: %s\nName: %s\nPrice: $%.2f\nQuantity: %s\nBookvalue: $%s\n", obj.getSymbol().toUpperCase(), invName, invPrice, obj.getQuantity(), obj.getBookValue());
                }
                index++;
            }
        }
    }
}