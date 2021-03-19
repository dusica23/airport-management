package daon.management.repository;

import daon.management.dao.Gate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface GateDAO
      extends CrudRepository<Gate, Integer>
{
    Gate findFirstByOrderByAvailableDesc();

    @Transactional
    @Modifying
    @Query( "update Gate g set g.available = :availability where g.id = :id" )
    int setAvailability( boolean availability,
                         int id );
}
