package View;


import Controller.Auction;
import Controller.Control;
import Model.Bidder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patrick on 1/14/15.
 */
public class DonorForm extends JFrame{
    private JTextField firstName;
    private JTextField firstName2;
    private JTextField lastName;
    private JTextField fullName;
    private JTextField tableName;
    private JTextField tableNo;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton saveButton;
    public JPanel contentPane;
    private JTextField bidderID;
    private JButton searchButton;
    private JButton backButton;
    private JLabel invalid;
    private JButton deleteButton;
    private JTextField postPay;

    public DonorForm(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equalsIgnoreCase("search")){
                    int tempBidID = Integer.valueOf(bidderID.getText());
                    if (Auction.bidders.get(tempBidID) != null) {
                        Bidder tempBidder = Auction.bidders.get(tempBidID);
                        firstName.setText(tempBidder.getFirstName());
                        firstName2.setText(tempBidder.getFirstName2());
                        lastName.setText(tempBidder.getLastName());
                        fullName.setText(tempBidder.getFullName());
                        tableName.setText(tempBidder.getTableName());
                        tableNo.setText("" + tempBidder.getTableNumber());
                        postPay.setText(tempBidder.getPostPayMethod());
                        if(tempBidder.isPrepay()){
                            yesRadioButton.setSelected(true);
                            noRadioButton.setSelected(false);
                        } else {
                            yesRadioButton.setSelected(false);
                            noRadioButton.setSelected(true);
                        }
                        invalid.setText("");
                    } else {
                        invalid.setText("Bidder not found");
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("yes")){
                    yesRadioButton.setSelected(true);
                    noRadioButton.setSelected(false);
                }
                else if(e.getActionCommand().equalsIgnoreCase("no")){
                    yesRadioButton.setSelected(false);
                    noRadioButton.setSelected(true);
                }
                else if(e.getActionCommand().equalsIgnoreCase("save")){
                    int tempBidID = Integer.valueOf(bidderID.getText());
                    if (Auction.bidders.get(tempBidID) != null) {
                        Auction.bidders.get(tempBidID).setFirstName(firstName.getText());
                        Auction.bidders.get(tempBidID).setFirstName2(firstName2.getText());
                        Auction.bidders.get(tempBidID).setLastName(lastName.getText());
                        Auction.bidders.get(tempBidID).setFullName(fullName.getText());
                        Auction.bidders.get(tempBidID).setTableName(tableName.getText());
                        Auction.bidders.get(tempBidID).setTableNumber(Integer.valueOf(tableNo.getText()));
                        Auction.bidders.get(tempBidID).setPostPayMethod(postPay.getText());
                        if(yesRadioButton.isSelected()){
                            Auction.bidders.get(tempBidID).setPrepay(true);
                        } else {
                            Auction.bidders.get(tempBidID).setPrepay(false);
                        }

                        try {
                            Auction.save(Auction.year);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                        bidderID.setText("");
                        firstName.setText("");
                        firstName2.setText("");
                        lastName.setText("");
                        fullName.setText("");
                        tableName.setText("");
                        tableNo.setText("");
                        postPay.setText("");
                        invalid.setText("");

                    } else {
                        Object[] options = {"Yes","No","Cancel"};
                        int n = JOptionPane.showOptionDialog(contentPane,"Bidder with ID #"+bidderID.getText()+" not found, would you like to create a new bidder with this ID?","WARNING",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

                        switch (n) {
                            case 0:
                                Bidder tempBid = new Bidder(firstName.getText(),firstName2.getText(),lastName.getText(),fullName.getText(),tableName.getText(),Integer.valueOf(tableNo.getText()),tempBidID,false,yesRadioButton.isSelected(),"");
                                Auction.bidders.put(tempBidID,tempBid);
                                try {
                                    Auction.save(Auction.year);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                bidderID.setText("");
                                firstName.setText("");
                                firstName2.setText("");
                                lastName.setText("");
                                fullName.setText("");
                                tableName.setText("");
                                tableNo.setText("");
                                postPay.setText("");
                                break;
                            case 1:
                                bidderID.setText("");
                                firstName.setText("");
                                firstName2.setText("");
                                lastName.setText("");
                                fullName.setText("");
                                tableName.setText("");
                                tableNo.setText("");
                                postPay.setText("");
                                break;
                            case 2:
                                break;
                        }
                        invalid.setText("");
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("back")){
                    bidderID.setText("");
                    firstName.setText("");
                    firstName2.setText("");
                    lastName.setText("");
                    fullName.setText("");
                    tableName.setText("");
                    tableNo.setText("");
                    postPay.setText("");
                    invalid.setText("");

                    Control.showMainMenu();
                }
                else if(e.getActionCommand().equalsIgnoreCase("delete")){
                    int tempBidID = Integer.valueOf(bidderID.getText());
                    if (Auction.bidders.get(tempBidID) != null) {
                        Object[] options = {"Yes","No"};
                        int n = JOptionPane.showOptionDialog(contentPane,"Delete Bidder #"+tempBidID+"?","WARNING",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
                        if(n==0){
                            Auction.bidders.remove(tempBidID);
                            bidderID.setText("");
                            firstName.setText("");
                            firstName2.setText("");
                            lastName.setText("");
                            fullName.setText("");
                            tableName.setText("");
                            tableNo.setText("");
                            invalid.setText("");
                            postPay.setText("");
                        }
                    }
                }
            }
        };
        saveButton.addActionListener(listener);
        backButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        yesRadioButton.addActionListener(listener);
        noRadioButton.addActionListener(listener);
        searchButton.addActionListener(listener);
    }


}
