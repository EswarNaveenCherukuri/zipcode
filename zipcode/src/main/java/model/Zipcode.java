package model;

public class Zipcode {
	
	private int minRange;
	private int maxRange;
	
	public Zipcode(int minRange, int maxRange) {
		super();
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	@Override
	public String toString() {
		return "Zipcode [minRange=" + minRange + ", maxRange=" + maxRange + "]";
	}
	
	
	

}
