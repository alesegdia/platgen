package com.alesegdia.platgen.map;

public class MeasuredNearBlocksConvolutor extends TileMapConvolutor {

	public MeasuredNearBlocksConvolutor(TileMap tm, int winWidth, int winHeight, boolean useInAsOut) {
		super(tm, winWidth, winHeight, useInAsOut);
		// TODO Auto-generated constructor stub
	}
	
	float points = 0;

	@Override
	public void startWindowProcess(int i, int j) {
		points = 0;
	}

	@Override
	public void endWindowProcess(int i, int j) {
		if( points > 200f ) outMap.Set(i, j, TileType.DOORL);
	}

	float distance( float x1, float y1, float x2, float y2 ) {
		return (float) Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	@Override
	public void visitWindowTile(int tileType, int iAbs, int jAbs, int iRel, int jRel) {
		if( tileType != TileType.WALL ) {
			points += 1/distance(iAbs, jAbs, iRel, jRel);
		}
	}

}
