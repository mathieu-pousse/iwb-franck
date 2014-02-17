package iwb.repository.impl;


import com.google.common.base.Optional;
import iwb.bo.Metropolis;
import iwb.repository.MetropolisDAO;
import org.bson.types.ObjectId;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component @Named("metropolisDAO")
public class MetropolisDAOImpl implements MetropolisDAO {

    private JongoCollection metropolises;

    public MetropolisDAOImpl(@Named("metropolises") JongoCollection metropolises){
        this.metropolises = metropolises;
    }

    public Metropolis addMetropolis(Metropolis metropolis) {
        metropolises.get().save(metropolis);
        return metropolis;
    }

    public Metropolis updateMetropolis(String oid, Metropolis metropolis) {
        checkEquals("oid", oid, "Metropolis.id", metropolis.getId());
        metropolises.get().save(metropolis);
        return metropolis;
    }

    public void deleteMetropolis(String oid) {
        metropolises.get().remove(new ObjectId(oid));
    }

    public Optional<Metropolis> getMetropolisById(String oid) {
        return Optional.fromNullable(metropolises.get().findOne(new ObjectId(oid)).as(Metropolis.class));
    }
}
