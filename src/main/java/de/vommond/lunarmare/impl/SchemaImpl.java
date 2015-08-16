package de.vommond.lunarmare.impl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

import de.vommond.lunarmare.Schema;
import de.vommond.lunarmare.SchemaBuilder;
import de.vommond.lunarmare.fields.ArrayField;
import de.vommond.lunarmare.fields.BooleanField;
import de.vommond.lunarmare.fields.DateField;
import de.vommond.lunarmare.fields.DoubleField;
import de.vommond.lunarmare.fields.Field;
import de.vommond.lunarmare.fields.FloatField;
import de.vommond.lunarmare.fields.IDField;
import de.vommond.lunarmare.fields.IntArrayField;
import de.vommond.lunarmare.fields.IntegerField;
import de.vommond.lunarmare.fields.LongField;
import de.vommond.lunarmare.fields.ObjectField;
import de.vommond.lunarmare.fields.StringField;


public class SchemaImpl implements Schema, SchemaBuilder {

	private final List<Field> fields = new ArrayList<Field>();
	
	private final String name;
	
	
	public SchemaImpl(String name) {
		this.name = name;
		this.fields.add(new IDField(this, "_id").setOptional());
	}
	
	public List<Field> getFields(){
		return fields;
	}

	@Override
	public String getName(){
		return name;
	}
	
	public void foreach(Handler<Field> handler){
		for(Field field : fields){
			handler.handle(field);
		}
	}
	
	
	@Override
	public List<String> validate(JsonObject object){
		return validate(object, false);
	}
	
	@Override
	public List<String> validate(JsonObject object, boolean partial){
		
		List<String> errors = new ArrayList<String>();
		
		
		if(partial){
			
			/**
			 * check only the fields that were in the json
			 */
			for(Field field : fields){
	
				if(object.containsKey(field.getName())){
					String valid = field.validate(object);
					if(valid != Field.VALID){
						errors.add(name +"."+ field.getName() +"."+ valid);
					}	
				}	
			}
		} else {
			
			/**
			 * check for every field
			 */
			for(Field field : fields){
				String valid = field.validate(object);
				if(valid != Field.VALID){
					errors.add(name +"."+ field.getName() +"."+ valid);
				}		
			}
			
		}
		
		
		
		return errors;
	}

	
	@Override
	public IntegerField addInteger(String name){
		IntegerField field = new IntegerField(this, name);
		this.fields.add(field);
		return field;
	}
	
	
	@Override
	public DoubleField addDouble(String name){
		DoubleField field = new DoubleField(this, name);
		this.fields.add(field);
		return field;
	}
	
	@Override
	public StringField addString(String name){
		StringField field = new StringField(this, name);
		this.fields.add(field);
		return field;
	}
	
	
	@Override
	public LongField addLong(String name){
		LongField field = new LongField(this, name);
		this.fields.add(field);
		return field;
	}
	
	
	@Override
	public DateField addDate(String name){
		DateField field = new DateField(this, name);
		this.fields.add(field);
		return field;
	}

	
	@Override
	public ObjectField addObject(String name){
		ObjectField field = new ObjectField(this, name);
		this.fields.add(field);
		return field;
	}

	
	@Override
	public ArrayField addArray(String name){
		ArrayField field = new ArrayField(this, name);
		this.fields.add(field);
		return field;
	}
	
	
	@Override
	public IntArrayField addIntArray(String name){
		IntArrayField field = new IntArrayField(this, name);
		this.fields.add(field);
		return field;
	}
	
	

	@Override
	public FloatField addFloat(String name){
		FloatField field = new FloatField(this, name);
		this.fields.add(field);
		return field;
	}

	@Override
	public BooleanField addBoolean(String name){
		BooleanField field = new BooleanField(this, name);
		this.fields.add(field);
		return field;
	}

	@Override
	public Schema build() {
		return this;
	}

	@Override
	public JsonObject write(JsonObject object) {
		
		JsonObject result = new JsonObject();

		foreach(field -> {
			String key = field.getName();
			/**
			 * if the field is not hidden and it exists in the other 
			 * object, we will copy it.
			 */
		
			if(!field.isHidden() && object.containsKey(key)){
				result.put(key, object.getValue(key));	
			}
		});
		
		return result;
	}

	@Override
	public JsonObject read(JsonObject object) {
		return write(object);
	}


}
