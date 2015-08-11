package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.tilemap.TileMap;

public class GeneratorPipeline {

	private LogicMap lm;
	
	public TileMap generate(Config cfg) {
		IRegionGenerator g;
		if( cfg.regionGeneratorType == ERegionGenerator.SIMPLE ) {
			g = new RegionGeneratorSimple();
		} else {
			g = new RegionGeneratorBalanced(cfg);
		}

		lm = g.Generate(cfg.mapSize.x, cfg.mapSize.y);
		SectorGenerator sg = new SectorGenerator(cfg);
		SectorCreatorVisitor scv = new SectorCreatorVisitor(sg);
		lm.regionTree.visit(scv);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMap tm = mr.raster();
		if( cfg.rasterRegionLimits ) {
			RegionOutlinerVisitor rov = new RegionOutlinerVisitor(tm);
			lm.regionTree.visit(rov);
		}
		return tm;
	}

	public LogicMap getLogicMap() {
		return lm;
	}

}
