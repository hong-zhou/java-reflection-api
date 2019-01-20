package org.hongzhou.reflection.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hongzhou.reflection.annotation.Column;
import org.hongzhou.reflection.annotation.PrimaryKey;

public class Metamodel<T> {

	private Class<T> clazz;

	public static <T> Metamodel<T> of(Class<T> clazz) {
		return new Metamodel<>(clazz);
	}

	public Metamodel(Class<T> clazz) {
		this.clazz = clazz;
	}

	public PrimaryKeyField getPrimaryKey() {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
			if (primaryKey != null) {
				PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
				return primaryKeyField;
			}
		}
		throw new IllegalArgumentException("No primary key found!");
	}

	public List<ColumnField> getColumns() {
		List<ColumnField> columnFields = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			if (column != null) {
				ColumnField columnField = new ColumnField(field);
				columnFields.add(columnField);
			}
		}

		return columnFields;
	}

}
