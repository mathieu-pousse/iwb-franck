package iwb.repository;


import iwb.bo.Metropolis;

import com.google.common.base.Optional;

public interface MetropolisDAO {
    public Metropolis createMetropolis(Metropolis metropolis);
    public Metropolis updateMetropolis(String oid, Metropolis metropolis);
    public void deleteMetropolis(String oid);
    public Optional<Metropolis> getMetropolisById(String oid);
    public Iterable<Metropolis> getMetropolises();
}
