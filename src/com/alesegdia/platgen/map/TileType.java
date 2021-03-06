package com.alesegdia.platgen.map;

public class TileType {
	public static final int WALL=1; // muro
	public static final int LASTSECTOR=2; // puerta
	public static final int FREE=0; // hueco libre
	public static final int USED=3; // hueco libre perteneciente a una habitación
	public static final int ONEWAYPLATFORM = 8;
	public static final int FIRSTSECTOR = 9;
	public static final int MOBZONE = 10;
	public static final int OPENED = 11;
	public static final int REGIONLIMIT = 12;
	public static final int MOBLIMIT = 13;
	public static int ConvertFromString(String string) {
		switch(string) {
		case "free": return FREE; 
		case "door": return LASTSECTOR; 
		case "wall": return WALL; 
		case "used": return USED;
		}
		return 0;
	}
}
