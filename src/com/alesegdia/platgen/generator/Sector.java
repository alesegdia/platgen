package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.util.Rect;

public class Sector extends Rect {

	public Region region;
	
	public boolean isGap;
	public int height;

	public Sector(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
}
