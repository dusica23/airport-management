package daon.management.service;

import daon.management.dao.Gate;
import daon.management.repository.GateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class GateService
{
    @Autowired
    private GateDAO gateDAO;


    public void saveGate( Gate gate )
    {
        gateDAO.save( gate );
    }


    public Gate findById( int gateId )
    {
        final Optional<Gate> gate = gateDAO.findById( gateId );

        return gate.isPresent() ? gate.get() : null;
    }


    public Gate getAvailableGate()
    {
        return gateDAO.findFirstByOrderByAvailableDesc();
    }


    public void reserveGate( int gateId )
    {
        gateDAO.setAvailability( false, gateId );
    }


    public void releaseGate( int gateId )
    {
        gateDAO.setAvailability( true, gateId );
    }
}
