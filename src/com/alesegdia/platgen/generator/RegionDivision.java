package com.alesegdia.platgen.generator;

public class RegionDivision implements Comparable<RegionDivision> {

	RegionTree r;
	boolean horizontal = false;
	
	public RegionDivision(RegionTree r, boolean horizontal) {
		this.r = r;
		this.horizontal = horizontal;
	}
	
	float value() {
		if( horizontal ) {
			return this.r.size.x / this.r.size.y;
		} else {
			return this.r.size.y / this.r.size.x;
		}
	}
	
	@Override
	public int compareTo(RegionDivision o) {
		return Float.compare(this.value(), o.value());
	}
	
	@Override
	public boolean equals(Object o) {
		if( this.getClass() != o.getClass() ) return false;
		RegionDivision rd = ((RegionDivision)o);
		return rd.r == this.r && rd.horizontal == this.horizontal;
	}
	
	@Override
	public String toString() {
		return horizontal + ", " + r.toString();
	}

}
