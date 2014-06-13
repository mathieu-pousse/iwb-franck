package iwb.service;

import iwb.bo.Acronym;
import iwb.bo.Waste;

import java.util.List;

import com.google.common.base.Optional;

public interface WasteService {
    public Waste createWaste(Waste waste);
    public Waste updateWaste(String oid, Waste waste);
    public void deleteWaste(String oid);
    public Optional<Waste> getWasteById(String oid);
    public Iterable<Waste> getWastesByName(String name);
    public Iterable<Waste> getWastes();
    public List<Acronym> getAcronyms();
}
