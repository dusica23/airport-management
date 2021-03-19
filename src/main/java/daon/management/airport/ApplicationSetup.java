package daon.management.airport;

import daon.management.data.initializer.DatabaseInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class ApplicationSetup
{
    @Autowired
    private DatabaseInitializer databaseInitializer;

    @PostConstruct
    public void init() {
        databaseInitializer.init();
    }
}
