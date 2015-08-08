package com.alesegdia.platgen.generator;

public class RegionTree extends Region {

	public RegionTree(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.parent = null;
		this.A = null;
		this.B = null;
	}

	public RegionTree A;
	public RegionTree B;
	public RegionTree parent;
	public int level = 0;
	
	public void Divide(boolean horizontal, float k) {
		if( horizontal ) {
			int mid1 = (int) (this.size.y * k);
			int mid2 = this.size.y - mid1;
			A = new RegionTree(
					this.position.x,
					this.position.y,
					this.size.x,
					mid1);
			B = new RegionTree(
					this.position.x,
					this.position.y + mid1,
					this.size.x,
					mid2);
		} else {
			int mid1 = (int) (this.size.x * k);
			int mid2 = this.size.x - mid1;
			A = new RegionTree(
					this.position.x,
					this.position.y,
					mid1,
					this.size.y);
			B = new RegionTree(
					this.position.x + mid1,
					this.position.y,
					mid2,
					this.size.y);
		}
		
		A.level = this.level + 1;
		B.level = this.level + 1;
		A.parent = this;
		B.parent = this;
	}
	
	public boolean isLeaf() {
		return this.A == null && this.B == null;
	}
	
	public void visit( IRegionTreeVisitor visitor ) {
		visitor.process(this);
		if( A != null ) A.visit(visitor);
		if( B != null ) B.visit(visitor);
	}
		
}
