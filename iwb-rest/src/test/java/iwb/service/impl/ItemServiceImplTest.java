package iwb.service.impl;

import static org.junit.Assert.assertTrue;
import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.bo.WasteCustom;
import iwb.repository.ItemDAO;
import iwb.repository.impl.ItemDAOImpl;
import iwb.service.helpers.ImageUrlHelper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.base.Optional;

public class ItemServiceImplTest {
	
	private ItemDAO itemDAO;
	private ItemServiceImpl itemService;
	private Item fakeItem;
	
	@Before
	public void setUp(){
		itemDAO = Mockito.mock(ItemDAOImpl.class);
		itemService = new ItemServiceImpl(itemDAO, new ImageUrlHelper("fake/image/url/"));
	}

	@Test
	public void testSetLinksItemWithoutConstituents() {
		fakeItem = initFakeItem();
		Item settedItem = itemService.setLinks(fakeItem);
		assertTrue(settedItem.getLink().getHref().equals("/items/fakeItemId"));
		assertTrue(settedItem.getLink().getRel().equals("alternative"));
		assertTrue(settedItem.getWasteType().getLink().getHref().equals("/wastes/fakeWasteId"));
		assertTrue(settedItem.getWasteType().getLink().getRel().equals("alternative"));
	}
	
	@Test
	public void testSetLinksItemWithConstituents(){
		fakeItem = initFakeItemWithConstituents();
		Item settedItem = itemService.setLinks(fakeItem);
		assertTrue(settedItem.getLink().getHref().equals("/items/fakeItemId"));
		assertTrue(settedItem.getLink().getRel().equals("alternative"));
		for(Constituent constituent : fakeItem.getConstituents()){
			assertTrue(constituent.getWasteType().getLink().getHref().equals("/wastes/fakeWasteId"));
			assertTrue(constituent.getWasteType().getLink().getRel().equals("alternative"));
		}
	}
	
	@Test
	public void testSetLinksOptional(){
		fakeItem = initFakeItemWithConstituents();
		Optional<Item> settedItem = itemService.setLinks(Optional.of(fakeItem));
		assertTrue(settedItem.get().getLink().getHref().equals("/items/fakeItemId"));
		assertTrue(settedItem.get().getLink().getRel().equals("alternative"));
		for(Constituent constituent : Optional.of(fakeItem).get().getConstituents()){
			assertTrue(constituent.getWasteType().getLink().getHref().equals("/wastes/fakeWasteId"));
			assertTrue(constituent.getWasteType().getLink().getRel().equals("alternative"));
		}
	}
	
	@Test
	public void testSetImages(){
		fakeItem = initFakeItemWithImages();
		Item settedItem = itemService.setImages(fakeItem);
		assertTrue(settedItem.getImage().equals("fake/image/url/fakeItemImage.jpg"));
		for(Constituent constituent : Optional.of(fakeItem).get().getConstituents()){
			assertTrue(constituent.getImage().equals("fake/image/url/fakeConstImage.jpg"));
		}
	}
	
	/**
	 * Test méthode setImages() sur des items n'ayant pas d'image
	 */
	@Test
	public void testSetEmptyImages(){
		fakeItem = initFakeItemWithConstituents();
		Item settedItem = itemService.setImages(fakeItem);
		assertTrue(settedItem.getImage() == null);
		for(Constituent constituent : Optional.of(fakeItem).get().getConstituents()){
			assertTrue(constituent.getImage() == null);
		}
	}
	
	public Item initFakeItem(){
		Item item = new Item();
		item.setId("fakeItemId");
		item.setWasteType(new WasteCustom("fakeWasteId", "fakeName", "fakeAcronym"));
		return item;
	}
	
	public Item initFakeItemWithConstituents(){
		Item item = new Item();
		item.setId("fakeItemId");
		List<Constituent> constituents  = new ArrayList<Constituent>();
		constituents.add(new Constituent("fakeConstituentName", new WasteCustom("fakeWasteId", "fakeName", "fakeAcronym"), null));
		constituents.add(new Constituent("fakeConstituentName", new WasteCustom("fakeWasteId", "fakeName", "fakeAcronym"), null));
		item.setConstituents(constituents);
		return item;
	}
	
	public Item initFakeItemWithImages(){
		Item item = new Item();
		item.setId("fakeItemId");
		item.setImage("fakeItemImage.jpg");
		List<Constituent> constituents  = new ArrayList<Constituent>();
		Constituent constituent = new Constituent("fakeConstituentName", new WasteCustom("fakeWasteId", "fakeName", "fakeAcronym"), null);
		constituent.setImage("fakeConstImage.jpg");
		constituents.add(constituent);
		item.setConstituents(constituents);
		return item;
	}

}
