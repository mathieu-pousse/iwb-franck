package iwb.service.impl;

import iwb.bo.Link;
import iwb.bo.Trash;
import iwb.repository.TrashDAO;
import iwb.service.TrashService;

import javax.inject.Named;

import restx.factory.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;


@Component @Named("trashService")
public class TrashServiceImpl implements TrashService{

    private TrashDAO trashDAO;

    public TrashServiceImpl(@Named("trashDAO") TrashDAO trashDAO){
        this.trashDAO = trashDAO;
    }

    public Trash createTrash(Trash trash) {
        return setLinks(trashDAO.createTrash(trash));
    }

    public Trash updateTrash(String oid, Trash trash) {
        return setLinks(trashDAO.updateTrash(oid, trash));
    }

    public void deleteTrash(String oid) {
        trashDAO.deleteTrash(oid);
    }

    public Optional<Trash> getTrashById(String oid) {
        return setLinks(trashDAO.getTrashById(oid));
    }

    public Iterable<Trash> getTrashes() {
        Iterable<Trash> trashes = Lists.newArrayList(trashDAO.getTrashes());
        for(Trash trash : trashes){
            setLinks(trash);
        }
        return trashes;
    }

    public Trash setLinks(Trash trash){
        trash.setLink(new Link("alternate", "/trashes/"+trash.getId()));
        /*for(Waste waste : trash.getWastesHandled()){
            waste.setLink(new Link("alternate", "/wastes/"+waste.getId()));
        }*/
        return trash;
    }

    public Optional<Trash> setLinks(Optional<Trash> trash){
        trash.get().setLink(new Link("alternate", "/trashes/" + trash.get().getId()));
        /*for(Waste waste : trash.get().getWastesHandled()){
            waste.setLink(new Link("alternate", "/wastes/"+waste.getId()));
        }*/
        return trash;
    }
    
}