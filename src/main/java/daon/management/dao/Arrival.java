package daon.management.dao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Arrival
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @OneToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "flight_id" )
    private Flight flight;


    @OneToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "gate_id" )
    private Gate gate;


    public Flight getFlight()
    {
        return flight;
    }


    public void setFlight( final Flight flight )
    {
        this.flight = flight;
    }


    public Gate getGate()
    {
        return gate;
    }


    public void setGate( final Gate gate )
    {
        this.gate = gate;
    }


    public Integer getId()
    {
        return id;
    }


    public void setId( final Integer id )
    {
        this.id = id;
    }
}
