package iwb.repository.impl;


import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.repository.ItemDAO;
import org.bson.types.ObjectId;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import javax.inject.Named;
import java.util.List;

import static restx.common.MorePreconditions.checkEquals;

/**
 * Item DAO implements methods to create, retreive, update and delete items
 */
@Component @Named("itemDAO")
public class ItemDAOImpl implements ItemDAO {

    private JongoCollection items;

    public ItemDAOImpl(@Named("items") JongoCollection items) {
        this.items = items;
    }

    /**
     * Create a new item
     * @param item
     * @return
     */
    public Item createItem(Item item) {
        items.get().save(item);
        return item;
    }

    /**
     * Returns the Item that matches the oid parameter
     * @param oid oid of the Item to retreive
     * @return
     */
    public Optional<Item> getItemById(String oid) {
        return Optional.fromNullable(items.get().findOne(new ObjectId(oid)).as(Item.class));
    }

    /**
     * Returns the item that matches the barcode parameter
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByBarcode(String barcode) {
        return items.get().find("{barcode: #}", barcode).as(Item.class);
    }

    /**
     * Returns a list of all items
     * @return
     */
    public Iterable<Item> getItems() {
        return items.get().find().as(Item.class);
    }

    /**
     * Add a component to an item
     * @param item
     * @param comp
     */
    public void addConstituent(Item item, Constituent comp) {
        List<Constituent> components = Lists.newArrayList(item.getConstituents());
        if(!components.contains(comp))
            components.add(comp);
    }

    /**
     * Delete an item
     * @param id
     */
    public void deleteItem(String id) {
        items.get().remove(new ObjectId(id));
    }

    /**
     * Delete an item component
     * @param item
     * @param comp
     */
    public void deleteConstituent(Item item, Constituent comp) {
        List<Constituent> constituents = Lists.newArrayList(item.getConstituents());
        if(!constituents.contains(comp))
            constituents.remove(comp);

    }

    /**
     * Update a city and return a city updated
     * @param oid
     * @param item
     * @return
     */
    public Item updateItem(String oid, Item item) {
        checkEquals("oid", oid, "item.id", item.getId());
        items.get().save(item);
        return item;
    }
}
