package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;


public class ObjectField extends Field {

	public ObjectField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	
	public Type getType(){
		return Type.Object;
	}
	


	public ObjectField setOptional(){
		isRequired = false;
		return this;
	}
	
	public ObjectField setHidden(){
		hidden = true;
		return this;
	}
}
