package com.alesegdia.platgen.region;

import com.alesegdia.platgen.util.Vec2;

public class RDFSRatio implements IRegionDivisionFitnessSolver {

	@Override
	public float computeFitness(Vec2 size, boolean horizontal) {
		if( horizontal ) {
			//return this.r.size.y / this.r.size.x;
			return ((float)size.x) / ((float)size.y);
		} else {
			//return this.r.size.x / this.r.size.y;
			return ((float)size.y) / ((float)size.x);
		}
	}

}
