package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;
import com.alesegdia.platgen.util.Vec2;

public class Generator {
	
	public static final int numZones = 10;
	public static final Vec2 zoneWidthRange = new Vec2(6,12);
	public static final int maxDeltaHeight = 3;
	
	public TileMap Generate() {
		TileMap tm = new TileMap(250,1000,TileType.WALL);
		
		return tm;
	}

}
