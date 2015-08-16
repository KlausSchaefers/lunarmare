package de.vomonnd.lunarmare;

import org.junit.Test;

import de.vommond.lunarmare.Schema;
import de.vommond.lunarmare.SchemaFactory;
import de.vommond.lunarmare.sql.SQLBuilder;

public class SQLBuilderTest {

private SchemaFactory schemas = new SchemaFactory();
	
	@Test
	public void testSchemas(){
		
		Schema user = schemas.create("user")
			.addString("name")
			.addString("lastname")
			.addInteger("age")
			.build();
		
		
		SQLBuilder builder = new SQLBuilder(user);
		
		System.out.println("Create: " + builder.create());
		
		System.out.println("Insert: " + builder.insert());
		
	}
}
