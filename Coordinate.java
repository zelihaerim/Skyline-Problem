package assignment_7_zelihaerim_151044065;

import assignment_7_zelihaerim_151044065.CoordinateInterface;

public class Coordinate implements CoordinateInterface{
	private int x1;
	private int x2;
	private int y;
	public Coordinate(){}
	public Coordinate(int x1,int x2,int y) {
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
	}
	
	@Override
	public void setX1(int x1) {
		this.x1=x1;
	}
	@Override
	public void setX2(int x2) {
		this.x2 = x2;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}
	// copy constructor
	public Coordinate(Coordinate right){
		x1=right.x1;// deep copy 
		x2=right.x2;
		y=right.y;
	}// final methodlar @Override edilmedigi icin Interface yazmadim
	public final int getX1() {
		return x1;
	}
	public final int getX2() {
		return x2;
	}
	public final int getY() {
		return y;
	}
	

}
