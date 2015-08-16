package de.vommond.lunarmare;

import java.util.List;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

public interface DataService {
	
	public void create(User user, JsonObject object, Handler<DataServiceResult<String>> handler);
	
	public void update(User user, JsonObject object, Handler<DataServiceResult<String>> handler);
	
	public void updatePartial(User user, JsonObject object, Handler<DataServiceResult<String>> handler);
	
	public void get(User user, String id, Handler<DataServiceResult<JsonObject>> handler);
	
	public void find(User user, JsonObject object, Handler<DataServiceResult<List<JsonObject>>> handler);
	
	public void delete(User user, String id, Handler<DataServiceResult<Boolean>> handler);
}
