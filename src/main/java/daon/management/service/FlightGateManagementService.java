package daon.management.service;

import daon.management.dao.Flight;
import daon.management.dao.Gate;
import daon.management.exception.FlightConfirmationException;
import daon.management.exception.GateAvailabilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlightGateManagementService
{
    private static final String FLIGHT_DOES_NOT_EXIST = "Flight %d does not exist in our records.";

    private static final String NO_GATE_AVAILABLE = "We could not assigne gate for flight %d.";

    private static final Object sync = new Object();

    @Autowired
    private FlightService flightService;

    @Autowired
    private GateService gateService;

    @Autowired
    private ArrivalService arrivalService;


    public int assignGate( int flightId )
          throws FlightConfirmationException, GateAvailabilityException
    {
        final Flight flight = flightService.getFlightById( flightId );
        if ( flight == null )
        {
            throw new FlightConfirmationException( FLIGHT_DOES_NOT_EXIST );
        }


        final Gate gate = gateService.getAvailableGate();
        if ( gate != null )
        {
            synchronized ( sync )
            {
                if ( gate.getAvailable() )
                {
                    gateService.reserveGate( gate.getId() );
                }
                else
                {
                    throw new GateAvailabilityException( NO_GATE_AVAILABLE );
                }
            }
            arrivalService.saveArrival( flight, gate );
            return gate.getId();
        }
        throw new GateAvailabilityException( NO_GATE_AVAILABLE );
    }


    public boolean releaseGate( int gateId )
    {
        final Gate gate = gateService.findById( gateId );
        if ( gate == null )
        {
            return false;
        }
        gateService.releaseGate( gateId );
        arrivalService.removeScheduledArrival( gateId );
        return true;
    }
}
