package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class ArrayField extends Field{

	public ArrayField(SchemaImpl parent, String name) {
		super(parent, name);
	}

	public ArrayField setOptional(){
		isRequired = false;
		return this;
	}
	
	public ArrayField setHidden(){
		hidden = true;
		return this;
	}
	
}
