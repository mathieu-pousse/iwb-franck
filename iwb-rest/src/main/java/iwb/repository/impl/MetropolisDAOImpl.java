package iwb.repository.impl;


import static restx.common.MorePreconditions.checkEquals;
import iwb.bo.Metropolis;
import iwb.repository.MetropolisDAO;

import javax.inject.Named;

import org.bson.types.ObjectId;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;

/**
 * Metropolis DAO implements methods to create, retreive, update and delete metropolises
 * which are collections of cities with same recycling policies
 */
@Component @Named("metropolisDAO")
public class MetropolisDAOImpl implements MetropolisDAO {

    private JongoCollection metropolises;

    public MetropolisDAOImpl(@Named("metropolises") JongoCollection metropolises){
        this.metropolises = metropolises;
    }

    /**
     * Creates a metropolis
     * @param metropolis
     * @return
     */
    public Metropolis createMetropolis(Metropolis metropolis) {
        metropolises.get().save(metropolis);
        return metropolis;
    }

    /**
     * Updates a metropolis that matches the oid parameter
     * @param oid
     * @param metropolis
     * @return
     */
    public Metropolis updateMetropolis(String oid, Metropolis metropolis) {
        checkEquals("oid", oid, "Metropolis.id", metropolis.getId());
        metropolises.get().save(metropolis);
        return metropolis;
    }

    /**
     * Deletes a metropolis
     * @param oid
     */
    public void deleteMetropolis(String oid) {
        metropolises.get().remove(new ObjectId(oid));
    }

    /**
     * Returns the metropolis that matches the oid parameter
     * @param oid
     * @return
     */
    public Optional<Metropolis> getMetropolisById(String oid) {
        return Optional.fromNullable(metropolises.get().findOne(new ObjectId(oid)).as(Metropolis.class));
    }

    /**
     * Returns all the metropolises
     * @return
     */
    public Iterable<Metropolis> getMetropolises(){
        return metropolises.get().find().as(Metropolis.class);
    }
}
