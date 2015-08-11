package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapRasterizer;
import com.alesegdia.platgen.map.TileMapRenderer;
import com.alesegdia.platgen.region.IRegionGenerator;
import com.alesegdia.platgen.region.RegionGeneratorBalanced;
import com.alesegdia.platgen.region.RegionGeneratorSimple;
import com.alesegdia.platgen.sector.SectorCreatorVisitor;
import com.alesegdia.platgen.sector.SectorGenerator;

public class Test_RegionSectorGenerator {

	public static void main(String[] args) {
		Config cfg = new Config();
		IRegionGenerator g = new RegionGeneratorBalanced(cfg);
		//RegionGenerator g = new RegionGenerator();
		LogicMap lm = g.Generate(200, 100);
		SectorGenerator sg = new SectorGenerator(cfg);
		SectorCreatorVisitor scv = new SectorCreatorVisitor(sg);
		lm.regionTree.visit(scv);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
