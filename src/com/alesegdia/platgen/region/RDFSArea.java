package com.alesegdia.platgen.region;

import com.alesegdia.platgen.util.Vec2;

public class RDFSArea implements IRegionDivisionFitnessSolver {

	@Override
	public float computeFitness(Vec2 size, boolean horizontal) {
		float area = size.x * size.y;
		return 1f/area;
	}

}
