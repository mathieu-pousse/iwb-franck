package iwb.repository.impl;


import java.util.List;

import com.google.common.base.Optional;

import iwb.bo.City;
import iwb.bo.Trash;
import iwb.repository.TrashDAO;

import org.bson.types.ObjectId;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component @Named("trashDAO")
public class TrashDAOImpl implements TrashDAO{

    private JongoCollection trashes;

    public TrashDAOImpl(@Named("trashes") JongoCollection trashes){
        this.trashes = trashes;
    }
    
    /* (non-Javadoc)
     * @see iwb.repository.TrashDAO#createTrash(iwb.bo.Trash)
     */
    public Trash createTrash(Trash trash) {
        trashes.get().save(trash);
        return  trash;
    }
    

    public Trash updateTrash(String oid, Trash trash) {
        checkEquals("oid", oid, "waste.id", trash.getId());
        trashes.get().save(trash);
        return trash;
    }

    public void deleteTrash(String oid) {
        trashes.get().remove(new ObjectId(oid));
    }

    public Optional<Trash> getTrashById(String oid) {
        return Optional.fromNullable(trashes.get().findOne(new ObjectId(oid)).as(Trash.class));
    }

    public Iterable<Trash> getTrashes() {
        return trashes.get().find().limit(5).as(Trash.class);
    }

	public Iterable<Trash> getTrashesByWasteType(String acr, int max) {
		if(acr == null|| acr.isEmpty()){
			return trashes.get().find("{type: #, cityCode: # }", "decheterie", "35238").limit(max).as(Trash.class);
		}else{
			return trashes.get().find("{type: #, wastesHandled: { $all: [#] }}", "PAV",acr).limit(max).as(Trash.class);
		}
	}
	
	public Iterable<Trash> getTrashesByWasteTypeLimitless(String acr) {
		if(acr == null|| acr.isEmpty()){
			return trashes.get().find("{type: #, cityCode: # }", "decheterie", "35238").as(Trash.class);
		}else{
			return trashes.get().find("{type: #, wastesHandled: { $all: [#] }}", "PAV",acr).as(Trash.class);
		}
	}
	
	
	public Iterable<Trash> getTrashesByWasteTypeForComponents(String acr, int max) {
		if(acr == null|| acr.isEmpty()){
			return trashes.get().find("{type: #, cityCode: # }", "decheterie", "35238").limit(max).as(Trash.class);
		}else{
			return trashes.get().find("{type: #}", "DOM").limit(5).as(Trash.class);
		}
	}
	
	
}
