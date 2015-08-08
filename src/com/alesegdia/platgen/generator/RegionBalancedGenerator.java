package com.alesegdia.platgen.generator;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.alesegdia.platgen.util.RNG;

public class RegionBalancedGenerator {
	
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
	
	void AddDivisionsFromList(List<RegionTree> rtlist) {
		for( RegionTree rt : rtlist ) {
			RegionDivision rd1 = new RegionDivision(rt, false);
			RegionDivision rd2 = new RegionDivision(rt, true);
			rdset.add(rd1);
			rdset.add(rd2);
		}
	}
	
	public LogicMap Generate( int w, int h ) {
		lm = new LogicMap(w,h);
		List<RegionTree> children = new LinkedList<RegionTree>();
		rdset.clear();
		
		children.add(lm.regionTree);
		int vert = 0;
		
		
		for( int i = 0; i < numRegions; i++ ) {
			this.AddDivisionsFromList(children);
			System.out.println("before add divisions: " + rdset.size());
			cleanSet();
			System.out.println("before cleanset: " + rdset.size());

			//RegionTree current = children.get(rng.nextInt(0,children.size()-1));
			List<RegionDivision> rdlist = new LinkedList<RegionDivision>();
			rdlist.addAll(rdset);
			Collections.sort(rdlist);
			System.out.println(rdlist.get(0));
			RegionDivision rd = rdlist.get(0);
			float k = clamp(rng.nextFloat(), minK, maxK);
			boolean horizontal = rd.horizontal;
			rd.r.Divide(horizontal, k);
			children.add(rd.r.A);
			children.add(rd.r.B);
			rdset.remove(rd);
			cleanSet();
			System.out.println(rdlist.size());
		}
		
		/*
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
		*/
		return lm;
	}	

}
