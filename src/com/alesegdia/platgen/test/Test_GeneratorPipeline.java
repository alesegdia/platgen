package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileMapRenderer;
import com.alesegdia.platgen.util.Vec2;

public class Test_GeneratorPipeline {

	public static void main(String[] args) {
		
		// setup config
		Config cfg = new Config();
		cfg.mapSize = new Vec2(400,400);
		cfg.regionGeneratorType = ERegionGenerator.BALANCED;
		cfg.minK = 0.25f;
		cfg.maxK = 0.25f;
		cfg.numRegions = 5;
		
		cfg.rasterRegionLimits = true;
		
		// generate map
		GeneratorPipeline gp = new GeneratorPipeline();
		TileMap tm = gp.generate(cfg);
		
		// render it
		TileMapRenderer tmr = new TileMapRenderer(tm);
		tmr.setTileSize(1);
		tmr.Show();
	}
	
}
