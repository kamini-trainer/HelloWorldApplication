import com.flight.app.SearchModule;
import com.flight.app.SearchModuleImpl;
import com.google.errorprone.annotations.DoNotMock;
import org.junit.jupiter.api.*;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MyExtension.class)
public class SearchModuleUnitTest {

    @Mock
    private SearchModuleImpl searchModule;

    public SearchModuleUnitTest() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
    }


    @Test
    @Disabled
    void testMe(){}

    @ParameterizedTest
    @CsvSource({"2024-01-01", "2024-01-02", "2024-01-03"})
    public void testSomething(String date) {
        when(searchModule.getListOfFlightsByDate(date)).thenReturn(Arrays.asList("Indigo", "SpiceJet"));

        List<String> actualOutput = searchModule.getListOfFlightsByDate(date);
        List<String> expectedOutput = getExpectedFlightNames(date);
        //TODO: check for array of objects
        Assertions.assertEquals(expectedOutput, actualOutput); //expectedOutput, actualOutput

    }

    @ParameterizedTest
    @CsvSource({"2024-01-01"})

    //check here : https://github.com/kamini-trainer/HelloWorldApplication/blob/cdfee7817cc904513a4108443973ca679f4d99da/pom.xml#L65C9-L76C22
    public void searchcache_test(String date) {
        //
        when(searchModule.searchCache(date)).thenReturn(Arrays.asList("Indigo", "SpiceJet"));

        //!IMPORTANT :: check if the debugger is hitting the method to be mocked or not
        // if it hits -> then the methods has not been mocked, else its been mocked successfully

        List<String> actualOutput = searchModule.searchCache(date);
        List<String> expectedOutput = getExpectedFlightNames(date);
        //TODO: check for array of objects
        Assertions.assertEquals(expectedOutput, actualOutput); //expectedOutput, actualOutput

    }



    // TODO:// create a function that provides us the expected output for our query string
    private static List<String> getExpectedFlightNames(String date){
        // TODO : do the implementation to fetch from the database
        return Arrays.asList("Indigo", "SpiceJet");
    }
}


