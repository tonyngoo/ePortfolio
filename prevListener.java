package tngo07_a3.Investments;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The class prevListener is used specifically in the update command when the user presses the "prev" button.
 * Its purpose is to use the updateCounter and shift its index to show the previous investment's attributes
 */
public class prevListener implements ActionListener {
    /**
     * When the user presses the button, it will call a new prevListener() which will use this actionPerformed method.
     * When "prev" is pressed it will loop through the portfolio and fidn the index of the investment and then shift it back by 1.
     * It also considers when it is at the first investment and will remove the "prev" button as there are no investments prior to it.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GUI.updateMessageArea.setText("");
            GUI.updatePriceField.setText("");
            if (Portfolio.Investments.size() == 0) {
                GUI.updateMessageArea.setText("Cannot move to previous investment when there are no investments inside the Portfolio...\nPlease buy investments before trying to update.\n");
                return;
            }

            GUI.updateCounter -= 1;
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
            if (GUI.updateCounter == 0) {
                GUI.prevButton.setVisible(false);
                GUI.nextButton.setVisible(true);
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