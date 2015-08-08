package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;

public class SectorPlotterVisitor implements IRegionTreeVisitor {

	private TileMap tm;

	SectorPlotterVisitor(TileMap tm) {
		this.tm = tm;
	}
	
	@Override
	public void process(RegionTree rt) {
		for( Sector s : rt.sectors ) {
			if( !s.isGap ) {
				for( int i = s.position.x;
					 i < s.position.x + s.size.x; i++ ) {
					for( int j = s.height; j < s.height + s.size.y; j++ ) {
						tm.Set(j, i, TileType.WALL);
					}
				}
			}
		}
	}

}
