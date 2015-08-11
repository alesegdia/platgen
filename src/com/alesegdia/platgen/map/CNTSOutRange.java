package com.alesegdia.platgen.map;

public class CNTSOutRange implements ICheckNearTilesSolver {

	private int max;
	private int min;

	public CNTSOutRange( int min, int max ) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean process(int numNearTiles) {
		return numNearTiles < min && numNearTiles > max;
	}

}
