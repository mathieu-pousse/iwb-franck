package iwb.service.impl;

import com.google.common.base.Optional;
import iwb.bo.Trash;
import iwb.repository.TrashDAO;
import iwb.service.TrashService;
import restx.factory.Component;

import javax.inject.Named;

@Component @Named("trashService")
public class TrashServiceImpl implements TrashService{

    private TrashDAO trashDAO;

    public TrashServiceImpl(@Named("trashDAO") TrashDAO trashDAO){
        this.trashDAO = trashDAO;
    }

    public Trash createTrash(Trash trash) {
        return trashDAO.createTrash(trash);
    }

    public Trash updateTrash(String oid, Trash trash) {
        return trashDAO.updateTrash(oid, trash);
    }

    public void deleteTrash(String oid) {
        trashDAO.deleteTrash(oid);
    }

    public Optional<Trash> getTrashById(String oid) {
        return trashDAO.getTrashById(oid);
    }

    public Iterable<Trash> getTrashes() {
        return trashDAO.getTrashes();
    }
}