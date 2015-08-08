package com.alesegdia.platgen.generator;

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
