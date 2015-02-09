package Model;

public class Bidder {
	private String 	firstName;
	private String	firstName2;
	private String 	lastName;
	private String 	fullName;
	private String 	tableName;
	private int 	tableNumber;
	private int 	bidderID;
	private double 	totalPurchase;
	private boolean paid;
	private boolean prepay;
	private String	postPayMethod;
	/**
	 * @param firstName
	 * @param firstName2
	 * @param lastName
	 * @param fullName
	 * @param tableName
	 * @param tableNumber
	 * @param bidderID
	 * @param paid
	 * @param prepay
	 * @param postPayMethod
	 */
	public Bidder(String firstName, String firstName2,
			String lastName, String fullName, String tableName,
			int tableNumber, int bidderID, boolean paid,
			boolean prepay, String postPayMethod) {
		this.firstName = firstName;
		this.firstName2 = firstName2;
		this.lastName = lastName;
		this.fullName = fullName;
		this.tableName = tableName;
		this.tableNumber = tableNumber;
		this.bidderID = bidderID;
        this.totalPurchase = 0;
        this.paid = paid;
		this.prepay = prepay;
        this.postPayMethod = postPayMethod;
	}

	public Bidder(){

	}


	public int getBidderID() {
		return bidderID;
	}

	public void setBidderID(int bidderID) {
		this.bidderID = bidderID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the firstName2
	 */
	public String getFirstName2() {
		return firstName2;
	}
	/**
	 * @param firstName2 the firstName2 to set
	 */
	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the tableNumber
	 */
	public int getTableNumber() {
		return tableNumber;
	}
	/**
	 * @param tableNumber the tableNumber to set
	 */
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	/**
	 * @return the bidderID
	 */
	public int getbidderID() {
		return bidderID;
	}
	/**
	 * @param bidderID the bidderID to set
	 */
	public void setbidderID(int bidderID) {
		this.bidderID = bidderID;
	}
	/**
	 * @return the totalPurchase
	 */
	public double getTotalPurchase() {
		return totalPurchase;
	}
	/**
	 * @param totalPurchase the totalPurchase to set
	 */
	public void setTotalPurchase(double totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	/**
	 * @return the paid
	 */
	public boolean isPaid() {
		return paid;
	}
	/**
	 * @param paid the paid to set
	 */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	/**
	 * @return the prepay
	 */
	public boolean isPrepay() {
		return prepay;
	}
	/**
	 * @param prepay the prepay to set
	 */
	public void setPrepay(boolean prepay) {
		this.prepay = prepay;
	}
	/**
	 * @return the postPayMethod
	 */
	public String getPostPayMethod() {
		return postPayMethod;
	}
	/**
	 * @param postPayMethod the postPayMethod to set
	 */
	public void setPostPayMethod(String postPayMethod) {
		this.postPayMethod = postPayMethod;
	}
	
	
}
