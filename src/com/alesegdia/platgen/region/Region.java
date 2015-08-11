package com.alesegdia.platgen.region;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.platgen.sector.Sector;
import com.alesegdia.platgen.util.Rect;

public class Region extends Rect {
	
	public List<Sector> sectors = new LinkedList<Sector>();

	public Region(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Sector createSolidSector(int midX, int width, int height, int ySize) {
		Sector s = createSector(midX, width, false);
		s.height = height;
		s.size.y = ySize;
		return s;
	}
	
	public Sector createGapSector(int midX, int width) {
		return createSector(midX, width, true);
	}
	
	Sector createSector(int midX, int width, boolean gap) {
		Sector s = new Sector(this.position.x + midX, this.position.y, width, this.size.y);
		s.region = this;
		s.isGap = gap;
		sectors.add(s);
		return s;
	}
	
}
