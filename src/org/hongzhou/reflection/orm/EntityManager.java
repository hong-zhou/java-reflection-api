package org.hongzhou.reflection.orm;

import java.sql.SQLException;

public interface EntityManager<T> {

	static <T> EntityManager<T> of(Class<T> clazz) {
		// TODO Auto-generated method stub
		return new EntityManagerImpl<>();
	}

	void persist(T t) throws SQLException, IllegalArgumentException, IllegalAccessException;

}
