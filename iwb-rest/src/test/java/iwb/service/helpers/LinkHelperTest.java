package iwb.service.helpers;

import static org.junit.Assert.*;
import iwb.bo.Item;
import iwb.bo.Link;

import org.junit.Before;
import org.junit.Test;

public class LinkHelperTest {
	
	private LinkHelper linkHelper;
	private Item item;
	
	@Before
	public void setUp(){
		//init linkHelper
		linkHelper = new LinkHelper();
		
		//init item
		item = new Item();
	}

	@Test
	public void testCreateLinkWithBOObject() {
		item.setId("fakeObjectId");
		
		Link expectedLink = new Link("alternative","/items/fakeObjectId");
		Link createdLink = linkHelper.createLink(item);
		
		assertTrue(createdLink.getHref().equals(expectedLink.getHref()));
		assertTrue(createdLink.getRel().equals(expectedLink.getRel()));
		assertTrue(item.getLink().getRel().equals(expectedLink.getRel()));
		assertTrue(item.getLink().getHref().equals(expectedLink.getHref()));
	}
	
	@Test
	public void testCreateLinkWithNonBOObject(){
		Link createdLink = linkHelper.createLink(new Object());
		assertTrue(createdLink == null);
	}
	
	@Test
	public void testCreateLinkWithNullObject(){
		Link createdLink = linkHelper.createLink(null);
		assertTrue(createdLink == null);
	}
	
	@Test
	public void testRemoveLink(){
		item.setId("fakeObjectId");
		linkHelper.createLink(item);
		linkHelper.removeLink(item);
		assertTrue(item.getLink() == null);
	}
	
	
	

}
