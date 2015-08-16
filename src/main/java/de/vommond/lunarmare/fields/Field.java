package de.vommond.lunarmare.fields;

import io.vertx.core.json.JsonObject;
import de.vommond.lunarmare.Schema;
import de.vommond.lunarmare.SchemaBuilder;
import de.vommond.lunarmare.impl.SchemaImpl;

public class Field implements SchemaBuilder{
	
	public static enum Type {
		
		ID, Integer, Double, Float, Long, Object, Array, IntArray, Boolean, String, None, Date
		
	}
	
	
	private final SchemaImpl parent;
	
	protected final String name;
	
	protected boolean isRequired = true;
	
	protected boolean hidden = false;
	
	public static final String VALID = "valid";
	
	public static final String ERROR_REQUIRED = "error.required";
	
	public static final String ERROR_NULL = "error.null";
	
	public static final String ERROR_PATTERN = "error.pattern";
	
	public static final String ERROR_MIN = "error.min";
	
	public static final String ERROR_MAX = "error.max";
	
	public Field(SchemaImpl parent, String name){
		this.parent = parent;
		this.name = name;
	}
	
	public Field setOptional(){
		isRequired = false;
		return this;
	}
	
	public Field setHidden(){
		hidden = true;
		return this;
	}
		
	public boolean isHidden(){
		return hidden;
	}
	
	
	public String validate(JsonObject object){
		if(this.isRequired){
			if( object.containsKey(name)){
				return validateMore(object);
			} else {
				return ERROR_REQUIRED;
			}
		}
		return VALID;
	}
	
	protected String validateMore(JsonObject object){
		return VALID;
	}
	
	public IntegerField addInteger(String name){
		return parent.addInteger(name);
	}
	
	public DoubleField addDouble(String name){
		return parent.addDouble(name);
	}
	
	public StringField addString(String name){
		return parent.addString(name);
	}
	
	public LongField addLong(String name){
		return parent.addLong(name);
	}
	
	public DateField addDate(String name){
		return parent.addDate(name);
	}

	public ObjectField addObject(String name){
		return parent.addObject(name);
	}

	public ArrayField addArray(String name){
		return parent.addArray(name);
	}
	
	public IntArrayField addIntArray(String name){
		return parent.addIntArray(name);
	}
	
	public FloatField addFloat(String name){
		return parent.addFloat(name);
	}
	
	public BooleanField addBoolean(String name){
		return parent.addBoolean(name);
	}


	public String getName() {
		return name;
	}
	
	public Type getType(){
		return Type.None;
	}

	@Override
	public Schema build() {
		return parent;
	}


}
