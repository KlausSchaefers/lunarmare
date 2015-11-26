package de.vommond.lunarmare.rest;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.auth.ACL;

public class FindByRest extends Rest implements Handler<RoutingContext>{

	private Logger logger = LoggerFactory.getLogger(FindByRest.class);
	
	public FindByRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);
	}
	
	public FindByRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	
	public void handle(RoutingContext event) {
		logger.debug("handler() > enter");
		event.response().end("{}");
	}
}
