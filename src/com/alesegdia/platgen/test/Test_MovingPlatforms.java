package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.PlatformGenerator;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileType;
import com.alesegdia.platgen.sector.LimitSectorsCaptureVisitor;
import com.alesegdia.platgen.sector.LimitSectorsCaptureVisitor.Entry;
import com.alesegdia.platgen.sector.Sector;
import com.alesegdia.platgen.map.PlatformGenerator.Level;
import com.alesegdia.platgen.util.Vec2;

public class Test_MovingPlatforms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		tm = pg.generate(tm);
		TileMap tm2 = new TileMap(tm);
		tm2.RenderGUI(1);

		LimitSectorsCaptureVisitor lscv = new LimitSectorsCaptureVisitor();
		lm.regionTree.visit(lscv);
		
		for( Entry e : lscv.getEntries() ) {
			Sector f = e.first;
			for( int i = f.position.x; i < f.position.x + f.size.x; i++ ) {
				for( int j = f.height; j < f.height + f.size.y; j++ ) {
					tm.Set(j, i, TileType.FIRSTSECTOR);
				}
			}
			Sector l = e.last;
			for( int i = l.position.x; i < l.position.x + l.size.x; i++ ) {
				for( int j = l.height; j < l.height + l.size.y; j++ ) {
					tm.Set(j, i, TileType.LASTSECTOR);
				}
			}
		}
		tm.RenderGUI(2);
	}
}
