package iwb.service.impl;


import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import iwb.bo.Link;
import iwb.bo.Trash;
import iwb.bo.TrashCustom;
import iwb.bo.Waste;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.repository.TrashDAO;
import iwb.repository.WasteDAO;
import iwb.service.WasteService;
import iwb.service.helpers.TrashHelper;
import restx.factory.Component;

import javax.inject.Named;

@Component @Named("wasteService")
public class WasteServiceImpl implements WasteService {

    private WasteDAO wasteDAO;
    private TrashDAO trashDAO;

    public WasteServiceImpl(@Named("wasteDAO") WasteDAO wasteDAO, @Named("trashDAO") TrashDAO trashDAO){
        this.wasteDAO = wasteDAO;
        this.trashDAO = trashDAO;
    }

    /**
     *
     * @param waste
     * @return
     */
    public Waste createWaste(Waste waste) {
        return setLinks(wasteDAO.createWaste(waste));
    }

    /**
     *
     * @param oid
     * @param waste
     * @return
     */
    public Waste updateWaste(String oid, Waste waste) {
        return setLinks(wasteDAO.updateWaste(oid, waste));
    }

    /**
     *
     * @param oid
     */
    public void deleteWaste(String oid) {
        wasteDAO.deleteWaste(oid);
    }

    /**
     *
     * @param oid
     * @return
     */
    public Optional<Waste> getWasteById(String oid) {
        return setLinks(wasteDAO.getWasteById(oid));
    }

    /**
     *
     * @param name
     * @return
     */
    public Iterable<Waste> getWastesByName(String name) {
        Iterable<Waste> wastes = Lists.newArrayList(wasteDAO.getWastesByName(name));
        for(Waste waste : wastes){
            setLinks(waste);
        }
        return wastes;
    }

    /**
     *
     * @return
     */
    public Iterable<Waste> getWastes() {
        Iterable<Waste> wastes = Lists.newArrayList(wasteDAO.getWastes());
        for(Waste waste : wastes){
            setLinks(waste);
        }
        return wastes;
    }

    /**
     *
     * @param waste
     * @return
     */
    public Waste setLinks(Waste waste){
        waste.setLink(new Link("alternate", "/wastes/"+waste.getId()));

        return waste;
    }

    /**
     *
     * @param waste
     * @return
     */
    public Optional<Waste> setLinks(Optional<Waste> waste){
        waste.get().setLink(new Link("alternate", "/wastes/"+waste.get().getId()));
        return waste;
    }

    
	public Iterable<TrashCustom> getMatchingTrashesHome(String oid, Optional<String> nb, Optional<GeoPoint2D> location) {
		if(nb.isPresent() && location.isPresent()){
			int toIndex = 5;
			try{
				toIndex = Integer.parseInt(nb.get());
			}catch(NumberFormatException nfe){}
			return TrashHelper.findMatchingTrashes(wasteDAO, trashDAO, oid, location.get(), toIndex);
		}else{
			return null;
		}
	}
}
