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
	
	public static TileMap Extract( TileMap tm, int tt ) {
		TileMap extraction = new TileMap(tm.cols, tm.rows, TileType.FREE);
		for( int i = 0; i < extraction.cols; i++ ) {
			for( int j = 0; j < extraction.rows; j++ ) {
				int ttt = tm.Get(i,j);
				if( ttt == tt ) {
					extraction.Set(i, j, tt);
				}
			}
		}
		return extraction;
	}
	
	public static TileMap RemoveHorizontalStrikesOfLessThan( TileMap tm, int tt, int numStrike ) {
		TileMap ret = new TileMap(tm.cols, tm.rows, TileType.FREE);
		
		int currentStrike = 0;
		int lastStrikeX = -1;
		for( int j = 0; j < tm.rows; j++ ) {
			for( int i = 0; i < tm.cols; i++ ) {
				if( tm.Get(j, i) == tt ) {
					if( lastStrikeX == -1 ) {
						// start new strike
						lastStrikeX = i;
						currentStrike = 0;
					}
					currentStrike++;
				} else {
					// there is possible strike
					if( lastStrikeX != -1 ) {
						if( Math.abs(lastStrikeX - i) > numStrike ) {
							for( int ii = lastStrikeX; ii < i; ii++ ) {
								ret.Set(j,ii,tt);
							}
						}
					}
					currentStrike = 0;
					lastStrikeX = -1;
				}
			}
			currentStrike = 0;
			lastStrikeX = -1;
		}
		return ret;
	}
	
	public static TileMap ReplaceTiles( TileMap tm, int oldt, int newt ) {
		TileMap ret = new TileMap(tm);
		for( int i = 0; i < ret.cols; i++ ) {
			for( int j = 0; j < ret.rows; j++ ) {
				if( ret.Get(i, j) == oldt ) {
					ret.Set(i, j, newt);
				}
			}
		}
		return ret;
	}
	
}
