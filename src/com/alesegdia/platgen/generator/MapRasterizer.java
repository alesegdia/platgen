package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;
import com.alesegdia.platgen.util.Rect;

public class MapRasterizer {

	private LogicMap map;

	public MapRasterizer( LogicMap m ) {
		this.map = m;
	}
	
	public TileMap raster() {
		TileMap tm = new TileMap(map.size.y, map.size.x, TileType.FREE);
		map.regionTree.visit(new RegionOutlinerVisitor(tm));
		map.regionTree.visit(new SectorPlotterVisitor(tm));
		return tm;
	}
	
}
