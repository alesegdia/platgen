package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapRasterizer;
import com.alesegdia.platgen.map.TileMapRenderer;
import com.alesegdia.platgen.sector.SectorGenerator;

public class Test_SectorGenerator {

	public static void main(String[] args) {
		Config cfg = new Config();
		SectorGenerator g = new SectorGenerator(cfg);
		LogicMap lm = new LogicMap(150, 75);
		g.Generate(lm.regionTree);
		MapRasterizer mr = new MapRasterizer(lm);
		TileMapRenderer tmr = new TileMapRenderer(mr.raster());
		tmr.Show();
	}

}
