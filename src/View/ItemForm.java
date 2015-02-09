package View;

import Controller.Auction;
import Controller.Control;
import Model.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patrick on 1/14/15.
 */
public class ItemForm extends JFrame {
    private JTextField itemID;
    private JTextField itemDescription;
    private JTextField donorName;
    private JTextField value;
    private JButton saveButton;
    public JPanel contentPane;
    private JButton backButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JLabel invalid;

    public ItemForm(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equalsIgnoreCase("search")){
                    int tempItemID = Integer.valueOf(itemID.getText());
                    if (Auction.items.get(tempItemID) != null) {
                        Item tempItem = Auction.items.get(tempItemID);
                        itemDescription.setText(tempItem.getItemName());
                        donorName.setText(tempItem.getDonorName());
                        value.setText("" + tempItem.getValue());
                        invalid.setText("");
                    } else {
                        invalid.setText("Item Not Found");
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("save")){
                    int tempItemID = Integer.valueOf(itemID.getText());
                    if (Auction.items.get(tempItemID) != null) {
                        Auction.items.get(tempItemID).setItemName(itemDescription.getText());
                        Auction.items.get(tempItemID).setDonorName(donorName.getText());
                        Auction.items.get(tempItemID).setValue(Double.valueOf(value.getText()));

                        try {
                            Auction.save(Auction.year);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                        itemID.setText("");
                        itemDescription.setText("");
                        donorName.setText("");
                        value.setText("");
                        invalid.setText("");
                    } else {
                        Object[] options = {"Yes","No","Cancel"};
                        int n = JOptionPane.showOptionDialog(contentPane,"Item with ID #"+tempItemID+" not found, would you like to create a new item with this ID?","WARNING",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                        switch (n){
                            case 0:
                                Item tempItem = new Item(tempItemID,itemDescription.getText(),donorName.getText(),Double.valueOf(value.getText()));
                                Auction.items.put(tempItemID,tempItem);
                                try {
                                    Auction.save(Auction.year);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                itemID.setText("");
                                itemDescription.setText("");
                                donorName.setText("");
                                value.setText("");
                                invalid.setText("");
                                break;
                            case 1:
                                itemID.setText("");
                                itemDescription.setText("");
                                donorName.setText("");
                                value.setText("");
                                invalid.setText("");
                                break;
                            case 2:
                                break;

                        }
                        invalid.setText("");
                    }

                }
                else if (e.getActionCommand().equalsIgnoreCase("back")){
                    itemID.setText("");
                    itemDescription.setText("");
                    donorName.setText("");
                    value.setText("");
                    invalid.setText("");

                    Control.showMainMenu();
                }
                else if (e.getActionCommand().equalsIgnoreCase("delete")){
                    int tempItemID = Integer.valueOf(itemID.getText());
                    if (Auction.items.get(tempItemID) != null) {
                        Object[] options = {"Yes","No"};
                        int n = JOptionPane.showOptionDialog(contentPane,"Delete Item #"+tempItemID+"?","WARNING",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
                        if(n==0){
                            Auction.items.remove(tempItemID);
                            itemID.setText("");
                            itemDescription.setText("");
                            donorName.setText("");
                            value.setText("");
                            invalid.setText("");
                        }
                    }
                }
            }
        };
        saveButton.addActionListener(listener);
        backButton.addActionListener(listener);
        searchButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
    }

}
