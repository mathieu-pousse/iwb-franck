package iwb.service;


import iwb.bo.Trash;

import com.google.common.base.Optional;

public interface TrashService {
    public Trash createTrash(Trash waste);
    public Trash updateTrash(String oid, Trash trash);
    public void deleteTrash(String oid);
    public Optional<Trash> getTrashById(String oid);
    public Iterable<Trash> getTrashes();
}
