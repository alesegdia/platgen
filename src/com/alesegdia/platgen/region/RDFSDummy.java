package com.alesegdia.platgen.region;

import com.alesegdia.platgen.util.Vec2;

public class RDFSDummy implements IRegionDivisionFitnessSolver {

	@Override
	public float computeFitness(Vec2 size, boolean horizontal) {
		return 0;
	}

}
