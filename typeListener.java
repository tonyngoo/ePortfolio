package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


/**
 * The class typeListener is used inside of the GUI class, specifically on the buy window when the user is selecting between stock or mutualfund
 * The typeListener is used for the JComboBox. When the user selects between stock or mutualfund, it will change the textfields accordingly.
 */

public class typeListener implements ActionListener {
    /**
     * When the user opens the JComboBox in the buy window and then selects either "Stock" or "Mutual Fund", then it will call a typeListener and run this method.
     * The method will determine which type was chosen and make the textfields visible according to it. 
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JComboBox<String> chosen = (JComboBox<String>) e.getSource();
        String typeChosen = (String)chosen.getSelectedItem();

        if (typeChosen.equals("Mutual Fund")) {
            GUI.buyType = "mf";

            GUI.buyMFPane.add(GUI.buySymbolField);
            GUI.buyMFPane.add(GUI.buyNameField);
            GUI.buyMFPane.add(GUI.buyQuantityField);
            GUI.buyMFPane.add(GUI.buyPriceField);
            GUI.buyMFPane.add(GUI.buyMFButton);
            GUI.buyMFPane.add(GUI.resetButton);
            GUI.buyMFPane.add(GUI.buyScrolledText);

            //Setting all other possible panes to false
            GUI.contentPane.setVisible(false);
            GUI.buyStockPane.setVisible(false);

            GUI.buySymbolField.setVisible(true);
            GUI.buyNameField.setVisible(true);
            GUI.buyQuantityField.setVisible(true);
            GUI.buyPriceField.setVisible(true);
            GUI.buyMFButton.setVisible(true);
            GUI.resetButton.setVisible(true);
            GUI.buyMFPane.setVisible(true);

            GUI.buySymbolField.setText("");
            GUI.buyNameField.setText("");
            GUI.buyQuantityField.setText("");
            GUI.buyPriceField.setText("");
            GUI.buyMessageArea.setText("");

            GUI.buyMFPane.add(GUI.buyTypeList);
        }
        else {
            GUI.buyType = "s";

            GUI.buyStockPane.add(GUI.buySymbolField);
            GUI.buyStockPane.add(GUI.buyNameField);
            GUI.buyStockPane.add(GUI.buyQuantityField);
            GUI.buyStockPane.add(GUI.buyPriceField);
            GUI.buyStockPane.add(GUI.buyStockButton);
            GUI.buyStockPane.add(GUI.resetButton);
            GUI.buyStockPane.add(GUI.buyScrolledText);

            GUI.contentPane.setVisible(false);
            GUI.buyMFPane.setVisible(false);

            GUI.buySymbolField.setVisible(true);
            GUI.buyNameField.setVisible(true);
            GUI.buyQuantityField.setVisible(true);
            GUI.buyPriceField.setVisible(true);
            GUI.buyMFButton.setVisible(true);
            GUI.resetButton.setVisible(true);
            GUI.buyStockPane.setVisible(true);

            GUI.buySymbolField.setText("");
            GUI.buyNameField.setText("");
            GUI.buyQuantityField.setText("");
            GUI.buyPriceField.setText("");
            GUI.buyMessageArea.setText("");

            GUI.buyStockPane.add(GUI.buyTypeList);
        }
    }
}