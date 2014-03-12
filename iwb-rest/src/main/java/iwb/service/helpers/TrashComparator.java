package iwb.service.helpers;

import java.util.Comparator;

import iwb.bo.Trash;
import iwb.bo.coordinates.GeoPoint2D;

public class TrashComparator implements Comparator<Trash>{

	@Override
	public int compare(Trash t1, Trash t2) {
		if(t1.getDistanceTo() < t2.getDistanceTo()){
			return -1;
		}else if(t1.getDistanceTo() > t2.getDistanceTo()){
			return 1;
		}else{
			return 0;
		}
	}

}
