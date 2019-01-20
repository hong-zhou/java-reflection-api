package org.hongzhou.reflection.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public String buildInsertRequest() {
		// insert into Person (id, name, age) value (?, ?, ?)
		String columnElement = buildColumnNames();
		String questionMarksElement = buildQuestionMarksElement();
		
		return "insert into " + this.clazz.getSimpleName() + 
				" (" + columnElement + ") values (" + questionMarksElement + ")";
	}

	private String buildQuestionMarksElement() {
		int numberOfColumns = getColumns().size() + 1;
		String questionMarksElement = 
				IntStream.range(0, numberOfColumns)
					.mapToObj(index -> "?")
					.collect(Collectors.joining(", "));
		return questionMarksElement;
	}

	private String buildColumnNames() {
		String primaryKeyColumnName = getPrimaryKey().getName();
		List<String> columnNames = 
				getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
		columnNames.add(0, primaryKeyColumnName);
		String columnElement = String.join(", ", columnNames);
		return columnElement;
	}

}
