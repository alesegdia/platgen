package com.alesegdia.platgen.test;

import com.alesegdia.platgen.generator.LogicMap;
import com.alesegdia.platgen.generator.MapRasterizer;
import com.alesegdia.platgen.generator.RegionGenerator;
import com.alesegdia.platgen.generator.SectorCreatorVisitor;
import com.alesegdia.platgen.generator.SectorGenerator;
import com.alesegdia.platgen.tilemap.TileMapRenderer;

public class Test_RegionSectorGenerator {

	public static void main(String[] args) {
		RegionGenerator g = new RegionGenerator();
		LogicMap lm = g.Generate(200, 100);
		SectorGenerator sg = new SectorGenerator();
		SectorCreatorVisitor scv = new SectorCreatorVisitor(sg);
		lm.regionTree.visit(scv);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
