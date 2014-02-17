package iwb.repository;


import com.google.common.base.Optional;
import iwb.bo.Metropolis;

public interface MetropolisDAO {
    public Metropolis createMetropolis(Metropolis metropolis);
    public Metropolis updateMetropolis(String oid, Metropolis metropolis);
    public void deleteMetropolis(String oid);
    public Optional<Metropolis> getMetropolisById(String oid);
    public Iterable<Metropolis> getMetropolises();
}
