package tngo07_a3.Investments;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**The MutualFund class is a subclass that extends the Investment class.
 * It is used to store all of the MutualFund type investments that are made inside the program.
 */
public class MutualFund extends Investment{ //MutualFund class
    private String symbol;
    private String name;
    private String quantity;
    private String price;
    private String bookValue;
    static Scanner input = new Scanner (System. in);

    /**
     * Constructor used for MutualFund which holds 6 properties: type, symbol, name, quantity, price, booKValue. It holds all Mutual Fund investments with these properties for each object.
     * @param type Investment type
     * @param symbol Invesment's symbol
     * @param name Investment's name
     * @param quantity Investment's quantity
     * @param price Price of Investment
     * @param bookValue Bookvalue from the investment
     */
    public MutualFund (String type, String symbol, String name, String quantity, String price, String bookValue) {
        super(type, symbol, name, quantity, price, bookValue);
    }  

    /**
     * Mutator method for Mutual Fund symbol
     * @param symbol Changes the symbol to whatever the user passes in
     */
    public void setMFSymbol (String symbol){ //setter for the symbol
        this.symbol = symbol;
    }

    /**
     * Accessor method for symbol
     * @return Returns whatever the symbol is
     */
    public String getMFSymbol() { //Getter method for symbol
        return this.symbol;
    }

    /**
     * Mutator method for Mutual Fund name
     * @param name Changes the name to whatever the user passes in
     */

    public void setMFName (String name){ //Setter for name
        this.name = name;
    }

    /**
     * Accessor method for name
     * @return Returns whatever the name is
     */
    public String getMFName() { //Getter for name
        return this.name;
    }

    /**
     * Mutator method for Mutual Fund quantity
     * @param quantity Changes the quantity to whatever the user passes in
     */
    public void setMFQuantity (String quantity){ //For quantity
        this.quantity = quantity;
    }

    /**
     * Accessor method for Mutual Fund quantity
     * @return Returns whatever the quantity is
     */
    public String getMFQuantity() { //Getter for quantity
        return this.quantity;
    }

    /**
     * Mutator method for Mutual Fund Price
     * @param price Changes the price to whatever the user passes in
     */
    public void setMFPrice (String price){ //Setter for price
        this.price = price;
    }

    /**
     * Accessor method for price
     * @return Returns whatever the price is
     */
    public String getMFPrice() { //Getter for price
        return this.price;
    }

    /**
     * Mutator method for bookValue
     * @param bookValue Returns whatever the bookValue is
     */
    public void setMFbookValue (String bookValue){ //Set bookvalue
        this.bookValue = bookValue;
    }

    /**
     * Accessor method for bookValue
     * @return Returns whatever the bookValue is
     */
    public String getMFbookValue() { //Getter for bookvalue
        return this.bookValue;
    }

    /**
     * Helper method toString() which will convert the constructor properties in a specific format
     * @return returns the name quantity price and bookValue is a specific format.
     */
    public String convertToString () { //toString() method to convert in a specific format
        return this.symbol.toUpperCase() + "\n" + this.name + "\n" + this.quantity + "\n" + this.price + "\n" + this.bookValue ;
    }

    /**
     * equals() helper method which will essentially check if the symbol is equal to the symbol inside the constructor
     * @return returns true if equals, false if not equal
     */
    public boolean isEquals (String testSymbol) { //equals() method which can return true if equals, false if not
        if (symbol.equalsIgnoreCase(testSymbol)) {
            return true;
        }
        else {
            return false;
        }

    }
/**
 * Inside the MutualFund class, includes a method called "buy". This method is used and called upon everytime the user wants to specifically buy a MutualFund.
 * It begins by asking the user for a symbol to check if a pre-existing investment exists for that symbol.
 * If it already exists, then it would ask for a new quanttiy and price to replace it in the ArrayList Investment.
 * if it doesn't already exist then it would just ask for a nam, price, quantity, and add this new object into the Investment ArrayList as a "new MutualFund".
 * If the symbol that was entered exists, but exists for a stock investment type then the method will stop and return back to the menu as it cannot be bought.
 * Buying a mutualfund is different from a stock since the commission fee when buying a MutualFund is $45.00 which is diffeernt from a Stock's $9.99
 * After finishing adding, then it will loop through to update the HashMap which will be utilized later on in the search command.
 * The buy method is a void type since it does not return any values and is only modifying the ArrayList and HashMap.
 * The param's passed inside the method are Investment and the HashMap as they need to be modified.
 * @param Investments Investments is used inside of the buy method to loop through all of the Investment objects in order to get and set fields.
 * @param hashMap HashMap is included in the parameter of buy as it will add keys and indexes to the HashmMap when buying.
 */
    public static void buy(ArrayList <Investment> Investments, HashMap <String, ArrayList<Integer>> hashMap) { //Buy command for mutual funds arraylist
        String symbol;
        String quantity;
        String price;
        String name;
        double bookVal = 0;
        double oldbookVal = 0;
        int newQuantity = 0;

        System.out.print("Enter Symbol: ");
        symbol = input.nextLine();
        for (Investment obj: Investments) { //Loop through all objects in the mutualfund arraylist
            if (obj.getType().equalsIgnoreCase("MutualFund")) {
                if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if the inputted symbol exists in the arraylist, if it exists then put new quantity and price
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
                    obj.setPrice(price); //If an existing version exists, set new price

                    oldbookVal = Double.parseDouble(obj.getBookValue());
                    bookVal = oldbookVal + (Double.parseDouble(quantity) * Double.parseDouble(price));
                    System.out.printf("\nBookvalue = $.2f", bookVal);
                    obj.setBookValue(Double.toString(bookVal));
                    System.out.println("\nYou bought " + quantity + " more shares of " + symbol.toUpperCase());
                    return;
                }
            }
            else if (obj.getType().equalsIgnoreCase("Stock")) {
                if (obj.getSymbol().equalsIgnoreCase(symbol)) {
                    System.out.println("Invalid: The symbol that you have entered is a symbol being used in Stock type already.\nPlease try again later.\n");
                    return;
                }
            }
        } //If loop has finished and an existing symbol isn't found, input for new attributes for a new investment
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
        bookVal = (Double.parseDouble(quantity) * (Double.parseDouble(price)));
        System.out.println("\nBookvalue = $" + bookVal);
        Investments.add (new MutualFund ("MutualFund", symbol, name, quantity, price, Double.toString(bookVal)));
        System.out.println("You bought " + quantity + " shares of " + symbol.toUpperCase());
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
    }

