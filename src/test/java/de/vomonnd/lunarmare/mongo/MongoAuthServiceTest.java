package de.vomonnd.lunarmare.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vomonnd.lunarmare.BaseTestCase;
import de.vomonnd.lunarmare.example.MongoVerticle;

@RunWith(VertxUnitRunner.class)
public class MongoAuthServiceTest extends BaseTestCase{
	
	private Logger logger = LoggerFactory.getLogger(MongoAuthServiceTest.class);
	
	@Test
	public void test(TestContext context){
		logger.info("test() > entry");
		
		deploy(new MongoVerticle(), context);
		

		/**
		 * 1) create user
		 */
		JsonObject newUser = new JsonObject().put("email", "test@vommond.de").put("password", "123123");
		
		logger.info("test() > exit");
		
	}
}
