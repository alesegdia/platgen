package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.CNTSInRange;
import com.alesegdia.platgen.map.CheckNearBlocksConvolutor;
import com.alesegdia.platgen.map.ConditionalRemovalConvolutor;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapUtils;
import com.alesegdia.platgen.map.PlatformGenerator;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileType;
import com.alesegdia.platgen.map.PlatformGenerator.Level;
import com.alesegdia.platgen.util.Vec2;

public class Test_PlatformGeneratorSteps {
	
	public static void main( String args[] ) {
		// setup config
		Config cfg = new Config();
		cfg.mapSize = new Vec2(400,400);
		cfg.regionGeneratorType = ERegionGenerator.BALANCED;
		cfg.minK = 0.25f;
		cfg.maxK = 0.75f;
		cfg.numRegions = 7;
		cfg.rdfsType = ERDFSType.COMBINED;
		
		cfg.rasterRegionLimits = false;
		
		// generate map
		GeneratorPipeline gp = new GeneratorPipeline();
		TileMap tm = gp.generate(cfg);
		LogicMap lm = gp.getLogicMap();
		
		CheckNearBlocksConvolutor cnbc;
		TileMap oneWayPlats1, oneWayPlats2, oneWayPlats3,
				union, strikesRemoved, oneWayPlatsFinal;
		
		// generate level 1 one way platforms
		cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), tm, 10, 10, false);
		oneWayPlats1 = MapUtils.Extract(cnbc.convolute(),TileType.ONEWAYPLATFORM);

		// generate level 2 one way platforms
		cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(5,10), tm, 15, 15, false);			
		cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), tm, 15, 15, false);			
		oneWayPlats2 = MapUtils.Extract(cnbc.convolute(), TileType.ONEWAYPLATFORM);

		// generate level 2 one way platforms
		cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), tm, 25, 25, false);			
		oneWayPlats3 = MapUtils.Extract(cnbc.convolute(), TileType.ONEWAYPLATFORM);

		// join both levels
		union = MapUtils.Union(oneWayPlats3, MapUtils.Union(oneWayPlats2, MapUtils.Union(oneWayPlats1, tm)));
		union.RenderGUI(4);
		
		// remove strikes of less than 3
		strikesRemoved = MapUtils.RemoveHorizontalStrikesOfLessThan(union, TileType.ONEWAYPLATFORM, 3);
		
		ConditionalRemovalConvolutor crc = new ConditionalRemovalConvolutor(strikesRemoved, 3, 3, false);
		crc.convolute().RenderGUI(4);
		
		strikesRemoved = MapUtils.RemoveHorizontalStrikesOfLessThan(crc.convolute(), TileType.ONEWAYPLATFORM, 3);
		
		// join into map and render
		oneWayPlatsFinal = MapUtils.Union(strikesRemoved, tm);
		oneWayPlatsFinal.RenderGUI(4);
	}

}
