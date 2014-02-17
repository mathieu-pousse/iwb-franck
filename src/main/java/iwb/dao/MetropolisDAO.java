package iwb.dao;


import com.google.common.base.Optional;
import iwb.domain.Metropolis;

public interface MetropolisDAO {
    public Metropolis addMetropolis(Metropolis metropolis);
    public Metropolis updateMetropolis(String oid, Metropolis metropolis);
    public void deleteMetropolis(String oid);
    public Optional<Metropolis> getMetropolisById(String oid);
}
