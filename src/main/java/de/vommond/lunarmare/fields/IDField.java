package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class IDField extends Field{

	public IDField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	public Type getType(){
		return Type.ID;
	}
		
	public IDField setOptional(){
		isRequired = false;
		return this;
	}
	
	public IDField setHidden(){
		hidden = true;
		return this;
	}
}
