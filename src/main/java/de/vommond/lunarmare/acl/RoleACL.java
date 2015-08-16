package de.vommond.lunarmare.acl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import de.vommond.lunarmare.ACL;

public class RoleACL implements ACL{

	@Override
	public void canCreate(User user, JsonObject data, Handler<Boolean> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canRead(User user, String id, Handler<Boolean> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canFind(User user, JsonObject data, Handler<Boolean> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canUpdate(User user, JsonObject data, Handler<Boolean> handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canDelete(User user, String id, Handler<Boolean> handler) {
		// TODO Auto-generated method stub
		
	}

}
