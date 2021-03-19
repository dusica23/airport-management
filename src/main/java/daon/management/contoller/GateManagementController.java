package daon.management.contoller;

import daon.management.exception.FlightConfirmationException;
import daon.management.exception.GateAvailabilityException;
import daon.management.service.FlightGateManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping( path = "/gate/management" )
public class GateManagementController
{
    private static final String FLIGHT_ASSIGNED_TO_GATE = "Flight %d successfully assigned to gate %d";

    private static final String GATE_RELEASED = "Gate %d successfully released";

    private static final String GATE_DOES_NOT_EXITS = "Gate %d does not exist in database";

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";

    @Autowired
    private FlightGateManagementService flightGateManagementService;


    @PostMapping( path = "/release" )
    public String releaseGate( @RequestBody Integer gateNumber )
    {
        boolean gateReleased = flightGateManagementService.releaseGate( gateNumber.intValue() );
        return gateReleased ? String.format( GATE_RELEASED, gateNumber.intValue() ) : String.format( GATE_DOES_NOT_EXITS, gateNumber.intValue() );
    }


    @PostMapping( path = "/assign" )
    public String assignGate( @RequestBody Integer flightNumber )
    {
        int gateAssigned = -1;
        String message = "";
        try
        {
            gateAssigned = flightGateManagementService.assignGate( flightNumber.intValue() );
        }
        catch ( FlightConfirmationException | GateAvailabilityException e )
        {
            message = e.getMessage();
        }
        catch ( Exception e )
        {
            message = UNKNOWN_ERROR_MESSAGE;
            e.printStackTrace();
        }

        return gateAssigned != -1 ? String.format( FLIGHT_ASSIGNED_TO_GATE, flightNumber.intValue(), gateAssigned ) : message;
    }
}
