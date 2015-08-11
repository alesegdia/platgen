package com.alesegdia.platgen.test;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.config.ERegionGenerator;
import com.alesegdia.platgen.generator.GeneratorPipeline;
import com.alesegdia.platgen.map.CNTSGreaterThan;
import com.alesegdia.platgen.map.CNTSInRange;
import com.alesegdia.platgen.map.CNTSLessThan;
import com.alesegdia.platgen.map.CNTSOutRange;
import com.alesegdia.platgen.map.CheckNearBlocksConvolutor;
import com.alesegdia.platgen.map.ConditionalRemovalConvolutor;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.map.MapUtils;
import com.alesegdia.platgen.map.TileMap;
import com.alesegdia.platgen.map.TileMapRenderer;
import com.alesegdia.platgen.map.TileType;
import com.alesegdia.platgen.region.RegionOutlinerVisitor;
import com.alesegdia.platgen.util.Vec2;

public class Test_GeneratorPipelineWorkbench {

	public static void main(String[] args) {
		
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

		{
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

		/*
		{
			CheckNearBlocksConvolutor cnbc;
			cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), tm, 10,10, false);
			TileMap tmConvoluted = cnbc.convolute();
			tmConvoluted.RenderGUI(2);
			TileMap contoursOnly = new TileMap(tm.cols, tm.rows, TileType.FREE);
			RegionOutlinerVisitor rov = new RegionOutlinerVisitor(contoursOnly);
			lm.regionTree.visit(rov);
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), contoursOnly, 40,40,false);
			TileMap contoursOnlyConvoluted = cnbc.convolute();
			
			contoursOnlyConvoluted.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), tm, 20, 20, false);			
			TileMap tmConvoluted2 = cnbc.convolute();
			tmConvoluted2.RenderGUI(1);
			

			TileMap union = new TileMap(tm.cols, tm.rows, TileType.FREE);
			for( int i = 0; i < union.cols; i++ ) {
				for( int j = 0; j < union.rows; j++ ) {
					if( contoursOnlyConvoluted.Get(i,j) != TileType.ONEWAYPLATFORM &&
							tmConvoluted2.Get(i, j) == TileType.ONEWAYPLATFORM &&
							tmConvoluted2.Get(i, j) != TileType.WALL) {
						union.Set(i, j, TileType.ONEWAYPLATFORM);
					}
				}
			}
			
			union.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), union, 20, 20, false);
			cnbc.convolute().RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSOutRange(10,20), union, 20, 20, false);
			cnbc.convolute().RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), union, 20, 20, false);
			TileMap borders = cnbc.convolute();
			borders.RenderGUI(1);
			
			TileMap negated = MapUtils.NegateMap(borders, TileType.ONEWAYPLATFORM);
			negated.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSGreaterThan(50), negated, 100, 1, false, true);
			TileMap casi = cnbc.convolute();
			casi.RenderGUI(1);

			MapUtils.Union(tmConvoluted, casi).RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSGreaterThan(10), union, 20, 20, false);
			cnbc.convolute().RenderGUI(1);
			
			tmConvoluted.RenderGUI(2);
		}
		*/
	}
	
}
