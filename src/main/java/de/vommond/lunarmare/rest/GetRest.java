package de.vommond.lunarmare.rest;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.auth.ACL;

public class GetRest extends Rest implements Handler<RoutingContext>{

	private Logger logger = LoggerFactory.getLogger(GetRest.class);
	
	public GetRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);
	}
	
	public GetRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	
	public void handle(RoutingContext event) {
		logger.debug("handler() > enter");

		String id = getId(event);
		
		/**
		 * check acl
		 */
		acl.canRead(getUser(event), id, result -> {
			if(result){
				this.ds.get(id, res ->{
					returnJson(event, res.result());
				});
			}else {
				logger.error("handle() > User " + getUser(event) + " tried to read from '" + this.model.getName() +"' entity with id :" + id );
				returnError(event,404);
			}
		});
	}
}
