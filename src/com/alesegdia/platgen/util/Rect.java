package com.alesegdia.platgen.util;

public class Rect {
	
	public Vec2 position = new Vec2(0,0);
	public Vec2 size = new Vec2(0,0);
	
	public Rect (int x, int y, int w, int h) {
		this.position.Set(x,y);
		this.size.Set(w,h);
	}
	@Override
	public String toString() {
		return "[" + position.x + "," + position.y + " - " + size.x + "," + size.y + "]";
	}


}
