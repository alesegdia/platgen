package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.tilemap.TileType;
import com.alesegdia.platgen.util.Rect;

public class SectorPlotterVisitor implements IRegionTreeVisitor {

	private TileMap tm;

	SectorPlotterVisitor(TileMap tm) {
		this.tm = tm;
	}
	
	@Override
	public void process(RegionTree rt) {
		for( Sector s : rt.sectors ) {
			if( !s.isGap ) {
				System.out.println("ZONE!");
				for( int i = s.region.position.x + s.position.x;
					 i < s.region.position.x + s.position.x + s.size.x; i++ ) {
					for( int j = s.height; j < s.size.y; j++ ) {
						tm.Set(j, i, TileType.WALL);
					}
				}
			} else {
				System.out.println("GAP!");
			}
		}
	}

}
