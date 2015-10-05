package de.vomonnd.lunarmare;

import io.vertx.core.json.JsonObject;

import org.junit.Test;

import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.ModelFactory;
import de.vommond.lunarmare.sql.SQLBuilder;

public class SQLBuilderTest {

private ModelFactory schemas = new ModelFactory();
	
	@Test
	public void testSchemas(){
		
		Model user = schemas.create("user")
			.addString("name")
			.addString("lastname")
			.addInteger("age")
			.build();
		
		
		SQLBuilder builder = new SQLBuilder(user);
	
	
		JsonObject klaus = new JsonObject().put("name", "Klaus");
	
		System.out.println("Create: " + builder.create());
		
		System.out.println("Insert: " + builder.insert(klaus));
		
		System.out.println("Insert: " + builder.update(klaus));
		
		System.out.println("Insert: " + builder.replace(klaus));
		
		System.out.println("Insert: " + builder.delete(klaus));
		
	}
}
