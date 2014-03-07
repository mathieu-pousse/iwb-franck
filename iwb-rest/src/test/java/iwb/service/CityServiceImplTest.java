package iwb.service;

import static org.junit.Assert.*;
import iwb.bo.City;
import iwb.repository.CityDAO;
import iwb.repository.impl.CityDAOImpl;
import iwb.service.CityService;
import iwb.service.impl.CityServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class CityServiceImplTest {
	
	private CityDAO cityDAO;
	private City city;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Iterable<String> zipcodes = Lists.newArrayList("35385");
		city = new City("3564","Rennes", "35000",  zipcodes);
		cityDAO = Mockito.mock(CityDAOImpl.class);
		Mockito.when(cityDAO.getCityById(Mockito.anyString())).thenReturn(Optional.fromNullable(city));
	}

	@After
	public void tearDown() throws Exception {
		 Mockito.verify(cityDAO, Mockito.times(1)).getCityById(Mockito.anyString());
	}

	@Test
	public void testGetCityById() {
		CityService cityService = new CityServiceImpl(cityDAO);
		assertEquals(city,cityService.getCityById("d").get());

	}

}
