package View;


import Controller.Auction;
import Controller.Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patrick on 1/14/15.
 */
public class SilentOralRTP extends JFrame{
    private JTextField bidderID;
    private JTextField itemID;
    public JPanel contentPane;
    private JButton backButton;
    private JLabel invalid;
    private JButton saveButton;
    private JTextField amount;


    public SilentOralRTP() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equalsIgnoreCase("back")){
                    invalid.setText("");
                    Control.showMainMenu();
                }
                else if(e.getActionCommand().equalsIgnoreCase("save")){

                    int tempBidID = Integer.valueOf(bidderID.getText());
                    int tempItemID = Integer.valueOf(itemID.getText());
                    if (Auction.bidders.get(tempBidID) != null && Auction.items.get(tempItemID) != null) {
                        Auction.items.get(tempItemID).setBidderID(tempBidID);
                        Auction.items.get(tempItemID).setWinningBid(Double.valueOf(amount.getText()));
                        invalid.setText("Bidder #"+tempBidID+" and Item #"+tempItemID+" updated");
                        try {
                            Auction.save(Auction.year);
                        } catch (Exception e2){
                            e2.printStackTrace();
                        }
                    } else {
                        invalid.setText("invalid input");
                    }

                    bidderID.setText("");
                    itemID.setText("");
                    amount.setText("");
                }
            }
        };
        backButton.addActionListener(listener);
        saveButton.addActionListener(listener);
    }
}
