package tngo07_a3.Investments;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**The Stock class is a subclass that extends from the Investment class.
 * It is used to store all the stock investment types that are bought inside the program.
 * The Stock class includes methods used in the menu such as buy and sell as well which
 * implements an Investment class and also a HashMap.
 */
public class Stock extends Investment{
    String symbol;
    String name;
    String quantity;
    String price;
    String bookValue;
    static Scanner input = new Scanner (System. in);

    /**
     * The Stock constructor uses 6 properties, type, symbol, name, quantity, price, and bookValue. It holds all Stock investments with these properties for each object.
     * @param type Investment's type
     * @param symbol Investment's symbol
     * @param name Name of Investment
     * @param quantity Quantity of the Investment
     * @param price Price held for Investment
     * @param bookValue Bookvalue from the investment
     */
    public Stock (String type, String symbol, String name, String quantity, String price, String bookValue) {
        super(type, symbol, name, quantity, price, bookValue);
    }  
    
    /**
     * Mutator for Stock symbol
     * @param symbol Changes the symbol to whatever the code passes in
     */
    public void setStockSymbol (String symbol){ //For symbols
        this.symbol = symbol;
    }

    /**
     * Accessor for Stock symbol
     * @return Returns whatever the object's symbol is
     */
    public String getStockSymbol() {
        return this.symbol;
    }

    /**
     * Mutator for Stock name
     * @param name Changes the Stock's name to whatever the code passes in
     */
    public void setStockName (String name){ //For names
        this.name = name;
    }

    /**
     * Accessor for Stock name
     * @return Returns whatever the object's name is
     */
    public String getStockName() {
        return this.name;
    }

    /**
     * Mutator for Stock quantity
     * @param quantity Changes the quantity to whatever the code passes in
     */
    public void setStockQuantity (String quantity){ //For quantity
        this.quantity = quantity;
    }

    /**
     * Accessor for Stock quantity
     * @return Returns whatever the Stock quantity is
     */
    public String getStockQuantity() {
        return this.quantity;
    }

    /**
     * Mutator for Stock price
     * @param price Changes the price to whatever the code passes in
     */
    public void setStockPrice (String price){ //For price
        this.price = price;
    }

    /**
     * Accessor for Stock price
     * @return Returns whatever the object's price is
     */
    public String getStockPrice() {
        return this.price;
    }

    /**
     * Mutator for Stock bookValue
     * @param bookValue Changes the bookValue to whatever the code passes in
     */
    public void setStockbookValue (String bookValue){ //For bookvalue
        this.bookValue = bookValue;
    }

    /**
     * Accessor for Stock booKValue
     * @return Returns whatever the object's bookValue is
     */
    public String getStockbookValue() {
        return this.bookValue;
    }

    /**
     * Helper toString() method utilized for the object to convert it to a specific format.
     * @return returns the object in a specific formatted string, with its symbol, name, quantity, price, and bookvalue spaced by a newline
     */
    public String convertToString () {
        return this.symbol.toUpperCase() + "\n" + this.name + "\n" + this.quantity + "\n" + this.price + "\n" + this.bookValue ;
    }
    
