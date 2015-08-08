package com.alesegdia.platgen.generator;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;
import com.alesegdia.platgen.util.RNG;
import com.alesegdia.platgen.util.Rect;
import com.alesegdia.platgen.util.Vec2;

public class SectorGenerator {
	
	public static final int numZones = 10;
	public static final Vec2 zoneWidthRange = new Vec2(6,12);
	public static final Vec2 deltaHeightRange = new Vec2(3,10);
	public static final Vec2 gapWidthRange = new Vec2(3,6);
	private TileMap tm;

	private List<Rect> blockList = new LinkedList<Rect>();
	RNG rng = new RNG();
	
	int lastX = 0;
	int lastY = 0;
	private Region region;
	
	public void Generate(Region r) {
		this.lastY = r.size.y/2;
		System.out.println(r.size.y/2);
		this.region = r;
		boolean lastWasGap = false;
		while( lastX < this.region.size.x) {
			boolean isZone = (rng.nextFloat() > 0.75) || lastWasGap;
			lastWasGap = !isZone;
			if( isZone ) {
				plotZone();
			} else {
				plotGap();
			}
		}
	}
	
	private void plotGap() {
		int width = rng.nextInt(gapWidthRange.x, gapWidthRange.y);
		region.createGapSector(lastX, width);
		lastX += width;
	}

	private void plotZone() {
		int width = rng.nextInt(zoneWidthRange.x, zoneWidthRange.y);
		int height = rng.nextInt(deltaHeightRange.x, deltaHeightRange.y);
		int sign = (rng.nextFloat() > 0.5 ? 1 : -1);
		
		region.createSolidSector(lastX, width, lastY + height * sign);
		lastX = lastX + width;
		lastY = lastY + height * sign;
	}

}
