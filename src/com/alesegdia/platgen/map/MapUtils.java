package com.alesegdia.platgen.map;

public class MapUtils {

	public static TileMap NegateMap (TileMap tm, int tileToNegate ) {
		TileMap negated = new TileMap(tm.cols, tm.rows, TileType.FREE);
		for( int i = 0; i < tm.cols; i++ ) {
			for( int j = 0; j < tm.rows; j++ ) {
				if( tm.Get(i, j) != tileToNegate ) {
					negated.Set(i, j, tileToNegate);
				}
			}
		}
		return negated;
	}
	
	public static TileMap Union (TileMap tm1, TileMap tm2) {
		TileMap union = new TileMap(tm1);
		for( int i = 0; i < union.cols; i++ ) {
			for( int j = 0; j < union.rows; j++ ) {
				int tt = tm2.Get(i,j);
				if( tt != TileType.FREE ) {
					union.Set(i, j, tt);
				}
			}
		}
		return union;
	}
}
