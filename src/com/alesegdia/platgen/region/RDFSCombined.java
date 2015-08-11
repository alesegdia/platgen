package com.alesegdia.platgen.region;

import com.alesegdia.platgen.util.Vec2;

public class RDFSCombined implements IRegionDivisionFitnessSolver {

	RDFSRatio rdfsRatio = new RDFSRatio();
	RDFSArea rdfsArea = new RDFSArea();
	
	@Override
	public float computeFitness(Vec2 size, boolean horizontal) {
		float areaValue = rdfsArea.computeFitness(size, horizontal);
		float ratioValue = rdfsRatio.computeFitness(size, horizontal);
		
		return areaValue * ratioValue;
	}

}
