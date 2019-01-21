package org.hongzhou.reflection.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager<T>{

	public Connection buildConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:h2:C:\\Users\\zhsnn\\Documents\\workspace-spring-tool-suite-4-4.0.0.RELEASE\\JavaReflectionApi\\dbfiles\\db",
				"sa", "");
		return connection;
	}
}
