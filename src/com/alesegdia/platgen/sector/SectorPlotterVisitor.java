package com.alesegdia.platgen.sector;

import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileType;
import com.alesegdia.platgen.region.IRegionTreeVisitor;
import com.alesegdia.platgen.region.RegionTree;

public class SectorPlotterVisitor implements IRegionTreeVisitor {

	private TileMap tm;

	public SectorPlotterVisitor(TileMap tm) {
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
