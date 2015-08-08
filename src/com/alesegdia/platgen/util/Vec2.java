package com.alesegdia.platgen.util;

public class Vec2 {

	public Vec2(int x, int y) {
		Set(x,y);
	}

	public void Set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x;
	public int y;
	
	public String toString()
	{
		return "(" + this.x + "," + this.y + ")";
	}

	public Vec2 Add(Vec2 globalPosition) {
		return new Vec2( this.x + globalPosition.x, this.y + globalPosition.y );
	}

	public Float distance(Vec2 other) {
		return (float) Math.sqrt( Math.pow(this.x - other.x,2) + Math.pow(this.y - other.y, 2) );
	}

	public void Set(Vec2 other) {
		this.x = other.x;
		this.y = other.y;
	}
	
}
