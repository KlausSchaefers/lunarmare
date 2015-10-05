package de.vomonnd.lunarmare.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vommond.lunarmare.Model;
import de.vommond.lunarmare.ModelFactory;
import de.vommond.lunarmare.auth.DefaultACL;
import de.vommond.lunarmare.mongo.MongoDataService;
import de.vommond.lunarmare.rest.CRUDRest;


public class MongoVerticle extends AbstractVerticle{
	
	private MongoClient client;
	
	private Logger logger = LoggerFactory.getLogger(MongoVerticle.class);

	private HttpServer server;
	
	private final ModelFactory factory = new ModelFactory();
	
	@Override
	public void start() {
		this.logger.error("start() > enter");
		this.logger.info("asdasd");
			
		JsonObject config = this.config();
		JsonObject mongoConfig = config.getJsonObject("mongo");
		
	
		/**
		 * Create MongoDB client
		 */
		client = MongoClient.createShared(vertx, mongoConfig);

		/**
		 * Set body and cookie handler
		 */
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create().setMergeFormAttributes(false));
		router.route().handler(CookieHandler.create());
		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)).setNagHttps(false));
	
		
		/**
		 * Now setup the domain models
		 */
		Model user = factory.create("user")
				.addString("email")
				.addString("password").setHidden()
				.addString("name")
				.addString("lastname").build();
			
		
		MongoDataService ds = new MongoDataService(client, "users");

		CRUDRest userRest = new CRUDRest(ds, user, new DefaultACL());
		userRest.bind(router, "/rest/user", true);
		
		
//		UserRest authRest = new UserRest();
//	
//		router.route(HttpMethod.POST, "/rest/user/create").handler(authRest.createUser());
//		router.route(HttpMethod.POST, "/rest/user/create").handler(authRest.createUser());
		
		
		
		
		
		/**
		 * Launch server
		 */
		this.server = vertx.createHttpServer()
			.requestHandler(router::accept)
			.listen(8080);
		
		logger.info("*************************");
		logger.info("* MongoVerticle Started *");
		logger.info("*************************");
	}
	
	public void getDomainModel(){
		
	}
	

	@Override
	public void stop(){
	
		try {
			
			client.close();
			server.close();
			
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
