package de.vomonnd.lunarmare;

import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.vomonnd.lunarmare.example.MongoVerticle;

@RunWith(VertxUnitRunner.class)
public class MongoVerticleTest extends BaseTestCase {

	private Logger logger = LoggerFactory.getLogger(MongoVerticleTest.class);
	
	@Test
	public void test(TestContext context){
		logger.info("test() > entry");
		
		deploy(new MongoVerticle(), context);
		
		
		logger.info("test() > exit");
		
	}
	
}
