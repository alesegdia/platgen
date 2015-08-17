package com.alesegdia.platgen.map;

import com.alesegdia.platgen.sector.SectorPlotterVisitor;

public class MapRasterizer {

	private LogicMap map;

	public MapRasterizer( LogicMap m ) {
		this.map = m;
	}
	
	public TileMap raster() {
		TileMap tm = new TileMap(map.size.y*2, map.size.x*2, TileType.FREE);
		//map.regionTree.visit(new RegionOutlinerVisitor(tm));
		map.regionTree.visit(new SectorPlotterVisitor(tm));
		return tm;
	}
	
}
