package com.alesegdia.platgen.test;

import com.alesegdia.platgen.generator.Generator;
import com.alesegdia.platgen.tilemap.TileMapRenderer;

public class MainTest {

	public static void main(String[] args) {
		Generator g = new Generator();
		TileMapRenderer tmr = new TileMapRenderer(g.Generate());
		tmr.Show();
	}

}
