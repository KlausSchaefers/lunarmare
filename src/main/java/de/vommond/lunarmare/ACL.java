package de.vommond.lunarmare;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

public interface ACL {
	
	public void canCreate(User user, JsonObject data, Handler<Boolean> handler);

	public void canRead(User user, String id, Handler<Boolean> handler);
	
	public void canFind(User user, JsonObject data, Handler<Boolean> handler);
	
	public void canUpdate(User user, JsonObject data, Handler<Boolean> handler);
	
	public void canDelete(User user, String id, Handler<Boolean> handler);
}
