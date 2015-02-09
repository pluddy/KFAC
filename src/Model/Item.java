package Model;

/**
 * @author Patrick
 *
 */
public class Item {
	private int 	itemID;
	private String 	itemName;
	private String	donorName;
	private double 	value;
	private double  winningBid;
	private int		bidderID;

	
	/**
	 * @param itemID
	 * @param itemName
	 * @param donorName
	 * @param value
	 */
	public Item(int itemID, String itemName, String donorName, double value){
		this.itemID = itemID;
		this.itemName = itemName;
		this.donorName = donorName;
		this.value = value;
		this.winningBid = -1;
		this.bidderID = -1;
	}

	public Item(){

	}

	/**
	 * @return
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	/**
	 * @return
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	public int getBidderID() {
		return bidderID;
	}

	public void setBidderID(int bidderID) {
		this.bidderID = bidderID;
	}

	public double getWinningBid() {
		return winningBid;
	}

	public void setWinningBid(double winningBid) {
		this.winningBid = winningBid;
	}
	
	
}
