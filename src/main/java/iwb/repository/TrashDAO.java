package iwb.repository;


import com.google.common.base.Optional;
import iwb.bo.Trash;

public interface TrashDAO {
    public Trash createTrash(Trash trash);
    public Trash updateTrash(String oid, Trash trash);
    public void deleteTrash(String oid);
    public Optional<Trash> getTrashById(String oid);
    public Iterable<Trash> getTrashes();
}
