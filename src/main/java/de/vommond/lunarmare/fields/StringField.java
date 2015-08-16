package de.vommond.lunarmare.fields;

import io.vertx.core.json.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.vommond.lunarmare.impl.SchemaImpl;

public class StringField extends Field{

	private Pattern pattern;
	
	private int minLength = -1;
	
	private int maxLength = -1;
	
	
	public StringField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	public Type getType(){
		return Type.String;
	}

	public StringField setOptional(){
		isRequired = false;
		return this;
	}
	
	public StringField setHidden(){
		hidden = true;
		return this;
	}
	
	public StringField addPattern(String pattern){
		this.pattern = Pattern.compile(pattern);
		return this;
	}
	
	public StringField setMinLenth(int length){
		this.minLength = length;
		return this;
	}
	
	public StringField setMaxLenth(int length){
		this.maxLength = length;
		return this;
	}
	
	
	public String validateMore(JsonObject object){
		
		String value = object.getString(name);
		
		if(value != null){
			
			if(this.minLength >=0 && this.minLength <= value.length()){
				return ERROR_MIN;
			}
			

			if(this.maxLength >=0 && this.maxLength > value.length()){
				return ERROR_MAX;
			}
			
			if(this.pattern!=null){
				Matcher matcher =  pattern.matcher(value);
				if(!matcher.matches()){
					return ERROR_PATTERN;
				}
			} 
				
			return VALID;
			
	
		}
		else {
			return ERROR_NULL;
		}
		
		

	
	}

}
