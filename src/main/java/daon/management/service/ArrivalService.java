package daon.management.service;

import daon.management.dao.Arrival;
import daon.management.dao.Flight;
import daon.management.dao.Gate;
import daon.management.repository.ArrivalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArrivalService
{
    @Autowired
    private ArrivalDAO arrivalDAO;


    public void removeScheduledArrival( int gateId )
    {
        arrivalDAO.deleteByGateId( gateId );
    }


    public void saveArrival( Flight flight,
                             Gate gate )
    {
        final Arrival arrival = new Arrival();
        arrival.setFlight( flight );
        arrival.setGate( gate );
        arrivalDAO.save( arrival );
    }
}
