package com.alesegdia.platgen.map;

public abstract class TileMapConvolutor {
	
	private int winWidth;
	private int winHeight;
	private TileMap inMap;
	private int halfWinWidth;
	private int halfWinHeight;
	protected TileMap outMap;

	public TileMapConvolutor( TileMap tm, int winWidth, int winHeight, boolean useInAsOut ) {
		this( tm, winWidth, winHeight, useInAsOut, false );
	}
	
	public TileMapConvolutor( TileMap tm, int winWidth, int winHeight, boolean useInAsOut, boolean useNewMap ) {
		this.inMap = tm;
		if( useInAsOut ) {
			this.outMap = inMap;
		} else {
			if( useNewMap ) {
				this.outMap = new TileMap(tm.cols, tm.rows, TileType.FREE);
			} else {
				this.outMap = new TileMap(tm);				
			}
		}
		this.winWidth = winWidth;
		this.winHeight = winHeight;
		this.halfWinWidth = winWidth/2;
		this.halfWinHeight = winHeight/2;
	}
	
	public TileMap convolute() {
		for( int i = 0; i < inMap.cols - winWidth; i++ ) {
			for( int j = 0; j < inMap.rows - winHeight; j++ ) {
				startWindowProcess(i + halfWinWidth, j + halfWinHeight);
				for( int ii = i; ii < i + winWidth; ii++ ) {
					for( int jj = j; jj < j + winHeight; jj++ ) {
						int tt = inMap.Get(ii, jj);
						visitWindowTile( tt, ii, jj, i + halfWinWidth, j + halfWinHeight );
					}
				}
				endWindowProcess(i + halfWinWidth, j + halfWinHeight);
			}
		}
		return outMap;
	}

	public abstract void startWindowProcess(int i, int j);
	public abstract void endWindowProcess(int i, int j);
	public abstract void visitWindowTile(int tileType, int iAbs, int jAbs, int iRel, int jRel);
	
}
