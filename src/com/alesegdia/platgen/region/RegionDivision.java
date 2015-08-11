package com.alesegdia.platgen.region;

class RegionDivision implements Comparable<RegionDivision> {

	RegionTree r;
	boolean horizontal = false;
	private IRegionDivisionFitnessSolver rdfs;
	
	public RegionDivision(RegionTree r, boolean horizontal, IRegionDivisionFitnessSolver rdfs) {
		this.r = r;
		this.horizontal = horizontal;
		this.rdfs = rdfs;
	}
	
	float value() {
		return rdfs.computeFitness(this.r.size, this.horizontal);
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