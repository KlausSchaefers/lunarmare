package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class LongField extends Field{

	public LongField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	
	public Type getType(){
		return Type.Long;
	}
	

	
	public LongField setOptional(){
		isRequired = false;
		return this;
	}
	
	public LongField setHidden(){
		hidden = true;
		return this;
	}
}
