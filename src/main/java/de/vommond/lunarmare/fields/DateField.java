package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class DateField extends Field{

	public DateField(SchemaImpl parent, String name) {
		super(parent, name);
	}
	
	public Type getType(){
		return Type.Date;
	}
	

	public DateField setOptional(){
		isRequired = false;
		return this;
	}
	
	public DateField setHidden(){
		hidden = true;
		return this;
	}
}
