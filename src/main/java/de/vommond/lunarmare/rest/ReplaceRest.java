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

public class ReplaceRest extends Rest implements Handler<RoutingContext>{

	private Logger logger = LoggerFactory.getLogger(ReplaceRest.class);
	
	public ReplaceRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);
	}
	
	public ReplaceRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	
	public void handle(RoutingContext event) {
		logger.debug("handler() > enter");

		
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
		 * As we allow partial updates the id does not need be passed, but can come form the url
		 */
		if(object.containsKey(Model.ID)){
			String id = getId(event);
			object.put(Model.ID, id);
		}

		/**
		 * invoke custom callbacks
		 */
		this.before(object);
	
		/**
		 * now check acl
		 */
		acl.canUpdate(getUser(event), object, isAllowed-> {
			if(isAllowed){
								
				
								
				/**
				 * delegate to data service
				 */
				this.ds.replace(object, res->{
				
					if(res.succeeded()){
												
						/**
						 * invoke callback 
						 */
						this.after(object);
						
						/**
						 * finally return object
						 */
						returnOk(event, this.model.getName() + ".replaced");  
					} else {
						logger.error("handle() > Could not replace entity in '" + this.model.getName() + "'");
						returnError(event, 405);
					}
				});
				
			} else {
				logger.error("handle() > User " + getUser(event) + " tried to update new entity in '" + this.model.getName() +"'");
				returnError(event, 404);
			}
		});
	
	}
	
	
	protected void before(JsonObject obj){
		
	}
	
	protected void after(JsonObject obj){
		
	}
	

}
