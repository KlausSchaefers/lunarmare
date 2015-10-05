package de.vomonnd.lunarmare;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.vertx.core.json.JsonObject;
import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.ModelFactory;
import de.vommond.lunarmare.impl.Field;

public class ModelAPITest {
	
	private ModelFactory schemas = new ModelFactory();
	
	
	@Test
	public void testReadWrite(){
		
		Model schema = schemas.create("user")
			.addString("name")
			.addString("lastname")
			.addInteger("age")
			.addString("role").setHidden()
			.addString("password").setHidden()
			.addString("hash").setTransform( x->x+"!")
			.build();
		
		JsonObject user = new JsonObject()
			.put("name", "Klaus")
			.put("lastname", "Schaefers")
			.put("age",35)
			.put("role", "admin")
			.put("password", "123123")
			.put("hash", "123");
		
		
		JsonObject written = schema.write(user);
		
		
		Assert.assertTrue(written.containsKey("name"));
		Assert.assertTrue(written.containsKey("lastname"));
		Assert.assertTrue(written.containsKey("age"));
		Assert.assertTrue(written.containsKey("hash"));
		Assert.assertFalse(written.containsKey("password"));
		Assert.assertFalse(written.containsKey("role"));
	
		
		
		JsonObject read = schema.read(user);
		
		Assert.assertTrue(read.containsKey("name"));
		Assert.assertTrue(read.containsKey("lastname"));
		Assert.assertTrue(read.containsKey("age"));
		Assert.assertTrue(read.containsKey("password"));
		Assert.assertTrue(read.containsKey("role"));
		Assert.assertTrue(read.containsKey("hash"));
		Assert.assertEquals(read.getString("hash"), "123!");
	}
	
	@Test
	public void testSchemas(){
		
		schemas.create("user")
			.addString("name")
			.addString("lastname")
			.addInteger("age");
		
		schemas.create("user2")
			.addString("name")
			.addString("lastname").setOptional()
			.addInteger("age").setOptional();
		
		
		JsonObject user = new JsonObject();
		assertErrors("user", user, "user.name."+ Field.ERROR_REQUIRED, "user.lastname."+ Field.ERROR_REQUIRED, "user.age."+ Field.ERROR_REQUIRED);
		
		user = new JsonObject().put("name", "Klaus");
		assertErrors("user", user, "user.lastname."+ Field.ERROR_REQUIRED, "user.age."+ Field.ERROR_REQUIRED);
		
		user = new JsonObject().put("name", "Klaus").put("lastname", "Schaefers");
		assertErrors("user", user, "user.age."+ Field.ERROR_REQUIRED);
		
		user = new JsonObject().put("name", "Klaus").put("lastname", "Schaefers").put("age",35);
		assertErrors("user", user);
		
		/**
		 * Test optional stuff
		 */
		user = new JsonObject().put("name", "Klaus");
		assertErrors("user2", user);
		
		user = new JsonObject();
		assertErrors("user2", user, "user2.name."+ Field.ERROR_REQUIRED);
		assertPartialErrors("user2", user);
		
		/**
		 * test undefined schema
		 */
		assertErrors("undefined", user);
	}
	
	private void assertErrors(String table, JsonObject obj, String... expected){
		
		List<String> errors = schemas.validate(table, obj);
		
		Assert.assertEquals(expected.length, errors.size());
		
		for(String expError : expected){
			Assert.assertTrue(expError + " not found", errors.contains(expError));
		}
		
		
	}
	
	private void assertPartialErrors(String table, JsonObject obj, String... expected){
		
		List<String> errors = schemas.get(table).validate(obj, true);
		
		Assert.assertEquals(expected.length, errors.size());
		
		for(String expError : expected){
			Assert.assertTrue(expError + " not found", errors.contains(expError));
		}
		
		
	}

}
