package iwb.service.helpers;

import iwb.bo.Trash;
import iwb.bo.TrashCustom;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.repository.TrashDAO;
import iwb.repository.WasteDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrashHelper {

	public static List<TrashCustom> findMatchingTrashes(WasteDAO wasteDAO, TrashDAO trashDAO, String wasteId, GeoPoint2D location, int toIndex){
		List<Trash> trashList = new ArrayList<Trash>();
		List<TrashCustom> trashCustomList = new ArrayList<TrashCustom>();
		//get the waste acronym in order to find more easily in the trash collection
		String acronym = wasteDAO.getWasteById(wasteId).get().getAcronym();
		//return all the possible trashes
		Iterable<Trash> trashes = trashDAO.getTrashesByWasteTypeLimitless(acronym);
		//calculate the distance for all the possible trashes
		for(Trash trash : trashes){
			if(trash.getColor() != null){
				trash.setDistanceTo(0);
				trashList.add(trash);
				continue;
			}
			try{
				GeoPoint2D pt = new GeoPoint2D(trash.getLatitude(),trash.getLongitude());
				trash.setDistanceTo(location.calculateDistance(pt));
			}catch(NullPointerException n){	
			}finally{
				trashList.add(trash);
			}
		}
		//sort trashes by distance to the local point
		Collections.sort(trashList,new TrashComparator());
		//set list to return
		for(Trash trash : trashList.subList(0, toIndex)){
			trashCustomList.add(new TrashCustom(trash));
		}
		return trashCustomList;
	}
}