    /**
     * equals() method which will take whatever is passed in for the symbol and check if it matches the constructor's symbol.
     * @return returns true if the symbols equal, and will return false if the symbols do not equal.
     */
    public boolean isEquals (String testSymbol) {
        if (symbol.equalsIgnoreCase(testSymbol)) {
            return true;
        }
        else {
            return false;
        }

    }

/**
 * Inside the Stock class, includes a method called "buy". This method is used and called upon everytime the user wants to specifically buy a stock.
 * It begins by asking the user for a symbol to check if a pre-existing investment exists for that symbol.
 * If it already exists, then it would ask for a new quanttiy and price to replace it in the ArrayList Investment.
 * if it doesn't already exist then it would just ask for a nam, price, quantity, and add this new object into the Investment ArrayList as a "new Stock".
 * If the symbol that was entered exists, but exists for a mutual fund investment type then the method will stop and return back to the menu as it cannot be bought.
 * Buying a stock is different from mutual funds since the commission fee when buying a stock is $9.99 which is diffeernt from a MutualFund's $45.00
 * After finishing adding, then it will loop through to update the HashMap which will be utilized later on in the search command.
 * The buy method is a void type since it does not return any values and is only modifying the ArrayList and HashMap.
 * The param's passed inside the method are Investment and the HashMap as they need to be modified.
 * @param Investments Investments is used in the buy method to loop through the Investments objects and to utilize the get and set methods
 * @param hashMap HashMap is used inside of the buy method specifically to add a key/index to the HashMap if a new investment is made.
 */
    public static void buy(ArrayList <Investment> Investments, HashMap <String, ArrayList<Integer>> hashMap) { //Buy command for stock arraylist
        String symbol;
        String quantity;
        String price;
        String name;
        double bookVal = 0;
        double oldbookVal = 0;
        double bookValComm = 0;
        int newQuantity = 0;

        System.out.print("Enter Symbol: ");
        symbol = input.nextLine();

        for (Investment obj: Investments) { //Loop through all of the objects in the arraylist
            if (obj.getType().equalsIgnoreCase("stock")) {
                if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if symbol equals inputted symbol, if it equals then enter new quantity & price
                    System.out.print("Enter new quantity: ");
                    quantity = input.nextLine();
                    if (Integer.parseInt(quantity) <= 0) {
                        System.out.println("Invalid quantity entered.\nPlease try again later\n");
                        return;
                    }
                    newQuantity = Integer.parseInt(quantity) + (Integer.parseInt(obj.getQuantity()));
                    obj.setQuantity(Integer.toString(newQuantity));
    
                    System.out.print("Enter new price: $");
                    price = input.nextLine();
                    if (Double.parseDouble(price) <= 0) {
                        System.out.println("Invalid price entered.\nPlease try again later\n");
                        return;
                    }
                    obj.setPrice(price); ////If an existing version exists, set new price
    
                    oldbookVal = Double.parseDouble(obj.getBookValue());
                    bookVal = oldbookVal + (Double.parseDouble(quantity) * Double.parseDouble(price));
                    bookValComm = bookVal + 9.99;
                    System.out.printf("\nBookvalue = $%.2f", bookValComm);
                    obj.setBookValue(Double.toString(bookValComm));
                    System.out.println("\nYou bought " + quantity + " more shares of " + symbol.toUpperCase());
                    return;
                }
            }
            else if (obj.getType().equalsIgnoreCase("MutualFund")) {
                if (obj.getSymbol().equalsIgnoreCase(symbol)) {
                    System.out.println("Invalid: The symbol that you have entered is a symbol being used in Mutual Fund type already.\nPlease try again later.\n");
                    return;
                }
            }
        } //If the loop finishes and an existing symbol isn't found, then enter new attributes for a new investment
        System.out.print("Enter Name: ");
        name = input.nextLine();
        System.out.print("Enter Quantity: ");
        quantity = input.nextLine();
        if (Integer.parseInt(quantity) <= 0) {
            System.out.println("Invalid quantity entered.\nPlease try again later\n");
            return;
        }
        System.out.print("Enter Price: $");
        price = input.nextLine();
        if (Double.parseDouble(price) <= 0) {
            System.out.println("Invalid price entered.\nPlease try again later\n");
            return;
        }
        bookVal = (Double.parseDouble(quantity) * (Double.parseDouble(price))) + 9.99;
        System.out.println("\nBookvalue = $" + bookVal);
        Investments.add (new Stock ("Stock", symbol, name, quantity, price, Double.toString(bookVal))); //Add the investment into the arraylist
        System.out.println("You bought " + quantity + " shares of " + symbol.toUpperCase());
        hashMap.clear();
        for (Investment obj: Investments) {
            ArrayList<Integer> values = new ArrayList <Integer>();
            String [] nameArr = obj.getName().split("\\s+");
            for (int i = 0; i < nameArr.length; i++) {
                if (hashMap.get(nameArr[i]) != null) {
                    values = hashMap.get(nameArr[i]);
                }
                else {
                    values = new ArrayList <Integer>();
                }
                Integer indexAt = Portfolio.getIndex(obj.getName(), hashMap, Investments);
                values.add(indexAt);
                hashMap.put(nameArr[i], values);
            }
        }
    }

