package iwb.service;

import iwb.bo.Item;
import iwb.bo.Waste;
import iwb.repository.ItemDAO;
import iwb.repository.WasteDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Optional;

public class ItemServiceImplTest {
	
	private ItemDAO itemDAO;
	private WasteDAO wasteDAO;
	private Item item;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		//
		item = new Item("12345678","Nutella","987654321");
		item.setWasteType(new Waste("12343678","verre d'emballage","VE"));
		itemDAO = Mockito.mock(ItemDAO.class);
		wasteDAO = Mockito.mock(WasteDAO.class);
		
		Mockito.when(itemDAO.getItemById(Mockito.anyString())).
		thenReturn(Optional.fromNullable(item));
		Mockito.when(wasteDAO.getWasteById(Mockito.anyString())).
		thenReturn(Optional.fromNullable(item.getWasteType()));
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetItemAndTrash() {
			
	}

}
