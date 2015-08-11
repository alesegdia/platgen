package com.alesegdia.platgen.generator;

import com.alesegdia.platgen.util.RNG;
import com.alesegdia.platgen.util.Vec2;

public class SectorGenerator {
	
	RNG rng = new RNG();
	
	int lastX = 0;
	int lastY = 0;
	private Region region;

	private Config cfg;
	
	public SectorGenerator(Config cfg) {
		this.cfg = cfg;
	}
	
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
		int width = rng.nextInt(cfg.gapWidthRange.x, cfg.gapWidthRange.y);
		region.createGapSector(lastX, width);
		lastX += width;
	}

	private void plotZone() {
		int width = rng.nextInt(cfg.zoneWidthRange.x, cfg.zoneWidthRange.y);
		int height = rng.nextInt(cfg.deltaHeightRange.x, cfg.deltaHeightRange.y);
		int sign = (rng.nextFloat() > 0.5 ? 1 : -1);
		
		int ySize = rng.nextInt(cfg.ySizeRange.x, cfg.ySizeRange.y);
		region.createSolidSector(lastX, width, lastY + height * sign, ySize);
		lastX = lastX + width;
		lastY = lastY + height * sign;
	}

}
