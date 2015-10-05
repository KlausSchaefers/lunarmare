package de.vommond.lunarmare.sql;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.ModelServiceResult;

public class SQLDataService implements ModelService{
	
	private Logger logger = LoggerFactory.getLogger(SQLDataService.class);

	@Override
	public void create(JsonObject object,
			Handler<ModelServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replace(JsonObject object,
			Handler<ModelServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(JsonObject object,
			Handler<ModelServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get(String id, Handler<ModelServiceResult<JsonObject>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(JsonObject query,
			Handler<ModelServiceResult<List<JsonObject>>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id, Handler<ModelServiceResult<Boolean>> handler) {
		// TODO Auto-generated method stub
		
	}


	

}
