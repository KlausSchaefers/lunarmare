package de.vomonnd.lunarmare;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;

public class UserImpl implements User{
	
	private final String email, id;
	
	public UserImpl(String email, String id){
		this.email = email;
		this.id = id;
	}

	@Override
	public User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User clearCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonObject principal() {
		return new JsonObject().put("email",email).put("_id", id);
	}

	@Override
	public void setAuthProvider(AuthProvider authProvider) {
		
	}

}
