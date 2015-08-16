package de.vommond.lunarmare.mongo;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.mongo.MongoClient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.DataService;
import de.vommond.lunarmare.DataServiceResult;
import de.vommond.lunarmare.sql.SQLDataService;

public class MongoDataService implements DataService{
	
	private Logger logger = LoggerFactory.getLogger(SQLDataService.class);
	
	private final MongoClient client;
	
	private final String table;

	public MongoDataService(MongoClient client, String table) {
		this.table = table;
		this.client = client;
	}

	@Override
	public void create(User user, JsonObject object,Handler<DataServiceResult<String>> handler) {

		
	}

	@Override
	public void update(User user, JsonObject object,Handler<DataServiceResult<String>> handler) {
		
	}

	@Override
	public void updatePartial(User user, JsonObject object,	Handler<DataServiceResult<String>> handler) {
		
	}

	@Override
	public void get(User user, String id,	Handler<DataServiceResult<JsonObject>> handler) {
		
	}

	@Override
	public void find(User user, JsonObject object,	Handler<DataServiceResult<List<JsonObject>>> handler) {

		
	}

	@Override
	public void delete(User user, String id,Handler<DataServiceResult<Boolean>> handler) {
		
		
	}
	

}
