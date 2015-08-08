package com.alesegdia.platgen.test;

import com.alesegdia.platgen.generator.LogicMap;
import com.alesegdia.platgen.generator.MapRasterizer;
import com.alesegdia.platgen.generator.SectorGenerator;
import com.alesegdia.platgen.tilemap.TileMapRenderer;

public class Test_SectorGenerator {

	public static void main(String[] args) {
		SectorGenerator g = new SectorGenerator();
		LogicMap lm = new LogicMap(150, 75);
		g.Generate(lm.regionTree);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
