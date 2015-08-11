package com.alesegdia.platgen.map;

public class CheckNearBlocksConvolutor extends TileMapConvolutor {

	public CheckNearBlocksConvolutor(ICheckNearTilesSolver cnts, TileMap tm, int winWidth, int winHeight, boolean inAsOut) {
		super(tm, winWidth, winHeight, inAsOut);
		this.cnts = cnts;
	}

	public CheckNearBlocksConvolutor(ICheckNearTilesSolver cnts, TileMap tm, int ww, int wh, boolean inAsOut, boolean useNew) {
		
		super(tm, ww, wh, inAsOut, useNew);
		this.cnts = cnts;
	}

	@Override
	public void startWindowProcess(int i, int j) {
		// prepare for next
		numNearTiles = 0;
	}

	@Override
	public void visitWindowTile(int tt, int iAbs, int jAbs, int iRel, int jRel) {
		if( tt != TileType.FREE ) {
			numNearTiles++;
		}
	}

	int numNearTiles = 0;
	
	ICheckNearTilesSolver cnts;
	
	@Override
	public void endWindowProcess(int i, int j) {
		if( cnts.process(numNearTiles) ) outMap.Set(i, j, TileType.DOORL);
	}

}
