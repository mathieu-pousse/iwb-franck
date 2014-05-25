package iwb.service.helpers;

import iwb.bo.Trash;

import java.util.Comparator;

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
