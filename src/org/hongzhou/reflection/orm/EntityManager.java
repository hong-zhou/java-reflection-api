package org.hongzhou.reflection.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.hongzhou.reflection.model.Person;

public interface EntityManager<T> {

	static <T> EntityManager<T> of(Class<T> clazz) {
		// TODO Auto-generated method stub
		return new EntityManagerImpl<>();
	}

	void persist(T t) throws SQLException, IllegalArgumentException, IllegalAccessException;

	T find(Class<T> class1, Object primaryKey) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;

}
