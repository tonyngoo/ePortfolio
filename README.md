# ePortfolio - Java

## (1): General Problem to be Solved

The problem at hand is to create an ePortfolio program, implementing a GUI which allows an investor to track stocks and mutualfunds that they buy.
The program allows the user to do 5 different commands:
 - Buy: The user is able to choose a stock or mutualfund and buy a certain amount of it at a specific quantity
 - Sell: The user is able to sell an existing stock or mutualfund for a certain amount of quantity (that does not exceed the amount bought)
 - Update: Changes the prices of whichever investments they want
 - GetGain: Tells the user what the total gain of the portfolio would be if they sold
 - Search: The user is able to enter a symbol, keyword, or price range that allows a filter search for any investment that is owned
The program also includes an exit command which closes the program
The program tries to ensure the best experience for the investor when they are tracking their stocks and mutualfunds.
    
## (2): Assumptions and Limitations

In my solution, I have attempted to code the program as defensively as possible, using several if/else condition statements along with exception handling to deal with as many
bugs/problems as possible. I have made it so that numerical inputs such as price and quantity will prompt invalid when they are not numerical.
With that being said, it is still possible for many limitations to exist as there are just too many cases to test in the code.
It can be assumed that the program is dependent on an input file from the command line. 
When the program does not have a file to read from the command line, then the code will not function and the program will close itself.

Although the program does not break from it, it is assumed that the program runs "update" command with investments already made inside the portfolio.
When it executes "update" without investments, the user is able to press the "next" button but it will not break the code as an exception handler is implemented to prevent it from breaking.
Instead, it will print that an error occurred during the proccess of finding the next investment and will print its exception error.

The main assumption to be made from my program is in the "search" command. It has been defensively coded but it is assumed that the user will want to have upper limits and/or lower limits.
For that reason, it is assumed that if the user wants this, they will have to input "-" after their price for lower and "-" before the price for higher.
For example, if the user wanted to find all investments that are $50.00 and under, then they would use the high price box and input "-50".
Likewise, if the user wanted to find all investments $50.00 and greater, then they would use the low price box and input "50-".


## (3): User Guide

The user is able to build the program by utilizing the Investments package that is inside the zipped submission.
Make sure that the Investments package includes the following files:
 - **Portfolio.java**
 - **Stock.java**
 - **MutualFund.java**
 - **GUI.java**
 - **Investment.java**
 - **buyListener.java**
 - **sellListener.java**
 - **nextListener.java**
 - **prevListener.java**
 - **saveListener.java**
 - **searchListener.java**
 - **typeListener.java**

Main, where the test is performed can be found inside the Portfolio class
To compile the program, the user must be inside of the Investments directory and use the following command in terminal: "javac *.java"
To run the program, the user should exit the present directory by using the command "cd .." twice
and then using the command "java tngo07_a3.Investments.Portfolio" to run the program
Including a ".txt" file will not change anything as it will not read it anyway (this is because Professor Fei Song has stated that it input/output files are "optional").
To test the program, run it and enter inputs for the program to continue running

When the program runs, a GUI window will appear with a welcome display along with a drop-down "Commands" menu with 6 differnet command options which the user can pick from.
The 6 different commands are buy, sell, update, getgain, search and exit
    1) - Buy
            The buy command contains a drop-down menu bar which allows the user to select between a Stock or Mutual Fund. It also contains boxes for the symbol, name, 
            quantity and price for the user to enter into the program. When the user wants to purchase, then they will press the "buy" button located on the right side.
            If the user wants to reset the boxes to buy or change their investment, they can press the "reset" button.
            If the symbol already exists in the stock or mutualfund investment type, then it will use the existing one and ask for a new quantity and price instead.
            If the symbol name already exists but for the alternative type, then it'll print that it was not valid.
            After it successfully bought, the user is able to select a new command from the "Commands" menu or resetting to buy another.

    2) - Sell
            Once the user selects the sell command, it will prompt for the symbol of the investment, quantity, and price as well.
            It is important for the investment to already exist in the portfolio, meaning that the user has already bought shares of this investment.
            If no shares with the inputted symbol exists, then the sell command will not work and prompt to try again later.
            If the investment symbol is found, then it will ask for the quantity and price to sell the investment at.

    3) - Update
            The update command will start at the beginning of the portfolio and prompt for a new price in the box.
            If the user wants to save the updated price, they will press the "save" button. If they want to update a different investment, they can press
            "next" or "prev" buttons to look at other investments in the portfolio.
            If the update command is called with the portfolio EMPTY, then it will output that the user has an empty portfolio and update will not work

    4) - GetGain
            Once the user selects the getGain command, it will tell the user how much the user would gain from selling all of the investments in their portfolio.
            It does this by printing it in a box, and also shows the gains from each individual investment at the bottom.
            If no investments are stored inside the portfolio when getGain is called, then it will say that the user gains $0.00 (gain nothing).

    5) - Search
            The search command will find an investment depending on the attributes that the user wants to filter them by.
            It asks for a symbol, keyword, lower price and higher price to filter through all investments in the portfolio (stocks and mutualfunds).
            The user is able to choose if they want to enter all, some, or none of the filter options given and it will search accordingly.
            Using multiple filters will change the matching as the investment type must match all of the according filters.
            It should be noted that the price range has 3 different meanings. Using it normally will include a range (e.g: 10.00-100.00)
            that indicates its lower and upper price limit. The user could also only include a lower price range (e.g: 10.00-) which would search for
            any price that is above the given range. It could also search for a specific price of an investment (e.g: 10.00)
            Once the search is complete, it will display all of the matched investments.
            If no matches are found, then it will print that no matches were found according to the filters given.


