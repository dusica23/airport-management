package daon.management.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Gate
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    private String gateMark;

    private Boolean available;


    public Boolean getAvailable()
    {
        return available;
    }


    public void setAvailable( final Boolean available )
    {
        this.available = available;
    }


    public String getGateMark()
    {
        return gateMark;
    }


    public void setGateMark( final String gateMark )
    {
        this.gateMark = gateMark;
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
