import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.sql.Connection;
import java.sql.DriverManager;

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