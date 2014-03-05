package iwb.service.impl;


import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import iwb.bo.CityCustom;
import iwb.bo.Link;
import iwb.bo.Metropolis;
import iwb.bo.TrashCustom;
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

    /**
     * Gets the metropolis created using the appropriate DAO method,
     * then, sets the links to all resources.
     * @param metropolis
     * @return
     */
    public Metropolis createMetropolis(Metropolis metropolis) {
        return setLinks(metropolisDAO.createMetropolis(metropolis));
    }

    /**
     * Uses the appropriate DAO method to update the metropolis matching the oid parameter,
     * then sets all the links to the resources.
     * @param oid
     * @param metropolis
     * @return
     */
    public Metropolis updateMetropolis(String oid, Metropolis metropolis) {
        return setLinks(metropolisDAO.updateMetropolis(oid, metropolis));
    }

    /**
     * Uses the appropriate DAO method to delete the metropolis matching the oid parameter.
     * @param oid
     */
    public void deleteMetropolis(String oid) {
        metropolisDAO.deleteMetropolis(oid);
    }

    /**
     * Gets the metropolis matching the oid parameter, sets the links to the resources and return the value
     * @param oid
     * @return
     */
    public Optional<Metropolis> getMetropolisById(String oid) {
        return setLinks(metropolisDAO.getMetropolisById(oid));
    }

    /**
     * Gets the entire metropolis collection, sets the links and return the value.
     * @return
     */
    public Iterable<Metropolis> getMetropolises(){
        Iterable<Metropolis> metropolises = Lists.newArrayList(metropolisDAO.getMetropolises());
        for(Metropolis metropolis : metropolises){
            setLinks(metropolis);
        }
        return metropolises;
    }

    /**
     * Sets all the links for the metropolis resource
     * @param metropolis
     */
    public Metropolis setLinks(Metropolis metropolis){
        metropolis.setLink(new Link("alternate", "/metropolises/"+metropolis.getId()));
        for(CityCustom city : metropolis.getCities()){
            city.setLink(new Link("alternate", "/cities/" + city.getId()));
        }
        for(TrashCustom trash : metropolis.getTrashes()){
            trash.setLink(new Link("alternate", "/trashes/" + trash.getId()));
        }
        return metropolis;
    }


    public Optional<Metropolis> setLinks(Optional<Metropolis> metropolis){
        metropolis.get().setLink(new Link("alternate", "/metropolises/" + metropolis.get().getId()));
        for(CityCustom city : metropolis.get().getCities()){
            city.setLink(new Link("alternate", "/cities/" + city.getId()));
        }
        for(TrashCustom trash : metropolis.get().getTrashes()){
            trash.setLink(new Link("alternate", "/trashes/"+trash.getId()));
        }
        return metropolis;
    }
}