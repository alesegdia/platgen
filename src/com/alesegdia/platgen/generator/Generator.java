package com.alesegdia.platgen.generator;

import java.util.LinkedList;
import java.util.List;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;
import com.alesegdia.platgen.util.RNG;
import com.alesegdia.platgen.util.Rect;
import com.alesegdia.platgen.util.Vec2;

public class Generator {
	
	public static final int numZones = 20;
	public static final Vec2 zoneWidthRange = new Vec2(6,12);
	public static final int maxDeltaHeight = 10;
	private TileMap tm;

	private List<Rect> blockList = new LinkedList<Rect>();
	RNG rng = new RNG();
	
	public TileMap Generate() {
		tm = new TileMap(50,200,TileType.FREE);
		int lastX = 0;
		int lastY = 25;
		for( int i = 0; i < numZones; i++ ) {
			int width = rng.nextInt(zoneWidthRange.x, zoneWidthRange.y);
			int height = rng.nextInt(maxDeltaHeight);
			int sign = (rng.nextFloat() > 0.5 ? 1 : -1);
			plotFloor(lastX, lastX + width, lastY + height * sign);
			lastX = lastX + width;
			lastY = lastY + height * sign;
		}
		rasterMap();
		return tm;
	}
	
	private void plotFloor(int xFrom, int xTo, int height) {
		Rect r = new Rect(xFrom, tm.rows - height, Math.abs(xFrom - xTo), height);
		blockList.add(r);
	}
	
	private void rasterMap() {
		for( Rect r : blockList ) {
			for( int i = r.position.x; i < r.position.x + r.size.x; i++ ) {
				for( int j = r.position.y; j < r.position.y + r.size.y; j++ ) {
					tm.Set(j, i, TileType.WALL);
				}
			}
		}
	}

}
