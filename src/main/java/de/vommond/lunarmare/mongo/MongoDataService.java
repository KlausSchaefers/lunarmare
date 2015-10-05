package de.vommond.lunarmare.mongo;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.mongo.MongoClient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.ModelServiceResult;
import de.vommond.lunarmare.sql.SQLDataService;

public class MongoDataService implements ModelService{
	
	private Logger logger = LoggerFactory.getLogger(SQLDataService.class);
	
	private final MongoClient client;
	
	private final String table;

	public MongoDataService(MongoClient client, String table) {
		this.table = table;
		this.client = client;
	}

	@Override
	public void create(JsonObject object,Handler<ModelServiceResult<String>> handler) {

		
	}

	@Override
	public void replace(JsonObject object,Handler<ModelServiceResult<String>> handler) {
		
	}

	@Override
	public void update(JsonObject object,	Handler<ModelServiceResult<String>> handler) {
		
	}

	@Override
	public void get(String id,	Handler<ModelServiceResult<JsonObject>> handler) {
		
	}

	@Override
	public void find(JsonObject object,	Handler<ModelServiceResult<List<JsonObject>>> handler) {

		
	}

	@Override
	public void delete(String id,Handler<ModelServiceResult<Boolean>> handler) {
		
		
	}
	

}
