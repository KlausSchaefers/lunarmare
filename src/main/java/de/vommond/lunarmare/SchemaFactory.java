package de.vommond.lunarmare;

import io.vertx.core.json.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.impl.SchemaImpl;

public class SchemaFactory {
	
	private Logger logger = LoggerFactory.getLogger(SchemaFactory.class);
	
	private static Map<String, SchemaImpl> schemas = new HashMap<>();

	public SchemaBuilder create(String name){
		SchemaImpl schema =  new SchemaImpl(name);
		schemas.put(name, schema);
		return schema;
	}
	
	
	public List<String> validate(String name, JsonObject object){
		if(schemas.containsKey(name)){
			return get(name).validate(object);
		}
		logger.warn("validate() > The schema '" + name +"' is not managed. Return true!");
		return Collections.emptyList();
	}
	
	public Schema get(String name){
		return schemas.get(name);
	}
}
