package iwb.repository;

import com.google.common.base.Optional;
import iwb.bo.Waste;


public interface WasteDAO {
    public Waste createWaste(Waste waste);
    public Waste updateWaste(String oid, Waste waste);
    public void deleteWaste(String oid);
    public Optional<Waste> getWasteById(String oid);
    public Iterable<Waste> getWastesByName(String name);
    public Iterable<Waste> getWastes();
}
