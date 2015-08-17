package com.alesegdia.platgen.test;

import java.util.List;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.AirPlatformExtractor;
import com.alesegdia.platgen.map.AirPlatformExtractor.AirPlatform;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapUtils;
import com.alesegdia.platgen.map.MobZoneExtractor;
import com.alesegdia.platgen.map.MobZoneExtractor.MobZone;
import com.alesegdia.platgen.map.PlatformGenerator;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileType;
import com.alesegdia.platgen.map.PlatformGenerator.Level;
import com.alesegdia.platgen.util.Vec2;

public class Test_PlatformGenerator {
	
	public static void main( String args[] ) {
		// setup config
		Config cfg = new Config();
		cfg.mapSize = new Vec2(400,400);
		cfg.regionGeneratorType = ERegionGenerator.BALANCED;
		cfg.minK = 0.25f;
		cfg.maxK = 0.75f;
		cfg.numRegions = 7;
		cfg.rdfsType = ERDFSType.COMBINED;
		
		cfg.rasterRegionLimits = false;
		
		// generate map
		GeneratorPipeline gp = new GeneratorPipeline();
		TileMap tm = gp.generate(cfg);
		LogicMap lm = gp.getLogicMap();
		
		PlatformGenerator pg = new PlatformGenerator();
		pg.addLevel(new Level(10, 20, 10, 10));
		pg.addLevel(new Level(10, 20, 15, 15));
		pg.addLevel(new Level(10, 20, 25, 25));
		
		//pg.addLevel(new Level(20, 30, 35, 35));
		//pg.addLevel(new Level(30, 40, 50, 50));
		//pg.addLevel(new Level(50, 60, 100, 100));

		/*
		pg.addLevel(new Level(10, 20, 10, 10));
		pg.addLevel(new Level(10, 20, 15, 15));
		pg.addLevel(new Level(10, 20, 25, 25));
		pg.addLevel(new Level(20, 30, 35, 35));
		pg.addLevel(new Level(30, 40, 50, 50));
		pg.addLevel(new Level(50, 60, 100, 100));
		*/
		tm = pg.generate(tm);
		//tm.RenderGUI(4);
		
		TileMap tm2 = new TileMap(tm);
		
		MobZoneExtractor mze = new MobZoneExtractor();
		
		List<MobZone> mobZones = mze.computeMobZones(tm);
		for( MobZone mz : mobZones ) {
			for( int i = mz.xRange.x; i <= mz.xRange.y; i++ ) {
				tm2.Set(mz.height, i, TileType.MOBZONE);
			}
			tm2.Set(mz.height-1, mz.xRange.x-1, TileType.MOBLIMIT);
			tm2.Set(mz.height-1, mz.xRange.y+1, TileType.MOBLIMIT);
		}
		tm2.RenderGUI(1);
		
		AirPlatformExtractor ape = new AirPlatformExtractor();
		List<AirPlatform> platforms = ape.computeMobZones(tm);
		System.out.println(platforms.size());
	}

}
