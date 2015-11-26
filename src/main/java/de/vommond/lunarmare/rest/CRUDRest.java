package de.vommond.lunarmare.rest;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.ModelService;
import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.auth.ACL;

/**
 * Class the binds http to the data service. It uses several child classes,
 * which which handle the real work.
 * 
 * @author klaus_schaefers
 *
 */
public class CRUDRest extends Rest {
	
	private Logger logger = LoggerFactory.getLogger(CRUDRest.class);
	
	
	public CRUDRest(ModelService ds, Model schema, ACL acl) {
		super(ds, schema, acl);

	}
	
	public CRUDRest(ModelService ds, Model schema, ACL acl, String idParameter) {
		super(ds, schema, acl, idParameter);
	}
	
	public void bind(Router router, String prefix, boolean update){
		logger.info("bind() > enter. Bind " + this.model.getName() + " to " + prefix);
		
		router.route(HttpMethod.POST, prefix +"/").handler(create());
		if(update){
			router.route(HttpMethod.PUT, prefix +"/:" + this.idParameter).handler(update());
		} else {
			router.route(HttpMethod.PUT, prefix +"/:" + this.idParameter).handler(create());
		}

		router.route(HttpMethod.GET, prefix +"/:" + this.idParameter).handler(get());
		router.route(HttpMethod.DELETE, prefix +"/:" + this.idParameter).handler(delete());
		router.route(HttpMethod.GET, prefix +"/:from/:to").handler(find());
	}
	
	
	public Handler<RoutingContext> create() {
		return new CreateRest(super.ds, super.model, super.acl, super.idParameter);
	}

	public Handler<RoutingContext> update() {
		return new UpdateRest(super.ds, super.model, super.acl, super.idParameter);

	}

	public Handler<RoutingContext> find() {
		return new FindByRest(super.ds, super.model, super.acl, super.idParameter);
	}
	
	public Handler<RoutingContext> delete() {
		return new DeleteRest(super.ds, super.model, super.acl, super.idParameter);
	}
	
	
	public Handler<RoutingContext> get() {
		return new GetRest(super.ds, super.model, super.acl, super.idParameter);
	}
	
	public Handler<RoutingContext> replace() {
		return new ReplaceRest(super.ds, super.model, super.acl, super.idParameter);
	}

	


	


	
	

}
