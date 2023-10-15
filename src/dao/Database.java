package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // URL pattern for database
    private static final String DB_URL = "jdbc:sqlite:application.db";

    public static Connection getConnection() throws SQLException {
        try {
            // This line is to manually load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }

        // DriverManager is the basic service for managing a set of JDBC drivers
        // Can also pass username and password
        return DriverManager.getConnection(DB_URL);
    }
}


/*
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	// URL pattern for database
	private static final String DB_URL = "jdbc:sqlite:application.db";

	public static Connection getConnection() throws SQLException {
		// DriverManager is the basic service for managing a set of JDBC drivers
		// Can also pass username and password
		return DriverManager.getConnection(DB_URL);
	}
}

*/