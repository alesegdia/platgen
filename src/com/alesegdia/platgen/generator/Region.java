package com.alesegdia.platgen.generator;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.platgen.util.Rect;

public class Region extends Rect {
	
	public List<Sector> sectors = new LinkedList<Sector>();

	public Region(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Sector CreateSector(int midX, int width) {
		Sector s = new Sector(this.position.x + midX, this.position.y, width, this.size.y);
		s.region = this;
		sectors.add(s);
		return s;
	}

}
