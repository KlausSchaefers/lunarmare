package de.vommond.lunarmare;

import de.vommond.lunarmare.fields.ArrayField;
import de.vommond.lunarmare.fields.BooleanField;
import de.vommond.lunarmare.fields.DateField;
import de.vommond.lunarmare.fields.DoubleField;
import de.vommond.lunarmare.fields.FloatField;
import de.vommond.lunarmare.fields.IntArrayField;
import de.vommond.lunarmare.fields.IntegerField;
import de.vommond.lunarmare.fields.LongField;
import de.vommond.lunarmare.fields.ObjectField;
import de.vommond.lunarmare.fields.StringField;

public interface SchemaBuilder {

	public Schema build();
	
	public abstract IntegerField addInteger(String name);

	public abstract DoubleField addDouble(String name);

	public abstract StringField addString(String name);

	public abstract LongField addLong(String name);

	public abstract DateField addDate(String name);

	public abstract ObjectField addObject(String name);

	public abstract ArrayField addArray(String name);

	public abstract IntArrayField addIntArray(String name);

	public abstract FloatField addFloat(String name);

	public abstract BooleanField addBoolean(String name);
}
