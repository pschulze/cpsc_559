package data;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class Database {
  private static final BasicDataSource dataSource = new BasicDataSource();

  static {
      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl("jdbc:postgresql://host:port/database");
      dataSource.setUsername("user");
      dataSource.setPassword("password");
  }

  private Database() {
    //
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
