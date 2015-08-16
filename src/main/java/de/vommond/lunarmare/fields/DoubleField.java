package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class DoubleField extends Field {

	public DoubleField(SchemaImpl parent, String name) {
		super(parent, name);
	}

	public Type getType(){
		return Type.Double;
	}
	
	public DoubleField setOptional(){
		isRequired = false;
		return this;
	}
	
	public DoubleField setHidden(){
		hidden = true;
		return this;
	}
}
