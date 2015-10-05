package de.vommond.lunarmare.sql;

import io.vertx.core.json.JsonObject;

import java.util.List;

import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.impl.Field;
import de.vommond.lunarmare.impl.Field.Type;

public class SQLBuilder {
	
	private final Model schema;
	
	public SQLBuilder(Model schema){
		this.schema = schema;
		
	}
	
	private static String getType(Field field){
		
		String result = null;
		Type type = field.getType();
		
		switch (type){
			case Boolean:
				result = "BOOLEAN";
				break;
			case String:
				result = "VARCHAR(255)";
				break;
			case ID:
				result = "INT NOT NULL AUTO_INCREMENT";
				break;
			case Integer:
				result = "INT";
				break;
			case Float:
				result = "FLOAT";
				break;
			case Double:
				result = "DOUBLE PRECISION";
				break;
			case Long:
				result = "BIGINT";
				break;
			case Date:
				result = "TIMESTAMP";
				break;
			case Object:
				result = "TEXT";
				break;
			case Array:
				result = "TEXT";
				break;
			case IntArray:
				result = "TEXT";
				break;
			default:
				result = "TEXT";
				break;
		}
		
		
		return result;
	}
	
	
	/**
	 * CREATE TABLE table_name
		(
		column_name1 data_type(size),
		column_name2 data_type(size),
		column_name3 data_type(size),
		....
		);
	 * @return
	 */
	public String create(){
		final StringBuilder builder = new StringBuilder();
		
		builder.append("CREATE TABLE ");
		builder.append(schema.getName());
		builder.append(" (");
		
		List<Field> fields = schema.getFields();
		for(int i=0;i < fields.size(); i++){
			Field field = fields.get(i);
			builder.append(field.getName());
			builder.append(" ");
			builder.append(getType(field));
			builder.append(", ");
		}
	
		builder.append("PRIMARY KEY (_id)");
		builder.append(");");
	
			
		return builder.toString();
	} 
	
	/**
	 * INSERT INTO table_name (column1,column2,column3,...)
	 *	VALUES (value1,value2,value3,...);
	 * @return
	 */
	public String insert(){
		final StringBuilder builder = new StringBuilder();
		
		builder.append("INSERT INTO ");
		builder.append(schema.getName());
		builder.append(" (");
		
		List<Field> fields = schema.getFields();
		for(int i=0;i < fields.size(); i++){
			if(i > 0){
				builder.append(", ");
			}
			builder.append(fields.get(i).getName());
		}
	
		builder.append(") VALUES (");
		for(int i=0;i < fields.size(); i++){
			if(i > 0){
				builder.append(", ");
			}
			builder.append("?");
		}
		
		builder.append(");");
		
			
		return builder.toString();
	} 
	
	public String update(){
		return "";
	} 
	
	
	public String delete(){
		return "";
	}

	public String insert(JsonObject klaus) {
		// TODO Auto-generated method stub
		return null;
	}

	public String update(JsonObject klaus) {
		// TODO Auto-generated method stub
		return null;
	}

	public String replace(JsonObject klaus) {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete(JsonObject klaus) {
		// TODO Auto-generated method stub
		return null;
	} 
	

}
