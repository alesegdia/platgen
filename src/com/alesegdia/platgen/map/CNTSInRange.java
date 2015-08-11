package com.alesegdia.platgen.map;

public class CNTSInRange implements ICheckNearTilesSolver {

	private int max;
	private int min;

	public CNTSInRange( int min, int max ) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean process(int numNearTiles) {
		return numNearTiles > min && numNearTiles < max;
	}

}
