package de.vommond.lunarmare.rest;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.auth.ACL;

public class DeleteRest extends Rest implements Handler<RoutingContext>{

	private Logger logger = LoggerFactory.getLogger(DeleteRest.class);
	
	public DeleteRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);
	}
	
	public DeleteRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	
	public void handle(RoutingContext event) {
		logger.debug("handler() > enter");

		String id = getId(event);
		
		/**
		 * now check acl
		 */
		acl.canDelete(getUser(event), id, isAllowed -> {
			if(isAllowed){
				this.ds.delete(id, res ->{
					if(res.succeeded()){
						
					} else {
						logger.error("handle() > Could not delete entity in '" + this.model.getName() + "' with id " + id );
						returnError(event,405);
					}
				});
			}else {
				logger.error("handle() > The user  " + getUser(event)+ " tried to delete entity in '" + this.model.getName() + "' with id " + id );
				returnError(event,404);
			}
		});
	}
}
