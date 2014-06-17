package iwb.service;


import iwb.bo.Trash;

import com.google.common.base.Optional;

public interface TrashService {
    public Trash createTrash(Trash waste);
    public Trash updateTrash(String oid, Trash trash);
    public void deleteTrash(String oid);
    public Optional<Trash> getTrashById(String oid);
    public Iterable<Trash> getTrashes();
    public Iterable<Trash> getTrashesByWasteType(String wasteType, int max);
    public Trash getTrashHome(String acr);
    public Iterable<Trash> getTrashesPagination(final int NUMBER_OF_ITEMS, final int PAGE_NUMBER);
}
