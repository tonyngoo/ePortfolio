package tngo07_a3.Investments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI classs is where the GUI is created with all its frame, textpane, textfields, combobox, buttons, and more. It creates these properties of the GUI
 * and then either makes them visible or invisible. The GUI class also has an actionListener method which corresponds to the JMenuBar created and takes its action.
 * More instructions on how to run and utilize the program is specified in README.txt under User Guide (3)
 */
public class GUI extends JFrame implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    JPanel panel = new JPanel();

    JMenuBar menuBar = new JMenuBar();

    JMenu commandMenu = new JMenu("Commands");
    JMenuItem buyMenuItem = new JMenuItem("Buy Investment");
    JMenuItem sellMenuItem = new JMenuItem("Sell Investment");
    JMenuItem updateMenuItem = new JMenuItem("Update Investment prices");
    JMenuItem gainMenuItem = new JMenuItem("Get gain of portfolio");
    JMenuItem searchMenuItem = new JMenuItem("Search for investments");
    JMenuItem exitMenuItem = new JMenuItem("Exit program");

    //Textpanes
    protected static JTextPane contentPane = new JTextPane();
    protected static JTextPane buyStockPane = new JTextPane();
    protected static JTextPane buyMFPane = new JTextPane();
    protected static JTextPane sellPane = new JTextPane();
    protected static JTextPane updatePane = new JTextPane();
    protected static JTextPane gainPane = new JTextPane();
    protected static JTextPane searchPane = new JTextPane();

    protected static String[] typeStrings = {"Stock", "Mutual Fund"};
    protected static JComboBox<String> buyTypeList = new JComboBox<>(typeStrings);
    protected static JComboBox<String> sellTypeList = new JComboBox<>(typeStrings);

    //Buy textfields
    protected static JTextField buyTypeField = new JTextField();
    protected static JTextField buySymbolField = new JTextField();
    protected static JTextField buyNameField = new JTextField();
    protected static JTextField buyQuantityField = new JTextField();
    protected static JTextField buyPriceField = new JTextField();
    
    //Sell textfields
    protected static JTextField sellSymbolField = new JTextField();
    protected static JTextField sellQuantityField = new JTextField();
    protected static JTextField sellPriceField = new JTextField();

    //Update textfields
    protected static JTextField updateSymbolField = new JTextField();
    protected static JTextField updateNameField = new JTextField();
    protected static JTextField updatePriceField = new JTextField();

    //Gain textfields
    protected static JTextField gainField = new JTextField();

    //Search textfields
    protected static JTextField searchSymbolField = new JTextField();
    protected static JTextField searchNameField = new JTextField();
    protected static JTextField lowPriceField = new JTextField();
    protected static JTextField highPriceField = new JTextField();

    //All of the message and scrollbars
    protected static JTextArea buyMessageArea = new JTextArea("");
    protected static JScrollPane buyScrolledText = new JScrollPane(buyMessageArea);
    protected static JTextArea sellMessageArea = new JTextArea("");
    protected static JScrollPane sellScrolledText = new JScrollPane(sellMessageArea);
    protected static JTextArea gainMessageArea = new JTextArea("");
    protected static JScrollPane gainScrolledText = new JScrollPane(gainMessageArea);
    protected static JTextArea updateMessageArea = new JTextArea("");
    protected static JScrollPane updateScrolledText = new JScrollPane(updateMessageArea);
    protected static JTextArea searchMessageArea = new JTextArea("");
    protected static JScrollPane searchScrolledText = new JScrollPane(searchMessageArea);

    //Initializing all buttons and their respective names
    protected static JButton buyStockButton = new JButton("Buy");
    protected static JButton buyMFButton = new JButton("Buy");
    protected static JButton sellButton = new JButton("Sell");
    protected static JButton resetButton = new JButton("Reset");
    protected static JButton sellResetButton = new JButton("Reset");
    protected static JButton searchResetButton = new JButton("Reset");
    protected static JButton nextButton = new JButton("Next");
    protected static JButton prevButton = new JButton("Prev");
    protected static JButton saveButton = new JButton("Save");
    protected static JButton searchButton = new JButton("Search");

    //Button type flags to distinguish if it's stock type or a mutualfund
    protected static String buyType = "";
    protected static String buttonType = "";
    
    //Counter used in update to indicate which index it is looking at
    protected static Integer updateCounter = 0;

    Font contentFont = new Font("Comic Sans", Font.BOLD,28);
    Font optFont = new Font("Comic Sans", Font.BOLD, 20);
    Font gainFont = new Font("Comic Sans", Font.BOLD, 16);

    /**
     * Inside the public GUI, it creates the GUI and sets the properties of it as visible or invisible.
     * It also includes an ActionListener which will take in the user action from the JMenuBar
     */
    public GUI() {
        super("ePortfolio");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panel);
        panel.setLayout(null);

        buyMenuItem.addActionListener(this);
        commandMenu.add(buyMenuItem);

        sellMenuItem.addActionListener(this);
        commandMenu.add(sellMenuItem);
        
        updateMenuItem.addActionListener(this);
        commandMenu.add(updateMenuItem);

        gainMenuItem.addActionListener(this);
        commandMenu.add(gainMenuItem);

        searchMenuItem.addActionListener(this);
        commandMenu.add(searchMenuItem);

        exitMenuItem.addActionListener(this);
        commandMenu.add(exitMenuItem);

        menuBar.add(commandMenu);
        setJMenuBar(menuBar);

        //Content pane or the welcome page when GUI launches
        contentPane.setBounds(0,0,WIDTH,HEIGHT);
        contentPane.setVisible(true);
        contentPane.setEditable(false);
        contentPane.setFont(contentFont);
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setText("\n\n\nWelcome to ePortfolio\n\n\nChoose a command from the \"Commands\" menu above to buy or sell\nan investment, update prices for all investments, get gain for the\nportfolio, search for relevant investments, or quit the program.");
        panel.add(contentPane);

        //Buy command pane when a "buy" command is done
        buyStockPane.setBounds(0, 0, WIDTH, HEIGHT);
        buyStockPane.setVisible(false);
        buyStockPane.setEditable(false);
        buyStockPane.setFont(optFont);
        buyStockPane.setBackground(Color.LIGHT_GRAY);
        buyStockPane.setText("Buying an investment\n\n\tType:\n\n\tSymbol:\n\n\tName:\n\n\tQuantity:\n\n\tPrice:\n\n\n\n\nMessages");
        panel.add(buyStockPane);

        buyMFPane.setBounds(0, 0, WIDTH, HEIGHT);
        buyMFPane.setVisible(false);
        buyMFPane.setEditable(false);
        buyMFPane.setFont(optFont);
        buyMFPane.setBackground(Color.LIGHT_GRAY);
        buyMFPane.setText("Buying an investment\n\n\tType:\n\n\tSymbol:\n\n\tName:\n\n\tQuantity:\n\n\tPrice:\n\n\n\n\nMessages");
        panel.add(buyMFPane);

        //Sell command pane when a "sell" command is done
        sellPane.setBounds(0, 0, WIDTH, HEIGHT);
        sellPane.setVisible(false);
        sellPane.setEditable(false);
        sellPane.setFont(optFont);
        sellPane.setBackground(Color.LIGHT_GRAY);
        sellPane.setText("Selling an investment\n\n\tSymbol:\n\n\tQuantity:\n\n\tPrice:\n\n\n\n\n\n\n\n\nMessages");
        panel.add(sellPane);

        //getGain command pane when a "getGain" command is done
        gainPane.setBounds(0, 0, WIDTH, HEIGHT);
        gainPane.setVisible(false);
        gainPane.setEditable(false);
        gainPane.setFont(optFont);
        gainPane.setBackground(Color.LIGHT_GRAY);
        gainPane.setText(" Getting total gain\n\n\tTotal gain\n\n\n\nIndividual gains");
        panel.add(gainPane);

        //update command pane when a "UPDATE" command is done
        updatePane.setBounds(0, 0, WIDTH, HEIGHT);
        updatePane.setVisible(false);
        updatePane.setEditable(false);
        updatePane.setFont(optFont);
        updatePane.setBackground(Color.LIGHT_GRAY);
        updatePane.setText(" Updating investments\n\n\tSymbol:\n\n\tName:\n\n\tPrice:\n\n\n\n\n\nMessages");
        panel.add(updatePane);

        //search command pane when a "SEARCH" command is done
        searchPane.setBounds(0, 0, WIDTH, HEIGHT);
        searchPane.setVisible(false);
        searchPane.setEditable(false);
        searchPane.setFont(optFont);
        searchPane.setBackground(Color.LIGHT_GRAY);
        searchPane.setText(" Searching investments\n\n\tSymbol:\n\n\tName\n\tkeywords\n\n\tLow price:\n\n\tHigh price:\n\n\n\nSearch results");
        panel.add(searchPane);

        //Fields, buttons, scrollbar, Combobox for BUY command
        buySymbolField.setBounds(200, 100, 250, 40);
        buySymbolField.setVisible(false);
        buyStockPane.add(buySymbolField);

        buyNameField.setBounds(200, 150, 350, 40);
        buyNameField.setVisible(false);
        buyStockPane.add(buyNameField);

        buyQuantityField.setBounds(200, 200, 250, 40);
        buyQuantityField.setVisible(false);
        buyStockPane.add(buyQuantityField);

        buyPriceField.setBounds(200, 250, 250, 40);
        buyPriceField.setVisible(false);
        buyStockPane.add(buyPriceField);

        buyStockButton.setBounds(750, 200, 100, 40);
        buyStockButton.setVisible(false);
        buyStockButton.addActionListener(new buyListener());
        buyStockPane.add(buyStockButton);

        buyMFButton.setBounds(750, 200, 100, 40);
        buyMFButton.setVisible(false);
        buyMFButton.addActionListener(new buyListener());

        resetButton.setBounds(750, 100, 100, 40);
        resetButton.setVisible(false);
        resetButton.addActionListener(this);
        buyStockPane.add(resetButton);

        buyScrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        buyScrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buyScrolledText.setBounds(5,435,WIDTH-30,290);
        buyScrolledText.setVisible(false);
        buyMessageArea.setEditable(false);
        buyStockPane.add(buyScrolledText);

        buyTypeList.setSelectedIndex(0);
        buyTypeList.addActionListener(new typeListener());
        buyTypeList.setSize(250,40);
        buyTypeList.setLocation(200,50);
        buyTypeList.setVisible(true);
        buyStockPane.add(buyTypeList);

        //Fields, buttons, scrollbar, for SELL command
        sellSymbolField.setBounds(200, 50, 250, 40);
        sellSymbolField.setVisible(false);
        sellPane.add(sellSymbolField);

        sellQuantityField.setBounds(200, 100, 250, 40);
        sellQuantityField.setVisible(false);
        sellPane.add(sellQuantityField);

        sellPriceField.setBounds(200, 150, 250, 40);
        sellPriceField.setVisible(false);
        sellPane.add(sellPriceField);

        sellButton.setBounds(750, 200, 100, 40);
        sellButton.setVisible(false);
        sellButton.addActionListener(new sellListener());
        sellPane.add(sellButton);

        sellResetButton.setBounds(750, 100, 100, 40);
        sellResetButton.setVisible(false);
        sellResetButton.addActionListener(this);
        sellPane.add(sellResetButton);

        sellScrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sellScrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sellScrolledText.setBounds(5,435,WIDTH-30,290);
        sellScrolledText.setVisible(false);
        sellMessageArea.setEditable(false);
        sellPane.add(sellScrolledText);

        //Fields, buttons, scrollbar, for UPDATE command
        updateSymbolField.setBounds(200, 50, 250, 40);
        updateSymbolField.setFont(gainFont);
        updateSymbolField.setVisible(false);
        updateSymbolField.setEditable(false);
        updatePane.add(updateSymbolField);

        updateNameField.setBounds(200, 100, 350, 40);
        updateNameField.setFont(gainFont);
        updateNameField.setVisible(false);
        updateNameField.setEditable(false);
        updatePane.add(updateNameField);
        
        updatePriceField.setBounds(200, 150, 250, 40);
        updatePriceField.setVisible(false);
        updatePane.add(updatePriceField);

        updateScrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        updateScrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        updateScrolledText.setBounds(5,350,WIDTH-30,375);
        updateScrolledText.setVisible(false);
        updateMessageArea.setEditable(false);
        updatePane.add(updateScrolledText);

        nextButton.setBounds(750, 110, 100, 40);
        nextButton.setVisible(false);
        nextButton.addActionListener(new nextListener());
        updatePane.add(nextButton);

        prevButton.setBounds(750, 50, 100, 40);
        prevButton.setVisible(false);
        prevButton.addActionListener(new prevListener());
        updatePane.add(prevButton);

        saveButton.setBounds(750, 170, 100, 40);
        saveButton.setVisible(false);
        saveButton.addActionListener(new saveListener());
        updatePane.add(saveButton);


        //Fields, buttons, scrollbar, for GAIN command
        gainField.setBounds(200, 50, 250, 40);
        gainField.setFont(gainFont);
        gainField.setVisible(false);
        gainField.setEditable(false);
        gainPane.add(gainField);

        gainScrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        gainScrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gainScrolledText.setBounds(5,200,WIDTH-30,500);
        gainScrolledText.setVisible(false);
        gainMessageArea.setEditable(false);
        gainPane.add(gainScrolledText);

        //Fields, buttons, scrollbar, for SEARCH command
        searchSymbolField.setBounds(200, 50, 250, 40);
        searchSymbolField.setVisible(false);
        searchSymbolField.setEditable(true);
        searchPane.add(searchSymbolField);

        searchNameField.setBounds(200, 115, 350, 40);
        searchNameField.setVisible(false);
        searchNameField.setEditable(true);
        searchPane.add(searchNameField);

        lowPriceField.setBounds(200, 180, 250, 40);
        lowPriceField.setVisible(false);
        lowPriceField.setEditable(true);
        searchPane.add(lowPriceField);

        highPriceField.setBounds(200, 230, 250, 40);
        highPriceField.setVisible(false);
        highPriceField.setEditable(true);
        searchPane.add(highPriceField);

        searchScrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchScrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        searchScrolledText.setBounds(5,375,WIDTH-30,350);
        searchScrolledText.setVisible(false);
        searchMessageArea.setEditable(false);
        searchPane.add(searchScrolledText);

        searchButton.setBounds(750, 200, 100, 40);
        searchButton.setVisible(false);
        searchButton.addActionListener(new searchListener());
        searchPane.add(searchButton);

        searchResetButton.setBounds(750, 100, 100, 40);
        searchResetButton.setVisible(false);
        searchResetButton.addActionListener(this);
        searchPane.add(searchResetButton);
    }

    /**
     * actionPerformed will take the actionPerformed from the JMenuBar and will respond according to which action is chosen.
     * The user will either select from the menu: buy, sell, getGain, update, search, or exit
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Buy Investment")) {
            contentPane.setVisible(false);
            buyMFPane.setVisible(false);
            sellPane.setVisible(false);
            updatePane.setVisible(false);
            gainPane.setVisible(false);
            //set searchpane off

            buttonType = "buy";
            buyType = "s";
            buyTypeList.setSelectedIndex(0);

            //Set the fields and display to be visible
            buyMessageArea.setText("");
            buySymbolField.setVisible(true);
            buyNameField.setVisible(true);
            buyQuantityField.setVisible(true);
            buyPriceField.setVisible(true);
            buyScrolledText.setVisible(true);
            buyMessageArea.setVisible(true);
            buyStockButton.setVisible(true);
            resetButton.setVisible(true);
            buyStockPane.setVisible(true);

            buySymbolField.setText("");
            buyNameField.setText("");
            buyQuantityField.setText("");
            buyPriceField.setText("");

            buyStockPane.add(buyTypeList);
        }
        else if (action.equals("Sell Investment")) {
            contentPane.setVisible(false);
            buyStockPane.setVisible(false);
            buyMFPane.setVisible(false);
            updatePane.setVisible(false);
            gainPane.setVisible(false);
            //set searchpane off

            buttonType = "sell";
            //Set textfields visible
            sellMessageArea.setText("");
            sellSymbolField.setVisible(true);
            sellQuantityField.setVisible(true);
            sellPriceField.setVisible(true);
            sellScrolledText.setVisible(true);
            sellMessageArea.setVisible(true);
            //Set buttons and pane visible
            sellButton.setVisible(true);
            sellResetButton.setVisible(true);
            sellPane.setVisible(true);

            sellSymbolField.setText("");
            sellQuantityField.setText("");
            sellPriceField.setText("");
        }
        else if (action.equals("Update Investment prices")) {
            updateCounter = 0;
            Portfolio.symbolList.removeAll(Portfolio.symbolList);
            Portfolio.nameList.removeAll(Portfolio.nameList);

            contentPane.setVisible(false);
            buyStockPane.setVisible(false);
            buyMFPane.setVisible(false);
            sellPane.setVisible(false);
            gainPane.setVisible(false);
            //search pane set off

            updateMessageArea.setText("");
            updateSymbolField.setVisible(true);
            updateNameField.setVisible(true);
            updatePriceField.setVisible(true);
            updateScrolledText.setVisible(true);
            updateMessageArea.setVisible(true);
            prevButton.setVisible(true);
            nextButton.setVisible(true);
            saveButton.setVisible(true);
            updatePane.setVisible(true);

            if (Portfolio.Investments.size() == 0) {
                GUI.updateMessageArea.setText("The Portfolio is currently empty...\nPlease buy investments before trying to update.");
                return;
            }

            for (Investment obj: Portfolio.Investments) {
                String symbol = obj.getSymbol();
                String name = obj.getName();
                Portfolio.symbolList.add(symbol);
                Portfolio.nameList.add(name);
            }

            if (updateCounter == 0) {
                prevButton.setVisible(false);
                updateSymbolField.setText(Portfolio.symbolList.get(updateCounter));
                updateNameField.setText(Portfolio.nameList.get(updateCounter));
            }
        }
        else if (action.equals("Get gain of portfolio")) {
            contentPane.setVisible(false);
            buyStockPane.setVisible(false);
            buyMFPane.setVisible(false);
            sellPane.setVisible(false);
            updatePane.setVisible(false);
            //set searchPane off

            gainMessageArea.setText("");
            gainField.setVisible(true);
            gainScrolledText.setVisible(true);
            gainMessageArea.setVisible(true);
            gainPane.setVisible(true);

            Portfolio.getGain(Portfolio.Investments);
        }
        else if (action.equals("Search for investments")) {
            contentPane.setVisible(false);
            buyStockPane.setVisible(false);
            buyMFPane.setVisible(false);
            sellPane.setVisible(false);
            updatePane.setVisible(false);
            gainPane.setVisible(false);
            resetButton.setVisible(false);

            buttonType = "search";

            searchMessageArea.setText("");
            searchMessageArea.setVisible(true);
            searchScrolledText.setVisible(true);
            searchSymbolField.setVisible(true);
            searchNameField.setVisible(true);
            lowPriceField.setVisible(true);
            highPriceField.setVisible(true);
            searchResetButton.setVisible(true);
            searchButton.setVisible(true);
            searchPane.setVisible(true);

            searchSymbolField.setText("");
            searchNameField.setText("");
            lowPriceField.setText("");
            highPriceField.setText("");
        }
        else if (action.equals("Exit program")) {
            System.exit(0); //Close down the program
        }
        else if (action.equals("Reset")) {
            if (buttonType.equals("buy")) {
                buySymbolField.setText("");
                buyNameField.setText("");
                buyQuantityField.setText("");
                buyPriceField.setText("");
                buyMessageArea.setText("Reset Complete");
            }
            else if (buttonType.equals("sell")) {
                sellSymbolField.setText("");
                sellQuantityField.setText("");
                sellPriceField.setText("");
                sellMessageArea.setText("Reset Complete");
            }
            else if (buttonType.equals("search")) {
                searchSymbolField.setText("");
                searchNameField.setText("");
                lowPriceField.setText("");
                highPriceField.setText("");
                searchMessageArea.setText("Reset Complete");
            }

        }
    }
}