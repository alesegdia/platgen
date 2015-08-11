package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapRasterizer;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.region.IRegionGenerator;
import com.alesegdia.platgen.region.RegionGeneratorBalanced;
import com.alesegdia.platgen.region.RegionGeneratorSimple;
import com.alesegdia.platgen.region.RegionOutlinerVisitor;
import com.alesegdia.platgen.sector.SectorCreatorVisitor;
import com.alesegdia.platgen.sector.SectorGenerator;

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
