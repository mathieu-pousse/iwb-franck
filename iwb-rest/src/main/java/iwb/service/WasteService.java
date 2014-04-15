package iwb.service;

import com.google.common.base.Optional;

import iwb.bo.Trash;
import iwb.bo.TrashCustom;
import iwb.bo.Waste;
import iwb.bo.coordinates.GeoPoint2D;

public interface WasteService {
    public Waste createWaste(Waste waste);
    public Waste updateWaste(String oid, Waste waste);
    public void deleteWaste(String oid);
    public Optional<Waste> getWasteById(String oid);
    public Iterable<Waste> getWastesByName(String name);
    public Iterable<Waste> getWastes();
    public Iterable<TrashCustom> getMatchingTrashesHome(String oid, Optional<String> nb, Optional<GeoPoint2D> location);
}
