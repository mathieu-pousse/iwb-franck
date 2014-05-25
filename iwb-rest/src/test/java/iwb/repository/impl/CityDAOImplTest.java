package iwb.repository.impl;

import org.junit.Test;

/**
 * Created by franckylm on 2/25/14.
 */
public class CityDAOImplTest {

    @Test
    public void testCreateCity() throws Exception {

        /*DB db = new MongoClient().getDB("iwb-dev");
        Jongo jongo = new Jongo(db);
        final MongoCollection  cities = jongo.getCollection("cities");
        JongoCollection ct =  new JongoCollection() {
            @Override
            public String getName() {
                return "cities";
            }

            @Override
            public MongoCollection get() {
                return cities;
            }
        };

        CityDAO cityDAO = new CityDAOImpl(ct);

        City city = new City();
        city.setName("Rennes");
        cityDAO.createCity(city);

        assertThat(cityDAO.getCityByName("Rennes"), notNullValue());
        */

    }
}
