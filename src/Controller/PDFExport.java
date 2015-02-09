package Controller;

import Model.Bidder;
import Model.Item;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Patrick on 2/7/15.
 */
public class PDFExport {
    public static void export(){
        PDDocument doc = null;
        PDPage page = null;

        String title = "2015 Kicks for a Cure Dinner";


        String[] colTitles = {"Item No.","Item","Donor","Value","Bid","Bidder No."};
        String thanks = "Thank You for your Support!";
        String save = "SAVE THIS RECEIPT FOR TAX PURPOSES";
        String mark = "Mark your calendar for next year's KICKS FOR A CURE event April 16th, 2016";

        for (int bidderID : Auction.bidders.keySet()){
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);

            PDXObjectImage kfac = null;
            try {
                kfac = new PDJpeg(doc, new FileInputStream(new File("./KicksforCure.jpg")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            kfac.setHeight(kfac.getHeight()/4);
            kfac.setWidth(kfac.getWidth()/4);

            Bidder tempBidder = Auction.bidders.get(bidderID);
            String subtitle = "Auction Receipt for "+tempBidder.getFullName().replace("&amp;","&");
            ArrayList<String[]> itemsWon = new ArrayList<String[]>();
            for(Item itemWon : Auction.items.values()){
                if(itemWon.getBidderID()==bidderID){
                    String[] temp = {""+itemWon.getItemID(),itemWon.getItemName(),itemWon.getDonorName(),""+itemWon.getValue(),""+itemWon.getWinningBid(),""+itemWon.getBidderID()};
                    itemsWon.add(temp);
                }
            }
            String expressPay = "";
            if(tempBidder.isPrepay()) {
                expressPay = "Express Pay? YES";
            } else {
                expressPay = "Express Pay? NO";
            }
            String postPayMethod = "Other Method of Payment: "+tempBidder.getPostPayMethod();
            double bidTotal = 0;

            PDFont titleFont = PDType1Font.HELVETICA_BOLD;
            PDFont subtitleFont = PDType1Font.HELVETICA;
            PDFont font = PDType1Font.COURIER_BOLD;
            PDFont font2 = PDType1Font.COURIER;

            try {
                System.out.println("report for bidder: "+bidderID);
                PDPageContentStream content = new PDPageContentStream(doc, page);
                content.beginText();
                content.setFont(titleFont, 32);
                content.moveTextPositionByAmount(25, 720);
                content.drawString(title);

                content.setFont(subtitleFont, 24);
                content.moveTextPositionByAmount(0, -30);
                content.drawString(subtitle);

                content.setFont(font2, 8);
                content.moveTextPositionByAmount(0, -30);
                content.drawString(String.format("%-10s%-40s%-20s %11s  %12s%12s",colTitles[0],colTitles[1],colTitles[2],colTitles[3],colTitles[4],colTitles[5]));

                content.setFont(font2, 8);
                for(String[] s : itemsWon){
                    content.moveTextPositionByAmount(0, -10);
                    bidTotal += Double.valueOf(s[4]);
                    content.drawString(String.format("%-10s%-40s%-20s %11s  %12s%12s", s[0], s[1], s[2], "$"+s[3], "$"+s[4], s[5]));
                }
                content.moveTextPositionByAmount(400, -30);
                content.drawString("Bid Total: $"+bidTotal);
                content.moveTextPositionByAmount(0,-12);
                if(tempBidder.isPrepay()){
                    content.drawString("Express Pay Total: $"+bidTotal);
                    content.moveTextPositionByAmount(0,-12);
                    content.drawString("Traditional Pay Total: $" + 0);
                    content.moveTextPositionByAmount(0,-12);
                    content.setFont(font2, 8);
                    content.drawString("Total Balance: $"+0);

                } else if (!tempBidder.getPostPayMethod().equalsIgnoreCase("")){
                    content.drawString("Express Pay Total: $"+0);
                    content.moveTextPositionByAmount(0,-12);
                    content.drawString("Traditional Pay Total: $" + bidTotal);
                    content.moveTextPositionByAmount(0,-12);
                    content.setFont(font2, 8);
                    content.drawString("Total Balance: $"+0);
                } else {
                    content.drawString("Express Pay Total: $"+0);
                    content.moveTextPositionByAmount(0,-12);
                    content.drawString("Traditional Pay Total: $" + 0);
                    content.moveTextPositionByAmount(0,-12);
                    content.setFont(font2, 8);
                    content.drawString("Total Balance: $"+bidTotal);
                }
                content.moveTextPositionByAmount(-400,-10);
                content.drawString(expressPay);
                content.moveTextPositionByAmount(0,-10);
                content.drawString(postPayMethod);

                content.moveTextPositionByAmount(0, -50);
                content.setFont(subtitleFont, 14 );
                content.drawString(thanks);
                content.moveTextPositionByAmount(0,-16);
                content.drawString(save);
                content.moveTextPositionByAmount(0, -20);
                content.drawString(mark);

                content.endText();

                content.drawImage(kfac,475,50);
                content.close();
                doc.save("./src/export/Bidder_"+bidderID+".pdf");
                doc.close();
            } catch(Exception e){
                e.printStackTrace();
            }


//            System.out.println(title);
//            System.out.println(subtitle);
//            System.out.println();
//            System.out.println(colTitles[0] + "  " + colTitles[1] + "  " + colTitles[2] + "  " + colTitles[3] + "  " + colTitles[4] + "  " + colTitles[5]);
//            for(String[] s : itemsWon){
//                bidTotal += Double.valueOf(s[4]);
//                System.out.printf("%-8s  %-30s  %30s  $%11s  $%11s  %8s",s[0],s[1],s[2],s[3],s[4],s[5]);
//            }
//            System.out.println();
//            System.out.println("Bid Total: $"+bidTotal);
//            System.out.println(expressPay);
//            System.out.println(postPayMethod);
//            System.out.println();
//            System.out.println(thanks);
//            System.out.println(save);
//            System.out.println(mark);
        }


        try{
            doc = new PDDocument();
            page = new PDPage();

            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont( font, 32 );
            content.moveTextPositionByAmount(100,700);
            content.drawString("2014 Kicks for a Cure Dinner");

            content.endText();
            //content.drawImage(kfac,200,200);
            content.close();
            doc.save("./src/export/PDFWithText.pdf");
            doc.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
