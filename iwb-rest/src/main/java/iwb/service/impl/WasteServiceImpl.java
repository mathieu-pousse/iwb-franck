package iwb.service.impl;


import java.util.ArrayList;
import java.util.List;

import iwb.bo.Acronym;
import iwb.bo.AcronymEnum;
import iwb.bo.Link;
import iwb.bo.Waste;
import iwb.repository.TrashDAO;
import iwb.repository.WasteDAO;
import iwb.service.WasteService;

import javax.inject.Named;

import restx.factory.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

@Component @Named("wasteService")
public class WasteServiceImpl implements WasteService {

    private WasteDAO wasteDAO;

    public WasteServiceImpl(@Named("wasteDAO") WasteDAO wasteDAO, @Named("trashDAO") TrashDAO trashDAO){
        this.wasteDAO = wasteDAO;
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
    
    public List<Acronym> getAcronyms(){
    	return Lists.newArrayList(wasteDAO.getAcronyms());
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
}
