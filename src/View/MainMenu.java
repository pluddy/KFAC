package View;


import Controller.Auction;
import Controller.Control;
import Controller.PDFExport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Patrick on 1/6/15.
 */
public class MainMenu extends JFrame{
    private JButton prepayButton;
    private JButton silentOralRTPButton;
    private JButton itemMaintenanceButton;
    private JButton donorMaintenanceButton;
    private JButton reportsButton;
    private JButton importFilesButton;
    private JButton exitButton;
    public JPanel contentPane;
    public JComboBox comboBox1;
    private JLabel noDonorFile;
    private JLabel noItemFile;


    public MainMenu() {

        noDonorFile.setVisible(false);
        noItemFile.setVisible(false);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equalsIgnoreCase("prepay")){
                    System.out.println("prepay");
                    Control.showPrepayForm();
                }
                else if(e.getActionCommand().equalsIgnoreCase("silent, oral, rtp")){
                    System.out.println("sortp");
                    Control.showSORTP();
                }
                else if(e.getActionCommand().equalsIgnoreCase("donor maintenance")){
                    try{
                        Auction.bidders.isEmpty();
                        noDonorFile.setVisible(false);
                        System.out.println("donor");
                        Control.showDonorForm();
                    } catch(Exception e2) {
                        e2.printStackTrace();
                        noDonorFile.setVisible(true);

                    }

                }
                else if(e.getActionCommand().equalsIgnoreCase("item maintenance")){
                    try{
                        Auction.items.isEmpty();
                        noItemFile.setVisible(false);
                        System.out.println("item");
                        Control.showItemForm();
                    } catch(Exception e2) {
                        noItemFile.setVisible(true);
                        e2.printStackTrace();
                    }
                }
                else if(e.getActionCommand().equalsIgnoreCase("reports")){
                    System.out.println("reports");
                    PDFExport.export();
                }
                else if(e.getActionCommand().equalsIgnoreCase("import files")){
                    noDonorFile.setVisible(false);
                    noItemFile.setVisible(false);
                    System.out.println("import");
                    Control.showImportForm();

                }
                else if(e.getActionCommand().equalsIgnoreCase("exit")){
                    System.out.println("exit");
                    System.exit(0);
                }

            }
        };
        prepayButton.addActionListener(listener);
        silentOralRTPButton.addActionListener(listener);
        donorMaintenanceButton.addActionListener(listener);
        itemMaintenanceButton.addActionListener(listener);
        reportsButton.addActionListener(listener);
        importFilesButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
