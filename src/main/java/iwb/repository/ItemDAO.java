package iwb.repository;


import com.google.common.base.Optional;
import iwb.bo.Constituent;
import iwb.bo.Item;

public interface ItemDAO {
    public Item createItem(Item item);
    public Optional<Item> getItemById(String oid);
    public Optional<Item> getItemByBarcode(String barcode);
    public Iterable<Item> getItems();
    public void addConstituent(Item item, Constituent comp);
    public void deleteItem(String id);
    public void deleteConstituent(Item item, Constituent comp);
    public Item updateItem(String oid, Item item);
}
