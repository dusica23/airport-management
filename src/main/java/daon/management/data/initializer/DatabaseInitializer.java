package daon.management.data.initializer;

import daon.management.dao.Flight;
import daon.management.dao.Gate;
import daon.management.service.FlightService;
import daon.management.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class DatabaseInitializer
{

    private static final String GATE_MARK = "G";

    private static final String FLIGHT_MARK = "F";

    @Value( "${number.of.gates.minimum}" )
    private int minimumNumOfGates;

    @Value( "${number.of.flights.minimum}" )
    private int minumumNumOfFlights;

    @Value( "${number.of.flights.increment}" )
    private int safeIncrement;

    @Autowired
    private FlightService flightService;

    @Autowired
    private GateService gateService;


    public void init()
    {
        Random random = new Random();
        int numberOfGates = random.nextInt( minimumNumOfGates ) + minimumNumOfGates;
        for ( int i = 0; i < numberOfGates; i++ )
        {
            addNewGateToDatabase( i );
        }

        int numberOfFlights = minimumNumOfGates * ( random.nextInt( minumumNumOfFlights ) + safeIncrement );
        for ( int i = 0; i < numberOfFlights; i++ )
        {
            addNewFlightToDatabase( i );
        }
    }


    private void addNewFlightToDatabase( final int i )
    {
        Flight flight = new Flight();
        flight.setFlightName( FLIGHT_MARK + i );
        flightService.saveFlight( flight );
    }


    private void addNewGateToDatabase( final int i )
    {
        Gate gate = new Gate();
        gate.setGateMark( GATE_MARK + i );
        gate.setAvailable( true );
        gateService.saveGate( gate );
    }
}
