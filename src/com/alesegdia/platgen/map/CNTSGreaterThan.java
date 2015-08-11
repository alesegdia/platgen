package com.alesegdia.platgen.map;

public class CNTSGreaterThan implements ICheckNearTilesSolver {

	private int value;

	public CNTSGreaterThan( int value ) {
		this.value = value;
	}
	
	@Override
	public boolean process(int numNearTiles) {
		return numNearTiles > value;
	}

}
