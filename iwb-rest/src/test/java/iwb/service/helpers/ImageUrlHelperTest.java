package iwb.service.helpers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ImageUrlHelperTest {
	
	private ImageUrlHelper imgHelper;
	private String fakeUrl;
	
	@Before
	public void SetUp(){
		fakeUrl = "fake/image/url/";
		imgHelper = new ImageUrlHelper(fakeUrl);
	}

	@Test
	public void testAddBasePathUrl() {
		String imageWithoutPath = "imageNameFile.jpg";
		String imageWithPath = imgHelper.addBasePathUrl(imageWithoutPath);
		
		assertTrue(imageWithPath.indexOf(fakeUrl) == 0);
		assertTrue(imageWithPath.indexOf(imageWithoutPath)-fakeUrl.length() == 0);
	}
	
	@Test
	public void testAddBasePathUrlEmptyImage() {
		String imageWithoutPath = null;
		String imageWithPath = imgHelper.addBasePathUrl(imageWithoutPath);
		assertTrue(imageWithPath == null);
	}

	@Test
	public void testRemoveBasePathUrl() {
		String imageWithPath = fakeUrl.concat("imageNameFile.jpg");
		String imageWithoutPath = imgHelper.removeBasePathUrl(imageWithPath);
		
		assertTrue(imageWithoutPath.indexOf(fakeUrl) == -1);
		assertTrue(imageWithoutPath.equals("imageNameFile.jpg"));
	}
	
	@Test
	public void testRemoveBasePathUrlEmpty() {
		String imageWithPath = null;
		String imageWithoutPath = imgHelper.removeBasePathUrl(imageWithPath);
		
		assertTrue(imageWithoutPath == null);
	}

}
