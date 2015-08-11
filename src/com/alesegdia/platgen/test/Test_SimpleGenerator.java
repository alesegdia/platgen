package com.alesegdia.platgen.test;

import com.alesegdia.platgen.generator.SimpleGenerator;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileMapRenderer;

public class Test_SimpleGenerator {

	public static void main (String[] args) {
		SimpleGenerator sg = new SimpleGenerator();
		TileMap tm = sg.Generate();
		TileMapRenderer tmr = new TileMapRenderer(tm);
		tmr.Show();
	}
	
}
