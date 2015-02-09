package Controller;

import Model.Bidder;
import Model.Item;

import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Patrick on 1/15/15.
 */



public class Auction {
    public static HashMap<Integer, Bidder> bidders;
    public static HashMap<Integer, Item> items;
    public static ArrayList years = new ArrayList();
    public static int year = 2015;

    public static void main(String args[]){
        Control control = new Control();



        //PDFExport.export();

    }

    public static void importBidders(String bidderfilename){
        Importer importer = new Importer();
        bidders = importer.importBidders(bidderfilename);

        try {
            save(year);
            load(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importItems(String itemfilename){
        Importer importer = new Importer();
        items = importer.importItems(itemfilename);

        try {
            save(year);
            load(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(int year) throws Exception{
        int loadYear = year;
        FileInputStream is = new FileInputStream(new File("./src/data/bidders_"+loadYear+".xml"));
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(is);

        HashMap<Integer, Bidder> bidtemp = new HashMap<Integer, Bidder>();
        while(reader.hasNext()){
            Bidder temp = new Bidder();
            int type = reader.next();
            switch(type){
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("start");
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    if(reader.getLocalName().equalsIgnoreCase("bidder")){
                        temp.setBidderID(Integer.valueOf(reader.getAttributeValue(0)));
                        reader.next();
                        temp.setFirstName(reader.getElementText());
                        System.out.println(temp.getFirstName());
                        reader.next();
                        temp.setFirstName2(reader.getElementText());
                        System.out.println(temp.getFirstName2());
                        reader.next();
                        temp.setLastName(reader.getElementText());
                        System.out.println(temp.getLastName());
                        reader.next();
                        temp.setFullName(reader.getElementText());
                        System.out.println(temp.getFullName());
                        reader.next();
                        temp.setTableName(reader.getElementText());
                        System.out.println(temp.getTableName());
                        reader.next();
                        temp.setTableNumber(Integer.valueOf(reader.getElementText()));
                        System.out.println(temp.getTableNumber());
                        reader.next();
                        temp.setTotalPurchase(Double.valueOf(reader.getElementText()));
                        System.out.println(temp.getTotalPurchase());
                        reader.next();
                        temp.setPaid(Boolean.valueOf(reader.getElementText()));
                        System.out.println(temp.isPaid());
                        reader.next();
                        temp.setPrepay(Boolean.valueOf(reader.getElementText()));
                        System.out.println(temp.isPrepay());
                        reader.next();
                        temp.setPostPayMethod(reader.getElementText());
                        System.out.println(temp.getPostPayMethod());
                        reader.next();
                        bidtemp.put(temp.getBidderID(),temp);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println(reader.getLocalName());
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("end");
                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println(reader.getAttributeValue(0));
                    break;
            }
        }
        bidders = bidtemp;

        FileInputStream is2 = new FileInputStream(new File("./src/data/items_"+loadYear+".xml"));
        XMLInputFactory factory2 = XMLInputFactory.newInstance();
        reader = factory2.createXMLStreamReader(is2);

        HashMap<Integer, Item> itemtemp = new HashMap<Integer, Item>();
        while(reader.hasNext()){
            Item temp = new Item();
            int type = reader.next();
            switch(type){
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("start");
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    if(reader.getLocalName().equalsIgnoreCase("item")){
                        temp.setItemID(Integer.valueOf(reader.getAttributeValue(0)));
                        reader.next();
                        temp.setItemName(reader.getElementText());
                        System.out.println(temp.getItemName());
                        reader.next();
                        temp.setDonorName(reader.getElementText());
                        System.out.println(temp.getDonorName());
                        reader.next();
                        temp.setValue(Double.valueOf(reader.getElementText()).intValue());
                        System.out.println(temp.getValue());
                        reader.next();
                        temp.setWinningBid(Double.valueOf(reader.getElementText()).intValue());
                        System.out.println(temp.getValue());
                        reader.next();
                        temp.setBidderID(Double.valueOf(reader.getElementText()).intValue());
                        System.out.println(temp.getValue());
                        reader.next();

                        itemtemp.put(temp.getItemID(),temp);
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println(reader.getLocalName());
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("end");
                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println(reader.getAttributeValue(0));
                    break;
            }
        }
        items = itemtemp;
    }

    public static void save(int year) throws Exception{
        int saveYear = year;

        FileOutputStream os = new FileOutputStream(new File("./src/data/bidders_" + saveYear + ".xml"));


        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(os);

        writer.writeStartDocument("1.0");
        writer.writeStartElement("Bidders");
        for(int id : bidders.keySet()){
            Bidder temp = bidders.get(id);
            writer.writeStartElement("Bidder");
            writer.writeAttribute("id", String.valueOf(id));

            writer.writeStartElement("firstName");
            writer.writeCharacters(temp.getFirstName());
            writer.writeEndElement();

            writer.writeStartElement("firstName2");
            writer.writeCharacters(temp.getFirstName2());
            writer.writeEndElement();

            writer.writeStartElement("lastName");
            writer.writeCharacters(temp.getLastName());
            writer.writeEndElement();

            writer.writeStartElement("fullName");
            writer.writeCharacters(temp.getFullName());
            writer.writeEndElement();

            writer.writeStartElement("tableName");
            writer.writeCharacters(temp.getTableName());
            writer.writeEndElement();

            writer.writeStartElement("tableNumber");
            writer.writeCharacters(String.valueOf(temp.getTableNumber()));
            writer.writeEndElement();

            writer.writeStartElement("totalPurchase");
            writer.writeCharacters(String.valueOf(temp.getTotalPurchase()));
            writer.writeEndElement();

            writer.writeStartElement("paid");
            writer.writeCharacters(String.valueOf(temp.isPaid()));
            writer.writeEndElement();

            writer.writeStartElement("prepay");
            writer.writeCharacters(String.valueOf(temp.isPrepay()));
            writer.writeEndElement();

            writer.writeStartElement("postPayMethod");
            writer.writeCharacters(String.valueOf(temp.getPostPayMethod()));
            writer.writeEndElement();

            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();


        FileOutputStream os2 = new FileOutputStream(new File("./src/data/items_"+saveYear+".xml"));
        XMLStreamWriter writer2 = factory.createXMLStreamWriter(os2);

        writer2.writeStartDocument("1.0");
        writer2.writeStartElement("Items");
        for(int id : items.keySet()){
            Item temp = items.get(id);
            writer2.writeStartElement("Item");
            writer2.writeAttribute("id", String.valueOf(id));

            writer2.writeStartElement("itemName");
            writer2.writeCharacters(temp.getItemName());
            writer2.writeEndElement();

            writer2.writeStartElement("donorName");
            writer2.writeCharacters(temp.getDonorName());
            writer2.writeEndElement();

            writer2.writeStartElement("value");
            writer2.writeCharacters(String.valueOf(temp.getValue()));
            writer2.writeEndElement();

            writer2.writeStartElement("winningBid");
            writer2.writeCharacters(String.valueOf(temp.getValue()));
            writer2.writeEndElement();

            writer2.writeStartElement("bidderID");
            writer2.writeCharacters(String.valueOf(temp.getValue()));
            writer2.writeEndElement();

            writer2.writeEndElement();
        }
        writer2.writeEndElement();
        writer2.writeEndDocument();
    }

    public static ArrayList getYears() {
        return years;
    }
}
