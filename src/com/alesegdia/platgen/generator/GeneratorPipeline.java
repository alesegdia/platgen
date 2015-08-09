package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;
import com.alesegdia.platgen.util.Vec2;

public class GeneratorPipeline {

	public static class Config {
		public Config() {}
		public ERegionGenerator regionGeneratorType = ERegionGenerator.SIMPLE;
		public Vec2 mapSize = new Vec2(0,0);
	}

	private LogicMap lm;
	
	public TileMap generate(Config cfg) {
		IRegionGenerator g;
		if( cfg.regionGeneratorType == ERegionGenerator.SIMPLE ) {
			g = new RegionGeneratorSimple();
		} else {
			g = new RegionGeneratorBalanced();
		}

		lm = g.Generate(cfg.mapSize.x, cfg.mapSize.y);
		SectorGenerator sg = new SectorGenerator();
		SectorCreatorVisitor scv = new SectorCreatorVisitor(sg);
		lm.regionTree.visit(scv);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMap tm = mr.raster();
		return tm;
	}

	public LogicMap getLogicMap() {
		return lm;
	}

}
