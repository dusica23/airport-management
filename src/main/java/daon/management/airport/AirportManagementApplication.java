package daon.management.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan( basePackages = { "daon.management" } )
@EntityScan( "daon.management.dao" )
@EnableJpaRepositories( "daon.management.repository" )
public class AirportManagementApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run( AirportManagementApplication.class, args );
    }
}
