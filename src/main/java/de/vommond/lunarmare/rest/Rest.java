package de.vommond.lunarmare.rest;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import de.vommond.lunarmare.auth.ACL;
import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.Model;

public class Rest {

	
	protected final ACL acl;
	
	protected final ModelService ds;
	
	protected Model model;
	
	protected final String idParameter;
	
	
	public Rest(ModelService ds, Model schema, ACL acl){
		this.acl = acl;
		this.model = schema;
		this.ds = ds;
		this.idParameter = model.getName()+"Id";
	}
	
	public Rest(ModelService ds, Model schema, ACL acl, String idParameter){
		this.acl = acl;
		this.model = schema;
		this.ds = ds;
		this.idParameter = idParameter;
	}
	
	

	protected User getUser(RoutingContext event){
		return event.user();
	}
	
	
	
	
	/*********************************************************************
	 *  Helper Methods
	 *********************************************************************/
	protected void returnError(RoutingContext event, int code) {
		event.response().setStatusCode(code);
		event.response().end();
	}

	protected void returnJson(RoutingContext event, JsonObject result){
		event.response().putHeader("content-type", "application/json");
		event.response().end(result.encodePrettily());	
	}
	

	protected void returnJson(RoutingContext event, JsonArray result){
		event.response().end(result.encodePrettily());	
	}
	
	protected void returnError(RoutingContext event, String error){
		JsonObject result = new JsonObject().put("type", "error");
		result.put("errors", new JsonArray().add(error));
		event.response().end(result.encodePrettily());
	
	}
	
	protected void returnError(RoutingContext event, List<String> errors){
		JsonObject result = new JsonObject().put("type", "error");
		result.put("errors", errors);
		event.response().end(result.encodePrettily());
	}
	
	protected void returnOk(RoutingContext event, String code){
		JsonObject result = new JsonObject()
			.put("details", code)
			.put("status", "ok");
		event.response().end(result.encodePrettily());
	}
	
	
	protected String getId(RoutingContext event) {
		return event.request().getParam(idParameter);
	}
	
	protected String getId(RoutingContext event, String id) {
		return event.request().getParam(id);
	}
}
