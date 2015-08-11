package com.alesegdia.platgen.region;

import com.alesegdia.platgen.util.Vec2;

public interface IRegionDivisionFitnessSolver {
	
	public float computeFitness(Vec2 size, boolean horizontal);

}
