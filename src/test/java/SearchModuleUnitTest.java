import com.flight.app.SearchModule;
import com.flight.app.SearchModuleImpl;
import com.google.errorprone.annotations.DoNotMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MyExtension.class)
public class SearchModuleUnitTest {

    @Mock
    private SearchModuleImpl searchModule;

    public SearchModuleUnitTest() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
    }

    @ParameterizedTest
    @CsvSource({"2024-01-01", "2024-01-02", "2024-01-03"})
    public void testSomething(String date) {
        when(searchModule.getListOfFlightsByDate(date)).thenReturn(Arrays.asList("Indigo", "SpiceJet"));

        List<String> actualOutput = searchModule.getListOfFlightsByDate(date);
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

class MyExtension implements BeforeAllCallback, AfterAllCallback {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "mysecretpassword";
    private static Liquibase liquibase;

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
       // liquibase.rollback("", "");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            // Initialize Liquibase
            liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
//            liquibase.rollback("", "");
            // Run the change log
            liquibase.update("");
        }
    }
}
