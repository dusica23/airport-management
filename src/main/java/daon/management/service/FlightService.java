package daon.management.service;

import daon.management.dao.Flight;
import daon.management.repository.FlightDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FlightService
{
    @Autowired
    private FlightDAO flightDAO;


    public void saveFlight( Flight flight )
    {
        flightDAO.save( flight );
    }


    public Flight getFlightById( int flightId )
    {
        final Optional<Flight> flight = flightDAO.findById( flightId );

        return flight.isPresent() ? flight.get() : null;
    }
}
