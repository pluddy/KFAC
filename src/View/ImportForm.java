package View;

import Controller.Auction;
import Controller.Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Patrick on 2/8/15.
 */
public class ImportForm extends JFrame{
    private JButton importBiddersButton;
    private JButton importItemsButton;
    private JButton backButton;
    public JPanel contentPane;
    private JLabel importedBidders;
    private JLabel importedItems;
//    public JPanel contentPane;

    public ImportForm(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                if(e.getActionCommand().equalsIgnoreCase("Import Bidders")){
                    String file1 = "";

                    fc.setDialogTitle("Choose Bidder File");
                    int returnVal = fc.showOpenDialog(contentPane);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        try {
                            file1 = file.getCanonicalPath();
                        }catch (Exception e2){
                            e2.printStackTrace();
                        }

                        //This is where a real application would open the file.
                        importedBidders.setText("Opening: " + file.getName());
                        Auction.importBidders(file1);
                        importedBidders.setText(importedBidders.getText() + "\n" + Auction.bidders.size() + " Bidders added");
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                } else if(e.getActionCommand().equalsIgnoreCase("Import Items")){
                    String file2 = "";

                    fc.setDialogTitle("Choose Item File");
                    int returnVal = fc.showOpenDialog(contentPane);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        try {
                            file2 = file.getCanonicalPath();
                        }catch (Exception e2){
                            e2.printStackTrace();
                        }

                        //This is where a real application would open the file.
                        importedItems.setText("Opening: " + file.getName());
                        Auction.importItems(file2);
                        importedItems.setText(importedItems.getText() + "\n" + Auction.bidders.size() + " Items added");
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                } else if(e.getActionCommand().equalsIgnoreCase("Back")){
                    importedBidders.setText("");
                    importItemsButton.setText("");
                    Control.showMainMenu();
                }
            }
        };
        importBiddersButton.addActionListener(listener);
        importItemsButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

}
