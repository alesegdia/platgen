package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.PlatformGenerator;
import com.alesegdia.platgen.map.TileMap;
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
		pg.generate(tm).RenderGUI(4);
	}

}
