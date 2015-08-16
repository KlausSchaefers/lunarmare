package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;



public class IntegerField extends Field {

	public IntegerField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	public IntegerField setOptional(){
		isRequired = false;
		return this;
	}
	
	
	public Type getType(){
		return Type.Integer;
	}
	
	
	public IntegerField setHidden(){
		hidden = true;
		return this;
	}

}
