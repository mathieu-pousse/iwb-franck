package iwb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.bo.Trash;
import iwb.bo.Waste;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.repository.ItemDAO;
import iwb.repository.TrashDAO;
import iwb.repository.WasteDAO;
import iwb.service.impl.ItemServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class ItemServiceImplTest {
	
	private ItemDAO itemDAO;
	private WasteDAO wasteDAO;
	private TrashDAO trashDAO;
	private Item item;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//
		item = new Item("12345678","Nutella","987654321");
		item.setWasteType(new Waste("12343678","verre d'emballage","VE"));
		itemDAO = Mockito.mock(ItemDAO.class);
		wasteDAO = Mockito.mock(WasteDAO.class);
		trashDAO = Mockito.mock(TrashDAO.class);
		
		Mockito.when(itemDAO.getItemById(Mockito.anyString())).
		thenReturn(Optional.fromNullable(item));
		Mockito.when(wasteDAO.getWasteById(Mockito.anyString())).
		thenReturn(Optional.fromNullable(item.getWasteType()));
		Iterable<Trash> list = initTrashList();
		Mockito.when(trashDAO.getTrashesByWasteTypeLimitless(Mockito.anyString())).
		thenReturn(list);
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetItemAndTrash() {
		ItemService itemService = new ItemServiceImpl(itemDAO,wasteDAO,trashDAO);
		Optional<GeoPoint2D> location = Optional.
		fromNullable(new GeoPoint2D(48.111933799999996,-1.6838946999999962));
		Optional<Item> itemResult = itemService.
		getItemAndTrash("12343678",Optional.fromNullable("true"),Optional.fromNullable("2"),location);
		
		assertEquals(null,itemResult.get().getConstituents());
		assertEquals(2,itemResult.get().getTrashes().size());
		
		double d1 = itemResult.get().getTrashes().get(0).getDistanceTo(); 
		double d2 = itemResult.get().getTrashes().get(1).getDistanceTo(); 
		if(d1>d2){
			fail("unsorted list");
		}
			
	}
	
	public Iterable<Constituent> initConstituents(){
		List<Constituent> constituents = Lists.newArrayList();
		Constituent const1 =  new Constituent();
		const1.setName("pot en verre");
		const1.setWasteType(new Waste("12343678","verre d'emballage","VE"));
		constituents.add(const1);
		Constituent const2 =  new Constituent();
		const2.setName("couvercle");
		const2.setWasteType(new Waste("32343678","plastique d'emballage","OM"));
		constituents.add(const2);
		return constituents;	
	}
	
	public Iterable<Waste> initWasteList(){
		List<Waste> list = Lists.newArrayList();
		Waste waste1 = new Waste("12343678","verre d'emballage","VE");
		list.add(waste1);
		Waste waste2 = new Waste("32343678","plastique d'emballage","OM");
		list.add(waste2);
		
		Iterable<Waste> wastes = list;
		return wastes;
	}
	
	public Iterable<Trash> initTrashList(){
		List<Trash> list = Lists.newArrayList();
		
		//
		Trash t1 = new Trash();
		t1.setId("531ddcb5e4b018e8b8d9f290");
		t1.setAddress("Place Sainte-Anne (cote rue Pont aux Foulons)");
		t1.setCityCode("35238");
		t1.setLatitude("48.1142817105565");
		t1.setLongitude("-1.68026953007078");
		t1.setType("PAV");
		List<String> wh1= new ArrayList<String>();
		wh1.add("MM");
		wh1.add("OM");
		t1.setWastesHandled(wh1);
		list.add(t1);
		
		//
		Trash t2 = new Trash();
		t2.setId("531ddcbGe4b018e8b8d9f290");
		t2.setAddress("Place Sainte-Anne (cote rue d Echange)");
		t2.setCityCode("35238");
		t2.setLatitude("48.1145448096629");
		t2.setLongitude("-1.68416624244859");
		t2.setType("PAV");
		List<String> wh2= new ArrayList<String>();
		wh2.add("VE");
		wh2.add("OM");
		t1.setWastesHandled(wh2);
		list.add(t2);
		
		//
		Trash t3 = new Trash();
		t3.setId("531ddcb5e4b018e8b8d7f290");
		t3.setAddress("Face au 49, rue de Dinan");
		t3.setCityCode("35238");
		t3.setLatitude("48.1158773236147");
		t3.setLongitude("-1.68416624244859");
		t3.setType("PAV");
		List<String> wh3= new ArrayList<String>();
		wh2.add("VE");
		t2.setWastesHandled(wh3);
		list.add(t3);
		
		return list;
	}

}
