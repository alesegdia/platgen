package com.alesegdia.platgen.map;

public class CNTSLessThan implements ICheckNearTilesSolver {

	private int value;

	public CNTSLessThan( int value ) {
		this.value = value;
	}
	
	@Override
	public boolean process(int numNearTiles) {
		return numNearTiles < value;
	}

}
