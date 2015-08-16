package de.vommond.lunarmare.sql;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.DataService;
import de.vommond.lunarmare.DataServiceResult;

public class SQLDataService implements DataService{
	
	private Logger logger = LoggerFactory.getLogger(SQLDataService.class);


	@Override
	public void create(User user, JsonObject object,
			Handler<DataServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, JsonObject object,
			Handler<DataServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePartial(User user, JsonObject object,
			Handler<DataServiceResult<String>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get(User user, String id,
			Handler<DataServiceResult<JsonObject>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(User user, JsonObject object,
			Handler<DataServiceResult<List<JsonObject>>> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User user, String id,
			Handler<DataServiceResult<Boolean>> handler) {
		// TODO Auto-generated method stub
		
	}

	

}
