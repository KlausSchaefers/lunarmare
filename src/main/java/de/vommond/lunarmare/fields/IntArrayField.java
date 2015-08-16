package de.vommond.lunarmare.fields;

import de.vommond.lunarmare.impl.SchemaImpl;

public class IntArrayField extends Field{

	public IntArrayField(SchemaImpl parent, String name) {
		super(parent, name);
	}

	
	public Type getType(){
		return Type.IntArray;
	}
	
	
	public IntArrayField setOptional(){
		isRequired = false;
		return this;
	}
	
	public IntArrayField setHidden(){
		hidden = true;
		return this;
	}
	
}
