package com.alesegdia.platgen.region;

import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileType;

public class RegionOutlinerVisitor implements IRegionTreeVisitor {

	private TileMap tm;

	public RegionOutlinerVisitor(TileMap tm) {
		this.tm = tm;
	}
	
	@Override
	public void process(RegionTree rt) {
		if( rt.isLeaf() ) {
			OutlineRegion(rt);
		}
	}
	
	void OutlineRegion(Region r) {
		// UP
		for( int i = r.position.x; i < r.position.x + r.size.x; i++ ) {
			tm.Set(r.position.y, i, TileType.WALL);
		}
		// DOWN
		for( int i = r.position.x; i < r.position.x + r.size.x; i++ ) {
			tm.Set(r.position.y + r.size.y-1, i, TileType.OPENED);
		}
		// LEFT
		for( int i = r.position.y; i < r.position.y + r.size.y; i++ ) {
			tm.Set(i, r.position.x, TileType.DOORL);
		}
		// RIGHT
		for( int i = r.position.y; i < r.position.y + r.size.y; i++ ) {
			tm.Set(i, r.position.x + r.size.x-1, TileType.USED);
		}
	}

}
