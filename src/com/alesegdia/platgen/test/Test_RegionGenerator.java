package com.alesegdia.platgen.test;

import com.alesegdia.platgen.generator.LogicMap;
import com.alesegdia.platgen.generator.MapRasterizer;
import com.alesegdia.platgen.generator.RegionGenerator;
import com.alesegdia.platgen.generator.SimpleGenerator;
import com.alesegdia.platgen.tilemap.TileMapRenderer;

public class Test_RegionGenerator {

	public static void main(String[] args) {
		RegionGenerator g = new RegionGenerator();
		LogicMap lm = g.Generate(150, 75);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
