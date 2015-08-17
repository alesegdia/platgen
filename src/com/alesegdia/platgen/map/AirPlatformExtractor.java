package com.alesegdia.platgen.map;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.platgen.util.Vec2;

public class AirPlatformExtractor {

	public class AirPlatform implements Comparable<AirPlatform> {
		public Vec2 xRange = new Vec2(0,0);
		public int height = 0;
		@Override
		public int compareTo(AirPlatform other) {
			int me = Math.abs(this.xRange.x - this.xRange.y);
			int it = Math.abs(other.xRange.x - other.xRange.y);
			return me - it;
		}
	}
	
	public List<AirPlatform> computeMobZones(TileMap tm) {
		List<AirPlatform> platforms = new ArrayList<AirPlatform>();
		
		for( int j = 1; j < tm.rows-1; j++ ) {
			int lastX = -1;
			for( int i = 0; i < tm.cols; i++ ) {
				int tt = tm.Get(j, i);
				int ttTop = tm.Get(j+1, i);
				if( tt == TileType.ONEWAYPLATFORM) {
					// start strike!
					if( lastX == -1 ) {
						lastX = i;
					}
				} else {
					// there was a strike
					if( lastX != -1 ) {
						// create and save
						AirPlatform mz = new AirPlatform();
						mz.xRange.Set(lastX, i-1);
						mz.height = j;
						platforms.add(mz);
						
						// start all over again
						lastX = -1;
					}
				}
			}
		}
		return platforms;
	}


}
