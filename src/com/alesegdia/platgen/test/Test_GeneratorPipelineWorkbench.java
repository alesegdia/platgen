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
import com.alesegdia.platgen.map.PlatformGenerator;
import com.alesegdia.platgen.map.PlatformGenerator.Level;
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
		
		// generate platforms
		PlatformGenerator pg = new PlatformGenerator();
		pg.addLevel(new Level(10, 20, 10, 10));
		pg.addLevel(new Level(10, 20, 15, 15));
		pg.addLevel(new Level(10, 20, 25, 25));

		/*
		pg.addLevel(new Level(10, 20, 10, 10));
		pg.addLevel(new Level(10, 20, 15, 15));
		pg.addLevel(new Level(10, 20, 25, 25));
		pg.addLevel(new Level(20, 30, 35, 35));
		pg.addLevel(new Level(30, 40, 50, 50));
		pg.addLevel(new Level(50, 60, 100, 100));
		*/

		tm = pg.generate(tm);
		tm.RenderGUI(1);
		
		//tm = MapUtils.ReplaceTiles(tm, TileType.ONEWAYPLATFORM, TileType.WALL);
		//tm.RenderGUI(1);
		
		{
			CheckNearBlocksConvolutor cnbc;
			TileMap regionLimits, regionLimitsConvoluted, tmConvoluted;

			// create region limits map
			regionLimits = new TileMap(tm.cols, tm.rows, TileType.FREE);
			RegionOutlinerVisitor rov = new RegionOutlinerVisitor(regionLimits);
			lm.regionTree.visit(rov);
			
			// convolute region limits map
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), regionLimits, 40,40,false);
			regionLimitsConvoluted = cnbc.convolute();
			//regionLimitsConvoluted.RenderGUI(1);
			
			// convolute map with platforms
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), tm, 20, 20, false);			
			tmConvoluted = cnbc.convolute();
			//tmConvoluted.RenderGUI(1);
			

			TileMap union = new TileMap(tm.cols, tm.rows, TileType.FREE);
			for( int i = 0; i < union.cols; i++ ) {
				for( int j = 0; j < union.rows; j++ ) {
					if( regionLimitsConvoluted.Get(i,j) != TileType.ONEWAYPLATFORM &&
							tmConvoluted.Get(i, j) == TileType.ONEWAYPLATFORM &&
							tmConvoluted.Get(i, j) != TileType.WALL) {
						union.Set(i, j, TileType.ONEWAYPLATFORM);
					}
				}
			}
			
			union.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSInRange(10,20), union, 20, 20, false);
			//cnbc.convolute().RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), union, 20, 20, false);
			TileMap borders = cnbc.convolute();
			//borders.RenderGUI(1);
			
			TileMap negated = MapUtils.NegateMap(borders, TileType.ONEWAYPLATFORM);
			negated.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSGreaterThan(1), union, 3, 3, false);
			TileMap negatedGordo = cnbc.convolute();

			
			TileMap omgomg = MapUtils.Union(MapUtils.Intersect(negatedGordo, tm, TileType.ONEWAYPLATFORM),
					MapUtils.Extract(tmConvoluted, TileType.WALL));
			omgomg.RenderGUI(1);
			
			TileMap omgomgomgomg = new TileMap(omgomg);
			omgomgomgomg=MapUtils.ReplaceTiles(omgomgomgomg, TileType.ONEWAYPLATFORM, TileType.WALL);
			cnbc = new CheckNearBlocksConvolutor(new CNTSLessThan(10), omgomgomgomg, 20, 20, false);			
			omgomgomgomg = cnbc.convolute();
			omgomgomgomg.RenderGUI(1);
			
			TileMap onlyConv = MapUtils.Extract(omgomgomgomg, TileType.ONEWAYPLATFORM);
			onlyConv.RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSOutRange(300,420), omgomgomgomg, 70, 7, false, true);			
			MapUtils.Union(MapUtils.Extract(tm, TileType.WALL),
					MapUtils.Intersect(cnbc.convolute(), tm, TileType.ONEWAYPLATFORM)).RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSGreaterThan(50), negated, 100, 1, false, true);
			TileMap casi = cnbc.convolute();
			//casi.RenderGUI(1);

			//MapUtils.Union(tmConvoluted, casi).RenderGUI(1);
			
			cnbc = new CheckNearBlocksConvolutor(new CNTSGreaterThan(10), union, 20, 20, false);
			//cnbc.convolute().RenderGUI(1);
		}
	}
	
}
