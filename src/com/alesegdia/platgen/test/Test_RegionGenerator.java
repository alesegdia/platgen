package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapRasterizer;
import com.alesegdia.platgen.map.TileMapRenderer;
import com.alesegdia.platgen.region.RegionGeneratorBalanced;

public class Test_RegionGenerator {

	public static void main(String[] args) {
		Config cfg = new Config();
		RegionGeneratorBalanced g = new RegionGeneratorBalanced(cfg);
		LogicMap lm = g.Generate(150, 75);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
