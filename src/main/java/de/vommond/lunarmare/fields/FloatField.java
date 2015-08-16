package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class FloatField extends Field{

	public FloatField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	public Type getType(){
		return Type.Float;
	}
	

	
	public FloatField setOptional(){
		isRequired = false;
		return this;
	}
	
	public FloatField setHidden(){
		hidden = true;
		return this;
	}
}
