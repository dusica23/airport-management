package daon.management.repository;

import daon.management.dao.Arrival;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface ArrivalDAO
      extends CrudRepository<Arrival, Integer>
{
    @Transactional
    int deleteByGateId( int gateId );
}
