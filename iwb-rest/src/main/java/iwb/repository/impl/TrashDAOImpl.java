package iwb.repository.impl;


import com.google.common.base.Optional;
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
        return trashes.get().find().as(Trash.class);
    }
}
