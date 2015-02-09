package View;


import Controller.Auction;
import Controller.Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patrick on 1/14/15.
 */
public class Prepay extends JFrame {
    private JTextField textField1;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton saveButton;
    public JPanel contentPane;
    private JLabel invalid;
    private JButton backButton;

    public Prepay() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equalsIgnoreCase("yes")){
                    noRadioButton.setSelected(false);
                    yesRadioButton.setSelected(true);
                }
                else if(e.getActionCommand().equalsIgnoreCase("no")){
                    yesRadioButton.setSelected(false);
                    noRadioButton.setSelected(true);
                }
                else if(e.getActionCommand().equalsIgnoreCase("save")){
                    if(textField1.getText().equalsIgnoreCase("")){
                        invalid.setText("Enter Bidder ID");
                    } else {
                        try {
                            if (yesRadioButton.isSelected()) {
                                Auction.bidders.get(Integer.valueOf(textField1.getText())).setPrepay(true);
                            } else {
                                Auction.bidders.get(Integer.valueOf(textField1.getText())).setPrepay(false);
                            }
                            textField1.setText("");
                            invalid.setText("Bidder #" + textField1.getText() + " updated");
                            Auction.save(Auction.year);
                        } catch (Exception e2) {
                            textField1.setText("");
                            invalid.setText("ID not found");
                        }
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("back")){
                    textField1.setText("");
                    invalid.setText("");

                    Control.showMainMenu();
                }

            }
        };
        yesRadioButton.addActionListener(listener);
        noRadioButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        backButton.addActionListener(listener);

    }
}
