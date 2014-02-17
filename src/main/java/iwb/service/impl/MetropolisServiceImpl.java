package iwb.service.impl;


import com.google.common.base.Optional;
import iwb.bo.Metropolis;
import iwb.repository.MetropolisDAO;
import iwb.service.MetropolisService;
import restx.factory.Component;

import javax.inject.Named;

@Component @Named("metropolisService")
public class MetropolisServiceImpl implements MetropolisService{

    private MetropolisDAO metropolisDAO;

    public MetropolisServiceImpl(@Named("metropolisDAO") MetropolisDAO metropolisDAO){
        this.metropolisDAO = metropolisDAO;
    }

    public Metropolis createMetropolis(Metropolis metropolis) {
        return metropolisDAO.createMetropolis(metropolis);
    }

    public Metropolis updateMetropolis(String oid, Metropolis metropolis) {
        return metropolisDAO.updateMetropolis(oid, metropolis);
    }

    public void deleteMetropolis(String oid) {
        metropolisDAO.deleteMetropolis(oid);
    }

    public Optional<Metropolis> getMetropolisById(String oid) {
        return metropolisDAO.getMetropolisById(oid);
    }

    public Iterable<Metropolis> getMetropolises(){
        return metropolisDAO.getMetropolises();
    }
}
