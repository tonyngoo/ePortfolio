package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * The class nextListener is used specifically in the update command when the user presses the "next" button.
 * Its purpose is to use the updateCounter and shift its index to show the next investment's attributes.
 */
public class nextListener implements ActionListener {
    /**
     * When the user presses the button, it will call a new nextListener() which will use this actionPerformed method.
     * When "next" is pressed it will loop through the portfolio and find the index of the investment and then shift it.
     * It also considers when it is at the final investment and will remove the "next" button when it is reached.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GUI.updateMessageArea.setText("");
            GUI.updatePriceField.setText("");
            if (Portfolio.Investments.size() == 0) {
                GUI.updateMessageArea.setText("Cannot move to next investment when there are no investments inside the Portfolio...\nPlease buy investments before trying to update.\n");
                return;
            }
            GUI.updateCounter += 1;
            Portfolio.symbolList.removeAll(Portfolio.symbolList);
            Portfolio.nameList.removeAll(Portfolio.nameList);
            for (Investment obj: Portfolio.Investments) {
                String symbol = obj.getSymbol();
                String name = obj.getName();
                Portfolio.symbolList.add(symbol);
                Portfolio.nameList.add(name);
            }
            GUI.updateSymbolField.setText(Portfolio.symbolList.get(GUI.updateCounter));
            GUI.updateNameField.setText(Portfolio.nameList.get(GUI.updateCounter));
            if (Portfolio.symbolList.size() - 1 == GUI.updateCounter) {
                GUI.nextButton.setVisible(false);
                GUI.prevButton.setVisible(true);
            }
            else {
                GUI.prevButton.setVisible(true);
                GUI.nextButton.setVisible(true);
            }
        } catch (Exception err) {
            GUI.updateMessageArea.setText("An error occured while trying to update:\n" + err.getMessage());
        }

    }
}