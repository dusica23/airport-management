package daon.management.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Flight
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    private String flightName;


    public Integer getId()
    {
        return id;
    }


    public void setId( final Integer id )
    {
        this.id = id;
    }


    public String getFlightName()
    {
        return flightName;
    }


    public void setFlightName( final String flightName )
    {
        this.flightName = flightName;
    }
}