/**
 * sell in the Stock class is a method that is used and called upon when the user wants to sell specifically a Stock investment.
 * It begins by asking for a symbol input, which will be checked if the investment even exists inside the portfolio.
 * If no investments with that symbol are found, then it will stop the method.
 * If the investment is found, then it should prompt the user for quantity, price and will compute it depending on if it is partially or fully selling.
 * If the quantity entered exceeds the amount owned in the portfolio, then the investment won't be sold as that is not possible.
 * If it is partially sold, then it will set a new quantity, price, and bookvalue inside of that arraylist's object.
 * If it is fully sold, then it will remove the object entirely from the Investment arraylist.
 * Also, when it is fully sold it will modify the hashtable so the indexes will be adjusted and then the index itself from the HashMap.
 * The sell method is a void type since it does not return any values and is only modifying the ArrayList and HashMap.
 * The param's passed inside the method are Investment and the HashMap as they need to be modified.
 * @param Investments Investments is used in sell to loop through all of the Investments objects and to utilize the set and get methods.
 * @param hashMap HashMap is utilized in sell method specifically to remove the key and index when an investment is fully sold.
 */
public static void sell(ArrayList <Investment> Investments, HashMap <String, ArrayList<Integer>> hashMap) { //Sell command for stocks
    String symbol;
    String quantity;
    String price;

    String totalQuantity;
    double payment = 0;
    double bookVal = 0;
    double bookValSell = 0;
    Integer quantityAfter = 0;
    System.out.print("Enter Symbol: ");
    symbol = input.nextLine();
    for (Investment obj: Investments) { //Loop through all objects in the stocks arraylist
        if (obj.getType().equalsIgnoreCase("Stock")) {
            if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if an existing version of the inputted symbol exists
                System.out.print("Enter Quantity: ");
                quantity = input.nextLine();
                if (Integer.parseInt(quantity) <= 0) {
                    System.out.println("Invalid quantity entered.\nPlease try again later\n");
                    return;
                }
                System.out.print("Enter Price: $");
                price = input.nextLine();
                if (Double.parseDouble(price) <= 0) {
                    System.out.println("Invalid price entered.\nPlease try again later\n");
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
                    System.out.printf("\nYou have sold %s shares of %s for $%.2f\n", quantity, symbol.toUpperCase(), payment);
                    return;
                }
                else if ((Integer.parseInt(totalQuantity)) == (Integer.parseInt(quantity))) { //If quantity equals (FULLY)
                    payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 9.99;
                    System.out.printf("You have sold %s shares of %s for $%.2f\n", quantity, symbol.toUpperCase(), payment);
                    Investments.remove(obj);
                    hashMap.clear();
                    for (Investment objects: Investments) {
                        ArrayList<Integer> values = new ArrayList <Integer>();
                        String [] nameArr = objects.getName().split("\\s+");
                        for (int i = 0; i < nameArr.length; i++) {
                            if (hashMap.get(nameArr[i]) != null) {
                                values = hashMap.get(nameArr[i]);
                            }
                            else {
                                values = new ArrayList <Integer>();
                            }
                            Integer indexAt = Portfolio.getIndex(objects.getName(), hashMap, Investments);
                            values.add(indexAt);
                            hashMap.put(nameArr[i], values);
                        }
                    }
                    return;
                }
                else { //If quantity is not valid as amount entered exceeds amount owned
                    System.out.print("The quantity entered to be sold exceeds the amount that you own. Please try again later\n");
                    return;
                }
            }
        }
    }
    System.out.print("The investment " + symbol + " was not found so it could not be sold\n");
}
}