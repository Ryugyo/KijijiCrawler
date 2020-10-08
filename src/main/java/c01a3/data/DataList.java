package c01a3.data;

//Rental data
public class DataList {
	private int buildID;
	private String bedrooms;
	private float bathrooms;
	private int size;
	private String type;
	private String address;
	private long price;
	
	public DataList(int buildID, String type, String address, long price, String bedrooms, float bathrooms2, int size) {
		this.buildID = buildID;
		this.type = type;
		this.address = address;
		this.price = price;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms2;
		this.size = size;
	}
	
	public int getBuildID() {
		return buildID;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAddress() {
		return address;
	}
	
	public long getPrice() {
		return price;
	}
	
	public String getBedrooms() {
		return bedrooms;
	}
	
	public float getBathrooms() {
		return bathrooms;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setBuildID(int buildID) {
		this.buildID = buildID;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}
	
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
}