## (4): Test Plan (How Program is Tested for Correctness)

When the user executes the program with an input file in the command line, then a GUI window will appear with the words:

    Output: "Welcome to ePortfolio

            Choose a command from the "Commands" menu above to buy or sell an investment, update prices for all investments, get gain for the portfolio, search for relevant investments, or quit the program.

When the GUI window is started-up, it will include a "Commands" drop-down menu. Clicking on the drop-down menu will show 6 different options.
The following options from the menu are:
    1 - Buy

        When the user tries to purchase an AAPL stock, with the name Apple, 25 quantity at $50, it should display in the "Messages" box:
               Output: "Bookvalue = $1259.99
                         You bought 25 shares of AAPL

                         Please select another option from the "Commands" menu or press "Reset" to buy another."


        When the user purchases an existing investment's symbol, then it will state in the "Messages" box that it bought x "more" quantity:
                Output: "Bookvalue = $2519.98
                         You bought 50 more shares of AAPL

                         Please select another option from the "Commands" menu or press "Reset" to buy another."


        When the user tries to purchase a Mutual Fund with the symbol AAPL after already purchasing it as a stock, it will display invalid in the "Messages" box:
                Output: "Invalid: The symbol that you have entered is a symbol being used in Stock type already.
                         Please enter for the correct type."


        When the user tries to purchase an AAPL stock with 25 quantity at $50, without a name entered, it is not allowed so it should display in the "Messages" box: 
                Output: "You must enter a name... it cannot be empty"
        

        Likewise with the other input properties, it will check for validity of numerical inputs too. When the user tries to enter a non-numeric input for quantity OR price, it
        will print that it was not valid. For example if the user tries to buy an AAPL stock with "five" quantity at the price of "forty", it will display in the "Messages" box:
                Output: "You must enter a numerical quantity...
                         You must enter a numerical price..."
        
        When the user wants to reset the fields, they will press the "reset" button and it will display in the "Messages" box:
                Output: "Reset Complete"


        When the user already has boxes filled in for a Stock and then decides to change its investment type to a Mutual Fund, it should remove all the inputs stored previously
        and reset it to empty.

    2 - Sell

        When the user has already bought AAPL stocks and tries to sell all of them or a couple of them, it will successfully print in the "Messages" box that they were sold
        if the symbol was correct. For example, if the user already has AAPL stocks and sells 25 AAPL stocks at $25.00, then it will print:
                Output: "You have sold 25 shares of AAPL for $615.01


                         Please select another option from the "Commands" menu or press "Reset" to buy another."


        When the user tries to sell an investment that does not exist inside of the portfolio, then it should not work and the display in the "Messages" box is:
            Output: "The investment asdasd was not found so it could not be sold
                     Please re-enter an existing investment or choose another command"


        When the user tries to sell a quantity that exceeds the amount that they own, then it should not work either. For example, if the user bought 25 AAPL stocks and tries to
        sell 35 AAPL stocks, then it should display in the "Messages" box: 
                Output: "The quantity entered to be sold exceeds the amount that you own...
                         Please re-enter a valid quantity."


        Much like the buy command, when the user tries to enter a non-numeric input for quantity OR price, it will print that it was not valid. 
        For example if the user tries to sell an AAPL stock with "five" quantity at the price of "forty", it will display in the "Messages" box:
                Output: "You must enter a numerical quantity...
                         You must enter a numerical price..."


        When the user wants to reset the fields, they will press the "reset" button and it will display in the "Messages" box:
                Output: "Reset Complete"


    3 - Update
        When the user enters a valid numerical price and tries to "save" it, then it will show in the "Messages" box:
                Output: "Price successfully saved!"

        When the user tries to use the "next" button to go to another investment but they do not have any more investments inside the portfolio, then it should not work.
        The "Messages" in the display should print an exception message:
                Output: "An error occured while trying to update:
                         Index 2 out of bounds for length 1"


        When the user tries to enter a non-numerical input for the price, it is not valid and so it should not work. For example, if the user tries to "save" using the price "fifty",
        then the display in the "Messages" box should show:
                Output: "You must enter a numerical price..."

        When the user has multiple investments inside of the portfolio, then it can freely use the "next" and "prev" buttons to look through each investment and change their prices using the "save" button.
        When it is looking at the first investment, the "prev" button should not be visible and when looking at the last investment in the portfolio, the "next" button should not be visible.


    4 - Gain
        When the user selects getGain, it will simply print the total gain in a non-editable box.
        For example, when the user calls for getGain after buying 100 Stocks of AMZN for $50.00, and then updating the price to $80.00, the getGain would give:
                Output: "The gain from the Stock investment AMZN is $2980.02"


        When the user tries to calculate gain without any investments already made in the portfolio, then it should display nothing in the "Individual gains" display box and the
        gain should be set to "$0.0" as nothing is calculated.

    5 - Search
        When the user enters the search command, the user is prompted for 4 inputs: symbol, keyword, lower price and higher price.
        The user is able to filter for specific investments using these properties.

        If the user wants to search for all of the investments in the portfolio, they will enter blank inputs for all 4 boxes.
        For example, when the user loads the .txt given back in A2, then it will display in the "Search results" box:
                Output: "The following matches were found to the search:

                    Symbol: APPL
                    Name: Apple Inc.
                    Price: $142.23
                    Quantity: 500
                    Bookvalue: $55049.99

                    Symbol: SSETX
                    Name: BNY Mellon Growth Fund Class I
                    Price: $42.21
                    Quantity: 450
                    Bookvalue: $23967.00"

        The search will work on single keywords. When the user has investments named "Bank of Canada", "Toronto Bank", and "CIBC Bank",
        and uses the keyword "bank", it will print in the "Search results" box:
                Output: "The following matches were found to the search: 

                         Symbol: BOC
                         Name: Bank of Canada
                         Price: $35.32
                         Quantity: 25
                         Bookvalue: 892.99

                         Symbol: TD
                         Name: Toronto Bank
                         Price: $53.0
                         Quantity: 32
                         Bookvalue: 1705.99

                         Symbol: CIBC
                         Name: CIBC Bank
                         Price: $33.0
                         Quantity: 32
                         Bookvalue: 1065.99"


        The search will also work for mutliple keywords. When the user uses the same investments above and uses the keyword "CIBC Bank", it will print in the "Search results":
                Output: "The following matches were found to the search: 

                         Symbol: CIBC
                         Name: CIBC Bank
                         Price: $33.0
                         Quantity: 32
                         Bookvalue: 1065.99"


        The search also works for multiple filters. For example, when the user uses the same investments above and uses symbol "TD", keyword of "Toronto Bank" and
        low price of 20 and high price of 80, it will print in the "Search results" box:
                Output: "The following matches were found to the search: 

                        Symbol: TD
                        Name: Toronto Bank
                        Price: $53.0
                        Quantity: 32
                        Bookvalue: 1705.99"


        It will not work when the price fields given exceed the price of the investments, for example if the user conducts the same filter search as above but uses the
        low price of 80 and high price of 120, then the "Search results" box will display:
                Output: "There were no investments found according to the search given."


        When the user wants to reset the fields, they will press the "reset" button and it will display in the "Messages" box:
                Output: "Reset Complete" 


    6 - Exit
        When the user presses the "Exit Program" button, it will simply system exit, which will close down the GUI window and stop the program.
        As Professor Song has stated that input/output files are optional, I have chosen not to implement an output method upon exit.


## (5): Possible Improvements

There are always ways to improve my own code, but it often takes time (which not many students have the luxury of at times).
If I had more time, I would figure out a better and more simplistic algorithm to code the search method.
My search command is completely functional and works but it is not the neatest in its coding sense. At first glance, it may seem
overwhleming with the amount of lines of codes in it, as well as the number of if statements that are utilized.

As always, if there were more time then I would have liked to cover every single invalid input possible. Although I have covered almost every single one possible, it is always possible
for more to arise when there are so many possible routes and paths that the user can take and input.

If there was more time, then I would have liked to create an additional case for when the user starts the program without an input file to read. For example, if that happened the
user would be prompted a textpane that said "no file was read, do you want to input a file?: " which allowed the user to read a file from the GUI window.

I would also have liked to implement a input/output for .txt files if there were more time to do so.
