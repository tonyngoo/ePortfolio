package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The class saveListener is used inside of the update command when the user specifically presses the "save" button.
 * Its purpose is to take whatever the user inputted price is and to save it as the object's new price.
 */
public class saveListener implements ActionListener {
    /**
     * When the user presses the "save" button, it will call a new saveListener() which will use this actionPerformed method.
     * When it is pressed, it will check if it is a valid input for a price and then afterwards it will use the updateCounter to find
     * where the investment's index is and then save the new price in the object.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer currentCounter = 0;
            String price = "";
            boolean emptyFound = false;
            boolean incorrectFound = false;

            if (Portfolio.Investments.size() == 0) {
                GUI.updateMessageArea.setText("The Portfolio is currently empty...\nPlease buy investments before trying to save.");
                return;
            }
    
            price = GUI.updatePriceField.getText();

            if (Portfolio.isNumerical(price) == false) {
                if (!price.isEmpty()) {
                    GUI.updateMessageArea.append("You must enter a numerical price...\n");
                    incorrectFound = true;
                }
            }
            if (price.isEmpty()) {
                GUI.updateMessageArea.append("The price you wish to update to cannot be empty...");
                emptyFound = true;
            }
            if (emptyFound == true || incorrectFound == true) {
                return;
            }
            for (Investment obj: Portfolio.Investments) {
                if (currentCounter == GUI.updateCounter) {
                    obj.setPrice(price);
                    GUI.updateMessageArea.setText("Price successfully saved!\n");
                    return;
                }
                currentCounter += 1;
            }
        } catch (Exception err) {
            GUI.updateMessageArea.setText("An error occurred while trying to save price:\n" + err.getMessage());
        }
    }

}