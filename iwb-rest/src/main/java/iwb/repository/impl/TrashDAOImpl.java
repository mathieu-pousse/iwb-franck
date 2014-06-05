package iwb.repository.impl;


import static restx.common.MorePreconditions.checkEquals;
import iwb.bo.Trash;
import iwb.repository.TrashDAO;
import iwb.repository.helpers.MongoHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.bson.types.ObjectId;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;



@Component @Named("trashDAO")
public class TrashDAOImpl implements TrashDAO{

    private JongoCollection trashes;
    private MongoHelper mongoHelper;

    public TrashDAOImpl(@Named("trashes") JongoCollection trashes, @Named("mongoHelper") MongoHelper helper){
        this.trashes = trashes;
        this.mongoHelper = helper;
    }
    
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
    
    public Trash getTrashHome(String acr){
    	return trashes.get().findOne("{type: 'HOME', wastesHandled: #}", acr).as(Trash.class);
    }

	public Iterable<Trash> getTrashesByWasteType(String acr, int max) {
		
		BasicDBObject query = new BasicDBObject();
		query.append("geoNear", "trashes");
		double[] location = {-1.6838059, 48.1004064};
		query.append("near", location);
		query.append("spherical", true);
		query.append("maxDistance", 1);
		query.append("query", new BasicDBObject("wastesHandled", acr));
		query.append("limit", max);
		
		CommandResult results = mongoHelper.getDb().command(query);
		BasicDBList list = (BasicDBList) results.get("results");
		List<Trash> trashList = new ArrayList<Trash>();
		
		for(Object obj : list){
			BasicDBObject bObj = (BasicDBObject) obj;
			double distance =  (double) bObj.get("dis");
			Trash trash =  new Trash((BasicDBObject) bObj.get("obj"));
			trash.setDistanceTo(distance);
		    trashList.add(trash);
		}
		
		return trashList;
		
	}
	
	
}