/**
 * sell in the MutualFund class is a method that is used and called upon when the user wants to sell specifically a MutualFund investment.
 * It begins by asking for a symbol input, which will be checked if the investment even exists inside the portfolio.
 * If no investments with that symbol are found, then it will stop the method.
 * If the investment is found, then it should prompt the user for quantity, price and will compute it depending on if it is partially or fully selling.
 * If the quantity entered exceeds the amount owned in the portfolio, then the investment won't be sold as that is not possible.
 * If it is partially sold, then it will set a new quantity, price, and bookvalue inside of that arraylist's object.
 * If it is fully sold, then it will remove the object entirely from the Investment arraylist.
 * Also, when it is fully sold it will modify the hashtable so the indexes will be adjusted and then the index itself from the HashMap.
 * The sell method is a void type since it does not return any values and is only modifying the ArrayList and HashMap.
 * The param's passed inside the method are Investment and the HashMap as they need to be modified.
 * @param Investments Investments is used inside of sell method to loop through the Investments objects and to utilize the set and get methods.
 * @param hashMap HashMap is used inside of sell to remove the key and index when an investment is fully sold
 */
    public static void sell(ArrayList <Investment> Investments, HashMap <String, ArrayList<Integer>> hashMap) { //Sell command for mutual funds
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
        for (Investment obj: Investments) { //Loop through all objects in mutualfund arraylist
            if (obj.getType().equalsIgnoreCase("MutualFund")) {
                if (obj.getSymbol().equalsIgnoreCase(symbol)) { //Check if the symbol inputted exists in the arraylist
                    System.out.print("Enter Quantity: ");
                    quantity = input.nextLine();
                    if (Integer.parseInt(quantity) <= 0) {
                        System.out.println("Invalid quantity entered.\nPlease try again later\n");
                        return;
                    }
                    System.out.print("Enter Price: $");
                    price = input.nextLine();
                    if (Double.parseDouble(price) <= 0) {
                        System.out.println("Invalid quantity entered.\nPlease try again later\n");
                        return;
                    }
                    totalQuantity = obj.getQuantity();
                    bookVal = Double.parseDouble(obj.getBookValue());
                    payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 45;

                    if ((Integer.parseInt(totalQuantity)) > (Integer.parseInt(quantity))) { //If the quantity owned is more than quantity being sold (PARTIAL)
                        bookValSell = bookVal * ((Double.parseDouble(quantity)) /  (Double.parseDouble(totalQuantity))); //Find bookValue for selling
                        bookVal = bookVal - bookValSell;
                        quantityAfter = (Integer.parseInt(totalQuantity) - (Integer.parseInt(quantity)));
                        obj.setPrice(price); //Set the new price
                        obj.setBookValue(Double.toString(bookVal));
                        obj.setQuantity(Integer.toString(quantityAfter));
                        System.out.printf("\nYou have sold %s shares of %s for $%.2f\n", quantity, symbol.toUpperCase(), payment);
                        return;
                    }
                    else if ((Integer.parseInt(totalQuantity)) == (Integer.parseInt(quantity))) { //If quantity equals quantity being sold (FULLY)
                        payment = (Double.parseDouble(quantity)) * (Double.parseDouble(price)) - 45;
                        System.out.printf("You have sold %s shares of %s for $%.2f\n", quantity, symbol.toUpperCase(), payment);
                        Investments.remove(obj); //Remove the object from the arraylist
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
                    else { //If quantity exceeds quantity owned, then it is invalid
                        System.out.print("The quantity entered to be sold exceeds the amount that you own. Please try again later\n");
                        return;
                    }
                }
            }
        }
        System.out.print("The investment " + symbol + " was not found so it could not be sold\n");
    }


}