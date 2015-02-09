package Controller;

import View.*;

import javax.swing.*;

/**
 * Created by Patrick on 1/14/15.
 */
public class Control {

    private final int WIDTH = 750;
    private final int HEIGHT = 500;
    private final int X = 200;
    private final int Y = 200;
    static JFrame frameMain;
    static JFrame framePrepay;
    static JFrame frameSORTP;
    static JFrame frameDonor;
    static JFrame frameItem;
    static JFrame frameImport;

    public Control(){
        frameMain = new JFrame("Kicks for a Cure Auction Manager (v0.2)");
        frameMain.setContentPane(new MainMenu().contentPane);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setBounds(X, Y, WIDTH, HEIGHT);
        frameMain.setVisible(true);

        framePrepay = new JFrame("Prepay Form");
        framePrepay.setContentPane(new Prepay().contentPane);
        framePrepay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrepay.setBounds(X, Y, WIDTH, HEIGHT);
        framePrepay.setVisible(false);

        frameSORTP = new JFrame("Silent Oral RTP Form");
        frameSORTP.setContentPane(new SilentOralRTP().contentPane);
        frameSORTP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSORTP.setBounds(X, Y, WIDTH, HEIGHT);
        frameSORTP.setVisible(false);

        frameDonor = new JFrame("Donor Form");
        frameDonor.setContentPane(new DonorForm().contentPane);
        frameDonor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameDonor.setBounds(X, Y, WIDTH, HEIGHT);
        frameDonor.setVisible(false);

        frameItem = new JFrame("Item Form");
        frameItem.setContentPane(new ItemForm().contentPane);
        frameItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameItem.setBounds(X, Y, WIDTH, HEIGHT);
        frameItem.setVisible(false);

        frameImport = new JFrame("Import Form");
        frameImport.setContentPane(new ImportForm().contentPane);
        frameImport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameImport.setBounds(X, Y, WIDTH, HEIGHT);
        frameImport.setVisible(false);
    }

    public static void showMainMenu(){
        frameMain.setVisible(true);
        framePrepay.setVisible(false);
        frameSORTP.setVisible(false);
        frameDonor.setVisible(false);
        frameItem.setVisible(false);
        frameImport.setVisible(false);
    }
    public static void showPrepayForm(){
        frameMain.setVisible(false);
        framePrepay.setVisible(true);
        frameSORTP.setVisible(false);
        frameDonor.setVisible(false);
        frameItem.setVisible(false);
        frameImport.setVisible(false);

    }
    public static void showSORTP(){
        frameMain.setVisible(false);
        framePrepay.setVisible(false);
        frameSORTP.setVisible(true);
        frameDonor.setVisible(false);
        frameItem.setVisible(false);
        frameImport.setVisible(false);

    }
    public static void showDonorForm(){
        frameMain.setVisible(false);
        framePrepay.setVisible(false);
        frameSORTP.setVisible(false);
        frameDonor.setVisible(true);
        frameItem.setVisible(false);
        frameImport.setVisible(false);

    }
    public static void showItemForm(){
        frameMain.setVisible(false);
        framePrepay.setVisible(false);
        frameSORTP.setVisible(false);
        frameDonor.setVisible(false);
        frameItem.setVisible(true);
        frameImport.setVisible(false);

    }
    public static void showImportForm(){
        frameMain.setVisible(false);
        framePrepay.setVisible(false);
        frameSORTP.setVisible(false);
        frameDonor.setVisible(false);
        frameItem.setVisible(false);
        frameImport.setVisible(true);

    }
}
