package org.hongzhou.reflection.db;

import java.sql.SQLException;

import org.h2.tools.Server;

public class DBLauncher {
	public static void main(String... args) throws SQLException, ClassNotFoundException{
		
		  Server.main(); System.out.println("DB Launched!");
		 

	}
}
