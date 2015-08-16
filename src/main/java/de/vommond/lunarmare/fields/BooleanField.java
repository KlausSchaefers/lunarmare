package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class BooleanField extends Field{

	public BooleanField(SchemaImpl parent, String name) {
		super(parent, name);
	}

	public Type getType(){
		return Type.Boolean;
	}
	
	public BooleanField setOptional(){
		isRequired = false;
		return this;
	}
	
	public BooleanField setHidden(){
		hidden = true;
		return this;
	}
}
