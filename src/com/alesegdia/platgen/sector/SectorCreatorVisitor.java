package com.alesegdia.platgen.sector;

import com.alesegdia.platgen.region.IRegionTreeVisitor;
import com.alesegdia.platgen.region.RegionTree;

public class SectorCreatorVisitor implements IRegionTreeVisitor {

	private SectorGenerator sg;

	public SectorCreatorVisitor( SectorGenerator sg ) {
		this.sg = sg;
	}
	
	@Override
	public void process(RegionTree rt) {
		if( rt.isLeaf() ) {
			sg.Generate(rt);
		}
	}

}
