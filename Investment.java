package tngo07_a3.Investments;


/**
 * Investment is a parent class that will be used to extend onto the Stock and MutualFund subclasses
 * that were already creatted in A1. It'll combine both Stock and MutualFund types into one arraylist.
 * The difference between this class and the Stock annd MutualFund class is that Investment includes
 * another property for the type.
 * This is required to distinguish whether each investment stored int he program is a stock or mutualfund, or
 * else it won't be possible to categorize them.
 */
class Investment {
    String type;
    String quantity;
    String name;
    String symbol;
    String price;
    String bookValue;
    
    /**
     * Constructor for Investment includes its 6 properties stored for each investment:
     * @param type Type of Investment (stock or mutualfund)
     * @param symbol Investment symbol
     * @param name Investment name
     * @param quantity Quantity of Investments
     * @param price Price of each investment
     * @param bookValue Bookvalue calculated for the investment
     */
    Investment (String type, String symbol, String name, String quantity, String price, String bookValue) {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.bookValue = bookValue;
    }
    /**
     * Mutator for Investment type
     * @param type Changes the type to whatever the code passes in (stock or mutualfund)
     */
    public void setType(String type) { //Setter method for investment type
        this.type = type;
    }
    
    /**
     * Accessor for Investment type
     * @return Returns Stock or MutualFund depending on the type set for the object
     */
    public String getType() { //Getter method for investment type
        return this.type;
    }

    /**
     * Mutator for Investment symbol
     * @param symbol Changes the symbol to whatever the code passes in
     */
    public void setSymbol (String symbol){ //Setter method for symbol
        this.symbol = symbol;
    }

    /**
     * Accessor for Investment symbol
     * @return Returns whatever the Investment object's stored symbol is
     */
    public String getSymbol() { //Getter method for symbol
        return this.symbol;
    }

    /**
     * Mutator for Investment name
     * @param name Changes the Investment's name to whater the code passes in
     */
    public void setName (String name){ //Setter method for name of investment
        this.name = name;
    }

    /**
     * Accessor for Investment name
     * @return Returns whatever the object Investment's name is 
     */
    public String getName() { //Getter method for name of investment
        return this.name;
    }

    /**
     * Mutator for Investment quantity
     * @param quantity Changes the Investment's quantity to whatever the code passes in
     */
    public void setQuantity (String quantity){ //Setter method for quantity
        this.quantity = quantity;
    }

    /**
     * Accessor for Investment quantity
     * @return Returns whatever the quantity is for that Investment's object
     */
    public String getQuantity() { //Getter method for quantity
        return this.quantity;
    }

    /**
     * Mutator for the Investment's price
     * @param price Changes the price of the Investment to whatever is passed in
     */
    public void setPrice (String price){ //Setter method for price
        this.price = price;
    }

    /**
     * Accessor for Investment price
     * @return Returns whatever the Investment object's price is
     */
    public String getPrice() { //Getter method for price
        return this.price;
    }

    /**
     * Mutator for Investment's bookvalue
     * @param bookValue Changes the bookValue after it is computed in the respective methods
     */
    public void setBookValue (String bookValue){ //Setter method for bookvalue
        this.bookValue = bookValue;
    }

    /**
     * Accessor for Investment's bookvalue
     * @return Returns whatever the object's set bookValue is
     */
    public String getBookValue() { //Getter method for bookValue
        return this.bookValue;
    }
    
    /**
     * Helper toString() functoin which will convert the properties in a specific format
     * @return Returns the properties of the Investment object in a specific format, spaced by newlines
     */
    public String convertToString () { //Method toString() which will return the fields in a specific format
        return this.symbol.toUpperCase() + "\n" + this.name + "\n" + this.quantity + "\n" + this.price + "\n" + this.bookValue ;
    }

    /**
     * equals() helper method which will check whether the symbol matches to an existing symbol in the Investment class
     * @param testSymbol The symbol passed in to test whether it exists in the class
     * @return Returns true if a match to an existing symbol is found, or false if no moutch is found
     */
    public boolean isEquals (String testSymbol) { //Equals() method which will return true if the passed string matches with symbol
        if (symbol.equalsIgnoreCase(testSymbol)) {
            return true;
        }
        else {
            return false;
        }

    }
}