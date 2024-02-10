import com.flight.app.SearchModule;
import com.flight.app.SearchModuleImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class SearchModuleUnitTest {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "mysecretpassword";
    private static Liquibase liquibase;
    private static final SearchModule searchModule = new SearchModuleImpl();
    @BeforeAll
    public static void setUp() throws Exception {
        // Connect to PostgreSQL database
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            // Initialize Liquibase
            liquibase = new Liquibase("changelog.xml", new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
//            liquibase.rollback("", "");
            // Run the change log
            liquibase.update("");
        }
    }

    @ParameterizedTest
    @CsvSource({"2024-01-01"})
    public void testSomething(String date) {
        // Your test logic here
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

    @AfterAll
    public static void tearDown() throws Exception {
        // Rollback changes after tests
//         liquibase.rollback("", "");
    }
}
