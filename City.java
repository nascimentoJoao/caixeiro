public class City {
	
	private String name;
	private Double x;
	private Double y;

	public City(Double x, Double y, String name){
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	
	
}