package iwb.service.impl;


import com.google.common.base.Optional;
import iwb.bo.Waste;
import iwb.repository.WasteDAO;
import iwb.service.WasteService;
import restx.factory.Component;

import javax.inject.Named;

@Component @Named("wasteService")
public class WasteServiceImpl implements WasteService {

    private WasteDAO wasteDAO;

    public WasteServiceImpl(@Named("wasteDAO") WasteDAO wasteDAO){
        this.wasteDAO = wasteDAO;
    }

    public Waste createWaste(Waste waste) {
        return wasteDAO.createWaste(waste);
    }

    public Waste updateWaste(String oid, Waste waste) {
        return wasteDAO.updateWaste(oid, waste);
    }

    public void deleteWaste(String oid) {
        wasteDAO.deleteWaste(oid);
    }

    public Optional<Waste> getWasteById(String oid) {
        return wasteDAO.getWasteById(oid);
    }

    public Iterable<Waste> getWastesByName(String name) {
        return wasteDAO.getWastesByName(name);
    }

    public Iterable<Waste> getWastes() {
        return wasteDAO.getWastes();
    }
}
