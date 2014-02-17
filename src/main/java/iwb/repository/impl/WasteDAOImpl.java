package iwb.repository.impl;


import com.google.common.base.Optional;
import iwb.bo.Waste;
import iwb.repository.WasteDAO;
import org.bson.types.ObjectId;
import restx.factory.Component;
import restx.jongo.JongoCollection;


import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component  @Named("wasteDAO")
public class WasteDAOImpl implements WasteDAO{

    private JongoCollection wastes;

    public WasteDAOImpl(@Named("wastes") JongoCollection wastes){
        this.wastes = wastes;
    }

    public Waste createWaste(Waste waste) {
        wastes.get().save(waste);
        return waste;
    }

    public Waste updateWaste(String oid, Waste waste) {
        checkEquals("oid", oid, "waste.id", waste.getId());
        wastes.get().save(waste);
        return waste;
    }

    public void deleteWaste(String oid) {
        wastes.get().remove(new ObjectId(oid));
    }

    public Optional<Waste> getWasteById(String oid) {
        return Optional.fromNullable(wastes.get().findOne(new ObjectId(oid)).as(Waste.class));
    }

    public Iterable<Waste> getWastesByName(String name) {
        return wastes.get().find("{name: #}",name).as(Waste.class);
    }

    public Iterable<Waste> getWastes() {
        return wastes.get().find().as(Waste.class);
    }
}
