package com.alesegdia.platgen.region;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.alesegdia.platgen.map.LogicMap;
import com.alesegdia.platgen.util.RNG;

public class RegionGeneratorSimple implements IRegionGenerator {
	
	RNG rng = new RNG();
	
	public static final int numRegions = 5;
	public static final float minK = 0.25f;
	public static final float maxK = 0.75f;
	
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
	
	public LogicMap Generate( int w, int h ) {
		lm = new LogicMap(w,h);
		List<RegionTree> children = new LinkedList<RegionTree>();
		rdset.clear();
		
		children.add(lm.regionTree);
		int vert = 0;
		
		
		for( int i = 0; i < numRegions; i++ ) {
			RegionTree current = children.get(rng.nextInt(0,children.size()-1));
			if( validForDivision(current) ) {
				float k = clamp(rng.nextFloat(), minK, maxK);
				boolean horizontal = rng.nextBoolean();
				if( vert > 2 ) {
					horizontal = true;
					vert = 0;
				} else {
					horizontal = false;
					vert++;
				}
				current.Divide(horizontal, k);
				children.remove((Object)current);
				children.add(current.A);
				children.add(current.B);
			} else {
				i--;
			}
		}

		return lm;
	}	

}
