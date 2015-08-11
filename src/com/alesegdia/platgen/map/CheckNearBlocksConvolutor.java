package com.alesegdia.platgen.map;

import com.alesegdia.platgen.util.Vec2;

public class CheckNearBlocksConvolutor extends TileMapConvolutor {

	public CheckNearBlocksConvolutor(TileMap tm, int winWidth, int winHeight) {
		super(tm, winWidth, winHeight, true);
		// TODO Auto-generated constructor stub
	}

	private Vec2 currentCenterTile;
	
	@Override
	public void startWindowProcess(int i, int j) {
		currentCenterTile = new Vec2(i,j);
	}

	@Override
	public void visitWindowTile(int tt, int iAbs, int jAbs, int iRel, int jRel) {
		if( tt != TileType.FREE ) {
			numNearTiles++;
		}
	}

	int numNearTiles = 0;
	
	@Override
	public void endWindowProcess(int i, int j) {
		if( numNearTiles == 0 ) outMap.Set(i, j, TileType.OPENED);

		// prepare for next
		numNearTiles = 0;
	}

}