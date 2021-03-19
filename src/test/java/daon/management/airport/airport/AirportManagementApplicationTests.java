package daon.management.airport.airport;

import daon.management.airport.AirportManagementApplication;
import daon.management.contoller.GateManagementController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RunWith( SpringRunner.class )
@ContextConfiguration( classes = AirportManagementApplication.class )
@TestPropertySource( locations = "classpath:application-integrationtest.properties" )
@WebMvcTest( GateManagementController.class )
class AirportManagementApplicationTests
{
    private static final String ASSIGN_GATE_URI = "gate/management/assign";

    private static final String RELEASE_GATE_URI = "gate/management/release";

    @Autowired
    private MockMvc mvc;


    @Test
    void flightGateCoordinationTest()
    {
        ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );
        List<Future<String>> responseMessages = new ArrayList<>();

        final Collection<Callable<String>> callables = new ArrayList<>();

        for ( int i = 0; i < 15; i++ )
        {
            callables.add( ( createThread( ASSIGN_GATE_URI, String.valueOf( i ) ) ) );
        }
        callables.add( ( createThread( RELEASE_GATE_URI, String.valueOf( 3 ) ) ) );
        callables.add( ( createThread( RELEASE_GATE_URI, String.valueOf( 5 ) ) ) );

        try
        {
            final List<Future<String>> futureResponses = executor.invokeAll( callables );
            for ( Future<String> response : futureResponses )
            {
                System.out.println( response.get() );
            }
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        catch ( ExecutionException e )
        {
            e.printStackTrace();
        }
    }



    private Callable<String> createThread( String uri,
                                           String requestBody )
    {
        Callable<String> assigner = new Callable<String>()
        {
            @Override
            public String call()
                  throws Exception
            {
                final MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.post( uri ).content( requestBody )
                                                                               .accept( MediaType.APPLICATION_JSON ) ).andReturn();
                return mvcResult.getResponse().getContentAsString();
            }
        };

        return assigner;
    }
}
