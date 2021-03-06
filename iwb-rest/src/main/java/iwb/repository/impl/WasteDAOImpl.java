package iwb.repository.impl;


import static restx.common.MorePreconditions.checkEquals;
import iwb.bo.Acronym;
import iwb.bo.Waste;
import iwb.repository.WasteDAO;

import javax.inject.Named;

import org.bson.types.ObjectId;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;

/**
 * Waste DAO implements methods to create, retreive, update and delete waste type
 */
@Component  @Named("wasteDAO")
public class WasteDAOImpl implements WasteDAO{

    private JongoCollection wastes;
    private JongoCollection acronyms;

    public WasteDAOImpl(@Named("wastes") JongoCollection wastes, @Named("acronyms") JongoCollection acronyms){
        this.wastes = wastes;
        this.acronyms = acronyms;
    }

    /**
     * Creates a new type of waste
     * @param waste
     * @return
     */
    public Waste createWaste(Waste waste) {
        wastes.get().save(waste);
        return waste;
    }

    /**
     * Updates the waste type that matches the oid parameter
     * @param oid
     * @param waste
     * @return
     */
    public Waste updateWaste(String oid, Waste waste) {
        checkEquals("oid", oid, "waste.id", waste.getId());
        wastes.get().save(waste);
        return waste;
    }

    /**
     * Deletes the waste type that matches the oid parameter
     * @param oid
     */
    public void deleteWaste(String oid) {
        wastes.get().remove(new ObjectId(oid));
    }

    /**
     * Returns the waste type that matches the oid parameter
     * @param oid
     * @return
     */
    public Optional<Waste> getWasteById(String oid) {
        return Optional.fromNullable(wastes.get().findOne(new ObjectId(oid)).as(Waste.class));
    }

    /**
     * Returns the waste type that matches the name parameter
     * @param name
     * @return
     */
    public Iterable<Waste> getWastesByName(String name) {
        return wastes.get().find("{name: #}",name).as(Waste.class);
    }

    /**
     * Returns all the waste types
     * @return
     */
    public Iterable<Waste> getWastes() {
        return wastes.get().find().as(Waste.class);
    }

	@Override
	public Iterable<Waste> getWasteByAcronym(String acronym) {
		return wastes.get().find("{acronym : #}",acronym).limit(5).as(Waste.class);
	}
	
	public Iterable<Acronym> getAcronyms(){
		return acronyms.get().find().as(Acronym.class);
	}
}
