import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private String URL;
    private String DATABASE;
    private String USERNAME;
    private String PASSWORD;


    public void loadProp()throws IOException {
        Properties defaultProps = new Properties();
        FileInputStream in = new FileInputStream("C:\\Users\\joni2\\IdeaProjects\\JDBC1\\src\\main\\resources\\application.properties");
        defaultProps.load(in);
        in.close();

        this.URL = defaultProps.getProperty("DBURL");
        this.USERNAME = defaultProps.getProperty("DBUsername");
        this.PASSWORD = defaultProps.getProperty("DBPassword");
        this.DATABASE = defaultProps.getProperty("DBToUse");
    }

    public DatabaseConnectionManager()throws IOException{
        loadProp();
    }

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE, USERNAME, PASSWORD);    }
}
