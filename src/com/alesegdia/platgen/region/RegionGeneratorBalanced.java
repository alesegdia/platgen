package com.alesegdia.platgen.region;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.alesegdia.platgen.config.Config;
import com.alesegdia.platgen.config.ERDFSType;
import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.util.RNG;

public class RegionGeneratorBalanced implements IRegionGenerator {
	
	RNG rng = new RNG();
	
	float clamp(float k, float max, float min) {
		//return Math.min(max, Math.max(k, min));
		if( k < min ) return min;
		if( k > max ) return max;
		return k;
	}
	
	boolean validForDivision(RegionTree rt) {
		return rt.level <= 2 && rt.size.x > 20 && rt.size.y > 10;
	}
	
	Set<RegionDivision> rdset = new HashSet<RegionDivision>();

	private LogicMap lm;

	private Config cfg;
	
	public void cleanSet() {
		List<RegionDivision> remove_rds = new LinkedList<RegionDivision>();
		for( RegionDivision rd : rdset ) {
			if( !rd.r.isLeaf() ) {
				remove_rds.add(rd);
			}
		}

		for( RegionDivision rd : remove_rds ) {
			rdset.remove(rd);
		}
	}
	
	IRegionDivisionFitnessSolver rdfs;
	
	void AddDivisionsFromList(List<RegionTree> rtlist) {
		for( RegionTree rt : rtlist ) {
			RegionDivision rd1 = new RegionDivision(rt, false, rdfs);
			RegionDivision rd2 = new RegionDivision(rt, true, rdfs);
			rdset.add(rd1);
			rdset.add(rd2);
		}
	}
	
	public RegionGeneratorBalanced(Config cfg) {
		this.cfg = cfg;
		switch( cfg.rdfsType ) {
		case AREA: 	rdfs = new RDFSArea(); break;
		case RATIO: rdfs = new RDFSRatio(); break;
		case COMBINED: rdfs = new RDFSCombined(); break;
		default: 	rdfs = new RDFSDummy(); break;
		}
	}
	
	public LogicMap Generate( int w, int h ) {
		lm = new LogicMap(w,h);
		List<RegionTree> children = new LinkedList<RegionTree>();
		rdset.clear();
		children.add(lm.regionTree);
		
		for( int i = 0; i < cfg.numRegions; i++ ) {
			this.AddDivisionsFromList(children);
			cleanSet();

			List<RegionDivision> rdlist = new LinkedList<RegionDivision>();
			rdlist.addAll(rdset);
			Collections.sort(rdlist);

			RegionDivision rd = rdlist.get(0);
			float k = clamp(rng.nextFloat(), cfg.minK, cfg.maxK);
			boolean horizontal = rd.horizontal;
			rd.r.Divide(horizontal, k);
			children.add(rd.r.A);
			children.add(rd.r.B);
			rdset.remove(rd);
		}
		
		return lm;
	}	

}

