package org.hongzhou.reflection.util;

import java.lang.reflect.Field;

import org.hongzhou.reflection.annotation.Column;

public class ColumnField {

	private Field field;
	private Column column;

	public ColumnField(Field field) {
		this.field = field;
		this.column = field.getAnnotation(Column.class);
	}

	public String getName() {
		return column.name();
	}

	public Class<?> getType() {
		return field.getType();
	}

	public Field getField() {
		return this.field;
	}

}
