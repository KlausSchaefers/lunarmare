package de.vommond.lunarmare.rest;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.auth.ACL;

public class CreateRest extends Rest implements Handler<RoutingContext>{
	
	private Logger logger = LoggerFactory.getLogger(CreateRest.class);

	public CreateRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);
	}
	
	public CreateRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	
	public void handle(RoutingContext event) {

		/**
		 * Get cleaned data from model and
		 * check for other errors
		 */
		JsonObject object = model.read(event);
		List<String> errors = model.validate(object);
		if(errors.size() > 0){
			logger.error("handle() > User " + getUser(event) + " tried to send wrong data for '" + this.model.getName() +"'");
			returnError(event, errors);
		}
		
		/**
		 * invoke custom callbacks
		 */
		this.before(object);

	
		/**
		 * now check acl
		 */
		acl.canCreate(getUser(event), object, isAllowed -> {
			if(isAllowed){
				/**
				 * delegate to data service
				 */
				this.ds.create(object, res->{
				
					if(res.succeeded()){
						
						/**
						 * add the id and return the entire object!
						 */
						object.put(Model.ID, res.result());
						
						/**
						 * invoke callback 
						 */
						this.after(object);
						
						/**
						 * finally return object
						 */
						returnJson(event, object);
					} else {
						logger.error("handle() > Could not create entity in '" + this.model.getName() + "'");
						returnError(event, 405);
					}
				});
				
			} else {
				logger.error("handle() > User " + getUser(event) + " tried to create new entity in '" + this.model.getName() +"'");
				returnError(event, 404);
			}
		});
		
		
	}
	
	protected void before(JsonObject obj){
		
	}
	
	protected void after(JsonObject obj){
		
	}
	


}
