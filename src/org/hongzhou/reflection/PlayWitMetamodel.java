package org.hongzhou.reflection;

import java.util.List;

import org.hongzhou.reflection.model.Person;
import org.hongzhou.reflection.util.ColumnField;
import org.hongzhou.reflection.util.Metamodel;
import org.hongzhou.reflection.util.PrimaryKeyField;

public class PlayWitMetamodel {

	public static void main(String[] args) {
		
		Metamodel<Person> metamodel = Metamodel.of(Person.class);
		
		PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();
		List<ColumnField> columnFields = metamodel.getColumns();
		
		System.out.println("Primary key name = " + primaryKeyField.getName() + ", type = " + primaryKeyField.getType().getSimpleName());
		
		for (ColumnField columnField : columnFields) {
			System.out.println("Column name = " + columnField.getName() + ", type = " + columnField.getType().getSimpleName());
		}

	}

}
