package com.alesegdia.platgen.map;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.platgen.util.Vec2;

public class PlatformGenerator {
	
	public static class Level {
		public Level( int rx, int ry, int ww, int wh ) {
			cntsRange = new Vec2(rx, ry);
			winSize = new Vec2(ww, wh);
		}
		public Vec2 cntsRange = new Vec2(0,0);
		public Vec2 winSize = new Vec2(0,0);
	}
	
	public PlatformGenerator() {
		
	}
	
	List<Level> levels = new ArrayList<Level>();
	
	public void addLevel(Level lvl) {
		levels.add(lvl);
	}
	
	public TileMap generate(TileMap tm) {
		CheckNearBlocksConvolutor cnbc;
		
		List<TileMap> platformLevels = new ArrayList<TileMap>();
		for( Level l : levels ) {
			cnbc = new CheckNearBlocksConvolutor(
					new CNTSInRange(l.cntsRange.x, l.cntsRange.y), tm, l.winSize.x, l.winSize.y, false, true);
			platformLevels.add(cnbc.convolute());
		}
		
		TileMap union = new TileMap(tm.cols, tm.rows, TileType.FREE);
		for( TileMap t : platformLevels ) {
			union = MapUtils.Union(union, t);
		}

		// remove strikes of less than 3
		TileMap strikesRemoved = MapUtils.RemoveHorizontalStrikesOfLessThan(union, TileType.ONEWAYPLATFORM, 3);
		
		ConditionalRemovalConvolutor crc = new ConditionalRemovalConvolutor(strikesRemoved, 3, 3, false);
		strikesRemoved = MapUtils.RemoveHorizontalStrikesOfLessThan(crc.convolute(), TileType.ONEWAYPLATFORM, 3);
		
		// join into map and render
		TileMap oneWayPlatsFinal = MapUtils.Union(strikesRemoved, tm);
		return oneWayPlatsFinal;
	}

}
