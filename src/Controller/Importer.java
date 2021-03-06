package Controller;

import Model.Bidder;
import Model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;


public class Importer {

	private static HashMap<Integer, Bidder> bidders;
	private static HashMap<Integer, Item> items;
    private static int year;

    public int getYear() {
        return year;
    }

    public HashMap<Integer, Bidder> getBidders() {
        return bidders;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

	public static String importItems(String filename){

		items = new HashMap<Integer, Item>();
		File donorFile = new File(filename);
		Scanner scan = null;

		try {
			scan = new Scanner(new FileReader(donorFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
            return "failed to read item file";
		}

		//skip the column titles
		scan.nextLine();

		try {
            while(scan.hasNext()){

                String line = scan.nextLine();
                String[] arr = line.split(",");
                if(arr.length == 0){
                    continue;
                }
                int nextIndex = 0;
                year = Integer.valueOf(arr[0]);

                nextIndex = 1;
                int itemID = Integer.valueOf(arr[1]);
                System.out.println(itemID);

                nextIndex = 2;
                int count = 0;
                if(arr[2].startsWith("\"") && !arr[2].endsWith("\"")){
                    count = 1;
                    boolean done = false;
                    while(!done){
                        if(!arr[2+count].endsWith("\"")){
                            count++;
                        }
                        else{
                            done = true;
                        }

                    }
                }
                String itemName = "";
                for(int i = 0; i < count+1; i++){
                    itemName += arr[2+i];
                }

                nextIndex += count+1;
                count = 0;
                if(arr[nextIndex].startsWith("\"") && !arr[nextIndex].endsWith("\"")){
                    count = 1;
                    boolean done = false;
                    while(!done){
                        if(!arr[nextIndex+count].endsWith("\"")){
                            count++;
                        }
                        else{
                            done = true;
                        }

                    }
                }
                String donorName = "";
                for(int i = 0; i < count+1; i++){
                    donorName += arr[nextIndex+i];
                }

                nextIndex += count+1;
                double value;
                if(arr[nextIndex].contains("$")){
                    arr[nextIndex] = arr[nextIndex].replace("$", "");
                }
                if(arr[nextIndex].equalsIgnoreCase("priceless")){
                    value = 0;
                }else{
                    value = Double.valueOf(arr[nextIndex]);
                }

                Item temp = new Item(itemID, itemName, donorName, value);
                items.put(itemID, temp);
            }
        } catch (Exception e){
            e.printStackTrace();
            return "failed while reading items ("+e.getCause().toString()+")";
        }

		return "Success";
	}

    public static String importBidders(String filename){
        bidders = new HashMap<Integer, Bidder>();
        File bidderFile = new File(filename);
        Scanner scan = null;

        try {
            scan = new Scanner(new FileReader(bidderFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "failed to read bidder file";
        }

        //skip the column titles
        scan.nextLine();

        try{
            while(scan.hasNext()){
                String line = scan.nextLine();
                String[] arr = line.split(",");

                if(arr.length == 0){
                    continue;
                }
                int nextIndex=0;

                year = Integer.valueOf(arr[nextIndex]);

                nextIndex++;

                String firstName = arr[nextIndex];

                nextIndex++;

                String firstName2 = arr[nextIndex];

                nextIndex++;
                int count = 0;
                if(arr[nextIndex].startsWith("\"") && !arr[nextIndex].endsWith("\"")){
                    count = 1;
                    boolean done = false;
                    while(!done){
                        if(!arr[nextIndex+count].endsWith("\"")){
                            count++;
                        }
                        else{
                            done = true;
                        }

                    }
                }
                String lastName = "";
                for(int i = 0; i < count+1; i++){
                    lastName += arr[nextIndex+i];
                }

                nextIndex += count+1;

                count = 0;
                if(arr[nextIndex].startsWith("\"") && !arr[nextIndex].endsWith("\"")){
                    count = 1;
                    boolean done = false;
                    while(!done){
                        if(!arr[nextIndex+count].endsWith("\"")){
                            count++;
                        }
                        else{
                            done = true;
                        }

                    }
                }
                String fullName = "";
                for(int i = 0; i < count+1; i++){
                    fullName += arr[nextIndex+i];
                }

                nextIndex += count+1;
                count = 0;
                if(arr[nextIndex].startsWith("\"") && !arr[nextIndex].endsWith("\"")){
                    count = 1;
                    boolean done = false;
                    while(!done){
                        if(!arr[nextIndex+count].endsWith("\"")){
                            count++;
                        }
                        else{
                            done = true;
                        }

                    }
                }
                String tableName = "";
                for(int i = 0; i < count+1; i++){
                    tableName += arr[nextIndex+i];
                }

                nextIndex += count+1;
                if (arr[nextIndex].equalsIgnoreCase("")){
                    continue;
                }
                int tableID = Integer.valueOf(arr[nextIndex]);

                nextIndex++;
                int bidderID = Integer.valueOf(arr[nextIndex]);
                System.out.println(bidderID);

                nextIndex ++;
                //skip total purchase
                nextIndex ++;
                //skip paid
                nextIndex ++;
                boolean expressPay;
                if(arr[nextIndex].equalsIgnoreCase("yes")){
                    expressPay = true;
                } else {
                    expressPay = false;
                }
                nextIndex++;
                String postPayMethod = "";
                if(arr.length >= 12) {
                    postPayMethod= arr[nextIndex];
                }

                Bidder temp = new Bidder(firstName,firstName2,lastName,fullName,tableName,tableID,bidderID,false,expressPay,postPayMethod);
                bidders.put(bidderID, temp);
            }
        } catch (Exception e){
            e.printStackTrace();
            return "failed while importing bidders ("+e.getCause().toString()+")";
        }

        return "Success";
    }
}
