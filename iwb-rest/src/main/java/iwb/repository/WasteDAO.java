package iwb.repository;

import iwb.bo.Waste;

import com.google.common.base.Optional;


public interface WasteDAO {
    public Waste createWaste(Waste waste);
    public Waste updateWaste(String oid, Waste waste);
    public void deleteWaste(String oid);
    public Optional<Waste> getWasteById(String oid);
    public Iterable<Waste> getWastesByName(String name);
    public Iterable<Waste> getWastes();
    public Iterable<Waste> getWasteByAcronym(String acronym);
}
