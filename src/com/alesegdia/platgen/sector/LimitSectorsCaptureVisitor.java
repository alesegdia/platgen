package com.alesegdia.platgen.sector;

import java.util.ArrayList;
import java.util.List;

import com.alesegdia.platgen.region.IRegionTreeVisitor;
import com.alesegdia.platgen.region.Region;
import com.alesegdia.platgen.region.RegionTree;

public class LimitSectorsCaptureVisitor implements IRegionTreeVisitor {

	public class Entry {
		public Region region;
		public Sector first;
		public Sector last;
	}
	
	List<Entry> entries = new ArrayList<Entry>();
	
	@Override
	public void process(RegionTree rt) {
		if( rt.sectors.size() > 1 ) {
			Entry e = new Entry();
			
			e.first = rt.sectors.get(0);
			int i = 1;
			while( e.first.isGap ) {
				e.first = rt.sectors.get(i);
				i++;
			}
			
			e.last = rt.sectors.get(rt.sectors.size()-1);
			i = rt.sectors.size()-2;
			while( e.last.isGap ) {
				e.last = rt.sectors.get(i);
				i--;
			}
			e.region = rt;
			entries.add(e);
		} else {
			System.out.println(rt.sectors.size());
		}
	}

	public List<Entry> getEntries() {
		return entries;
	}

}
