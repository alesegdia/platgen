package com.alesegdia.platgen.map;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.platgen.util.Vec2;

public class MobZoneExtractor {

	public class MobZone implements Comparable<MobZone> {
		public Vec2 xRange = new Vec2(0,0);
		public int height = 0;
		@Override
		public int compareTo(MobZone other) {
			int me = Math.abs(this.xRange.x - this.xRange.y);
			int it = Math.abs(other.xRange.x - other.xRange.y);
			return me - it;
		}
	}
	
	public List<MobZone> computeMobZones(TileMap tm) {
		List<MobZone> mobZones = new ArrayList<MobZone>();
		
		for( int j = 1; j < tm.rows-1; j++ ) {
			int lastX = -1;
			for( int i = 0; i < tm.cols; i++ ) {
				int tt = tm.Get(j, i);
				int ttTop = tm.Get(j+1, i);
				if( ttTop == TileType.FREE && tt == TileType.WALL ) {
					// start strike!
					if( lastX == -1 ) {
						lastX = i;
					}
				} else {
					// there was a strike
					if( lastX != -1 ) {
						// create and save
						MobZone mz = new MobZone();
						mz.xRange.Set(lastX, i-1);
						mz.height = j;
						mobZones.add(mz);
						
						// start all over again
						lastX = -1;
					}
				}
			}
		}
		return mobZones;
	}


}
