package c01a3.data;

//Chart data
public class Data {
	private long price;
	private String type;
	private String color;
	
	public Data(String type, long price, String color) {
		this.type = type;
		this.price = price;
		this.color = color;
	}
	
	public String getType() {
		return type;
	}
	
	public long getPrice() {
		return price;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
}