package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;

public class MapRasterizer {

	private LogicMap map;

	public MapRasterizer( LogicMap m ) {
		this.map = m;
	}
	
	public TileMap raster() {
		TileMap tm = new TileMap(map.size.y, map.size.x, TileType.FREE);
		map.regionTree.visit(new RegionOutlinerVisitor(tm));
		return tm;
	}
	
}
