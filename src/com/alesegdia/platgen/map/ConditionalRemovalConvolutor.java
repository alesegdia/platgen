package com.alesegdia.platgen.map;

public class ConditionalRemovalConvolutor extends TileMapConvolutor {

	public ConditionalRemovalConvolutor(TileMap tm, int winWidth, int winHeight, boolean useInAsOut) {
		super(tm, winWidth, winHeight, useInAsOut);
	}

	public ConditionalRemovalConvolutor(TileMap tm, int winWidth, int winHeight, boolean useInAsOut, boolean useNew) {
		super(tm, winWidth, winHeight, useInAsOut, useNew);
	}

	int numTiles = 0;
	
	@Override
	public void startWindowProcess(int i, int j) {
		numTiles = 0;
	}

	@Override
	public void endWindowProcess(int i, int j) {
		if( numTiles > 2 ) {
			outMap.Set(i, j, TileType.FREE);
		}
	}

	@Override
	public void visitWindowTile(int tileType, int iAbs, int jAbs, int iRel, int jRel) {
		if( iAbs == iRel && jAbs == jRel) {
			// ignore plz
		} else {
			if( tileType != TileType.FREE ) {
				numTiles++;
			}
		}
	}

}
