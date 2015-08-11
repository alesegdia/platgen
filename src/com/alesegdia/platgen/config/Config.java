package com.alesegdia.platgen.config;

import com.alesegdia.platgen.util.Vec2;

public class Config {

	public Config() {}
	public ERegionGenerator regionGeneratorType = ERegionGenerator.SIMPLE;
	public Vec2 mapSize = new Vec2(0,0);

	// BALANCED REGION GENERATOR
	public int numRegions = 5;
	public float minK = 0.25f;
	public float maxK = 0.75f;

	// SECTOR GENERATOR CONFIG
	public Vec2 zoneWidthRange = new Vec2(6,40);
	public Vec2 deltaHeightRange = new Vec2(3,5);
	public Vec2 ySizeRange = new Vec2(10,20);
	public Vec2 gapWidthRange = new Vec2(3,6);
	public boolean rasterRegionLimits = false;

}