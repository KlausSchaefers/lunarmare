package de.vommond.lunarmare.acl;

import de.vommond.lunarmare.ACL;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

public class DefaultACL implements ACL {

	@Override
	public void canCreate(User user, JsonObject data, Handler<Boolean> handler) {
		handler.handle(true);
		
	}


	@Override
	public void canRead(User user, String id, Handler<Boolean> handler) {
		handler.handle(true);
	}
	
	@Override
	public void canFind(User user, JsonObject data, Handler<Boolean> handler) {
		handler.handle(true);
	}

	@Override
	public void canUpdate(User user, JsonObject data, Handler<Boolean> handler) {
		handler.handle(true);
	}

	@Override
	public void canDelete(User user, String id, Handler<Boolean> handler) {
		handler.handle(true);
	}

}
