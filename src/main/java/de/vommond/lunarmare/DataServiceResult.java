package de.vommond.lunarmare;

import java.util.Arrays;
import java.util.List;

public class DataServiceResult<T>  {
	
	private final T result;
	
	private final List<String> errors;
	
	public DataServiceResult(T result){
		this(result, null);
	}
	
	public DataServiceResult(String ...errors){
		this(null, Arrays.asList(errors));
	}
	
	public DataServiceResult(T result, List<String> errors){
		this.result = result;
		this.errors = errors;
	}

	public DataServiceResult(List<String> errors) {
		this(null, errors);
	}

	public T result() {
		return result;
	}

	public List<String> errors() {
		return errors;
	}

	public boolean succeeded() {
		return errors == null || errors.size() == 0;
	}

	
}
