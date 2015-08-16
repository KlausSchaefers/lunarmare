package de.vommond.lunarmare;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

import de.vommond.lunarmare.fields.Field;

public interface Schema {

	public abstract String getName();

	/**
	 * Validates all fields
	 * 
	 * @param object
	 * 		The object to check
	 * @return
	 * 		List of errors
	 */
	public abstract List<String> validate(JsonObject object);

	/**
	 * Validates only the properties that are really set
	 * @param object
	 * 		The object to check
	 * @return
	 * 		List of errors
	 */
	public abstract List<String> validate(JsonObject object, boolean partial);



	/**
	 * Loop over all fields...
	 */
	public abstract void foreach(Handler<Field> handler);

	
	/**
	 * Get all field
	 * @return
	 */
	public List<Field> getFields();
	
	
	/**
	 * This method will remove all field from the json object that are not supposed to be serialized. For instance
	 * the password field should ever be send back to the client.
	 * 
	 * @param object
	 * 			The object
	 * @return
	 * 
	 * 			The cleaned up object
	 */
	public JsonObject write(JsonObject object);
	
	
	/**
	 * Removes all non defined fields form the json object. The method filter everything out that is not defined!
	 * 
	 * @param object
	 * 			The object to filter!
	 * 
	 * @return
	 * 			The filtered object
	 */
	public JsonObject read(JsonObject object);
	
}