package iwb.repository.impl;

import com.google.common.base.Supplier;
import com.mongodb.MongoClient;
import iwb.bo.City;
import iwb.repository.CityDAO;
import org.hamcrest.CoreMatchers;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Test;
import restx.jongo.JongoCollection;
import com.mongodb.DB;

import javax.inject.Named;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
