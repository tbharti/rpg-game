package com.ramayan.persistence.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hsqldb.Server;

public class HSQLDBConnection {
	static Server hsqlServer = null;
	static Connection connection = null;
	ResultSet rs = null;

	private HSQLDBConnection() {

	}

	public static Connection getConnection() {
		if (connection == null) {
			startDatabase();
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/ramayan", "sa", "");
				bootStrapData();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException impException) {
				impException.printStackTrace();
			}
		}
		return connection;
	}

	private static void startDatabase() {
		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "ramayan");
		hsqlServer.setDatabasePath(0, "file:~/ramayandata/ramayandb");
		hsqlServer.start();
	}

	private static void bootStrapData() throws FileNotFoundException, IOException, SQLException {
		createTables();
	}

	private static void createTables() throws SQLException {
		connection.prepareStatement("create table if not exists players (name varchar(20) not null,player BLOB);")
				.execute();
		connection.commit();
	}

	public static void closeConnection() {
		hsqlServer.stop();
		hsqlServer.shutdown();
		hsqlServer = null;
		connection = null;
	}
}
