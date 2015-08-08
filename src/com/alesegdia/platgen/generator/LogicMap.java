package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.util.Vec2;

public class LogicMap {

	public Vec2 size;
	public RegionTree regionTree;

	public LogicMap(int w, int h) {
		this.size = new Vec2(w,h);
		regionTree = new RegionTree(0,0,w,h);
	}
	
}
