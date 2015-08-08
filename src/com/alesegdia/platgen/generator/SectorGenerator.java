package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.util.RNG;
import com.alesegdia.platgen.util.Vec2;

public class SectorGenerator {
	
	public static final Vec2 zoneWidthRange = new Vec2(1,3); // 6,12);
	public static final Vec2 deltaHeightRange = new Vec2(1,2); //3,10);
	public static final Vec2 ySizeRange = new Vec2(1,2); //20,40);
	public static final Vec2 gapWidthRange = new Vec2(1,2); //3,6);

	RNG rng = new RNG();
	
	int lastX = 0;
	int lastY = 0;
	private Region region;
	
	public void Generate(Region r) {
		this.region = r;
		this.lastX = 0;
		this.lastY = r.position.y + r.size.y/2;
		boolean lastWasGap = false;
		while( lastX < this.region.size.x) {
			boolean isZone = (rng.nextFloat() > 0.5) || lastWasGap;
			lastWasGap = !isZone;
			if( isZone ) {
				plotZone();
			} else {
				plotGap();
			}
		}
	}
	
	private void plotGap() {
		int width = rng.nextInt(gapWidthRange.x, gapWidthRange.y);
		region.createGapSector(lastX, width);
		lastX += width;
	}

	private void plotZone() {
		int width = rng.nextInt(zoneWidthRange.x, zoneWidthRange.y);
		int height = rng.nextInt(deltaHeightRange.x, deltaHeightRange.y);
		int sign = (rng.nextFloat() > 0.5 ? 1 : -1);
		
		int ySize = rng.nextInt(ySizeRange.x, ySizeRange.y);
		region.createSolidSector(lastX, width, lastY + height * sign, ySize);
		lastX = lastX + width;
		lastY = lastY + height * sign;
	}

}
