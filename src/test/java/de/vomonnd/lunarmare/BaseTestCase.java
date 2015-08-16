package de.vomonnd.lunarmare;

import static java.nio.file.Files.readAllBytes;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.unit.TestContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;

import com.google.common.io.CharStreams;


public class BaseTestCase {

	protected Vertx vertx;

	protected MongoClient mongo;

	protected SyncMongoClient client;

	public JsonObject conf;
	
	private BasicCookieStore cookieStore;
	
    protected CloseableHttpClient httpClient;
    
    private int loglevel = 3;
    
    private String vertileID;
	
	@Before
	public void before(TestContext contex) {
		try {
			conf = new JsonObject(new String(readAllBytes(Paths.get("src/test/resources/test.conf"))));
			conf.getJsonObject("mongo").put("table_prefix", "test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vertx = Vertx.vertx();
		mongo = MongoClient.createShared(vertx, conf.getJsonObject("mongo"));
		client = new SyncMongoClient(mongo);
		

		
		cookieStore = new BasicCookieStore();
		
	    httpClient = HttpClients.custom()
	             .setDefaultCookieStore(cookieStore)
	             .build();
		
	}

	@After
	public void after(TestContext contex) {
		try {
			
			//vertx.undeploy(vertileID);
			
			vertx.close();
		
			httpClient.close();
			mongo.close();
		} catch (Throwable e) {
			
		}
	}
	

	protected void sleep() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
	}

	
	public void cleanUp(){		  
		  
				  
	}
	
	
	
	public void debug(String method, String message) {
		log(4, method, message);
	}
	
	public void log(String method, String message) {
		log(3, method, message);
	}

	public void log(int level, String method, String message) {
		if(level <= loglevel)
			System.out.println(this.getClass().getSimpleName() + "." + method + "() > " + message);
	}

	public void print(List<JsonObject> results) {
		log("print", "#" + results.size() + " ");
		for (JsonObject result : results)
			log("print", result.encodePrettily());

	}
	
	public void print(JsonArray results) {
		log("print", "#" + results.size() + " ");
		
		log("print", results.encodePrettily());

	}
	
	public void deploy(Verticle v, TestContext context){
		
		CountDownLatch l = new CountDownLatch(1);
		

		
	
		DeploymentOptions options = new DeploymentOptions(
				new JsonObject().put("config", conf));
		
		vertx.deployVerticle(v, options, new Handler<AsyncResult<String>>() {
			
			@Override
			public void handle(AsyncResult<String> event) {
				
				if(event.succeeded()){
					log("deploy","exit > "  + event.result());
					vertileID = event.result();
				} else {
					//context.fail("Could not deploy verticle");
					event.cause().printStackTrace();
				}
				
			
				l.countDown();
			}
		});
		
		try {
			l.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	public JsonObject post(String url, JsonObject data){
		debug("post", url);
		url = "http://localhost:8080" + url;
		try{
			
			HttpPost post = new HttpPost(url);
			 
			StringEntity input = new StringEntity(data.encode());
			input.setContentType("application/json");
			post.setEntity(input);
		 
			CloseableHttpResponse resp = httpClient.execute(post);

			if(resp.getStatusLine().getStatusCode() == 200){
				
				InputStream is = resp.getEntity().getContent();
 
				String json = CharStreams.toString( new InputStreamReader(is ));
				
				
				resp.close();
				return new JsonObject(json);
				
				
			} else {
				resp.close();
				return new JsonObject().put("error",resp.getStatusLine().getStatusCode() );
			}
		
			
		} catch(Exception e){
			e.printStackTrace();
		
			return new JsonObject().put("error", "error");
		}
		
	
	}
	
	public JsonObject post(String url, String fileName){
		log("post", url);
		url = "http://localhost:8080" + url;
		try{
			
			HttpPost post = new HttpPost(url);
			
			File file = new File(fileName);
			if(!file.exists()){
				log("post", "File "+ file.getAbsolutePath() + " does not exits");
				return new JsonObject().put("error", "error");
			}
			
			HttpEntity en = MultipartEntityBuilder.create()
					.addBinaryBody(fileName, file).build();

		    post.setEntity(en);
			 
		 
			CloseableHttpResponse resp = httpClient.execute(post);
			
		
			if(resp.getStatusLine().getStatusCode() == 200){
				
				InputStream is = resp.getEntity().getContent();
 
				String json = CharStreams.toString( new InputStreamReader(is ));
				
				
				resp.close();
				return new JsonObject(json);
				
				
			} else {
				resp.close();
				return new JsonObject().put("error",resp.getStatusLine().getStatusCode() );
			}
		
			
		} catch(Exception e){
			e.printStackTrace();
		
			return new JsonObject().put("error", "error");
		}
		
	
	}
	
	public JsonObject get(String url){
		debug("get", url);
		url = "http://localhost:8080" + url;
		try {
			HttpGet httpget = new HttpGet(url);
	        CloseableHttpResponse resp = httpClient.execute(httpget);
	        
	        if(resp.getStatusLine().getStatusCode() == 200){
				

				InputStream is = resp.getEntity().getContent();
 
				String json = CharStreams.toString( new InputStreamReader(is ));
				resp.close();
				
				return new JsonObject(json);
				
			} else {
				  resp.close();
				return new JsonObject().put("error",resp.getStatusLine().getStatusCode() );
			}

	    
	      
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonObject().put("error", "error");
		}
      
	
		
	}
	
	
	public InputStream getRaw(String url){
		debug("getRaw", url);
		url = "http://localhost:8080" + url;
		try {
			HttpGet httpget = new HttpGet(url);
	        CloseableHttpResponse resp = httpClient.execute(httpget);
	        
	        if(resp.getStatusLine().getStatusCode() == 200){
				

				return resp.getEntity().getContent();
				
				
			} else {
				  resp.close();
				return null;
			}

	    
	      
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
      
	}
	
	
	
	public JsonArray getList(String url){
		log("getList", url);
		url = "http://localhost:8080" + url;
		try {
			HttpGet httpget = new HttpGet(url);
	        CloseableHttpResponse resp = httpClient.execute(httpget);
	        
	        if(resp.getStatusLine().getStatusCode() == 200){
				

				InputStream is = resp.getEntity().getContent();
 
				String json = CharStreams.toString( new InputStreamReader(is ));
				resp.close();
				
				return new JsonArray(json);
				
			} else {
				debug("getList", "Error :" + resp.getStatusLine().getStatusCode() );
				resp.close();
				return new JsonArray().add(resp.getStatusLine().getStatusCode());
			}

	    
	      
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
      
	
		
	}
	
	public JsonObject delete(String url){
		debug("delete", url);
		url = "http://localhost:8080" + url;
		try {
			HttpDelete httpget = new HttpDelete(url);
	        CloseableHttpResponse resp = httpClient.execute(httpget);
	        
	        if(resp.getStatusLine().getStatusCode() == 200){
				

				InputStream is = resp.getEntity().getContent();
 
				String json = CharStreams.toString( new InputStreamReader(is ));
				resp.close();
				
				try{
					return new JsonObject(json);
				} catch(Exception e){
					System.out.println(json);
					return new JsonObject().put("error", "error");
				}
		
				
			} else {
				resp.close();
				return new JsonObject().put("error",resp.getStatusLine().getStatusCode() );
			}

	    
	      
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonObject().put("error", "error");
		}
      
	
		
	}
	
	
	

	
	
	
	public JsonArray assertList(String url, int x,  TestContext context ){
		JsonArray list = getList(url); 
		log("assertList", "" + list.size() + " ?= " + x);
		if(list.size() == 1){
			try{
				context.assertNotEquals(404, list.getInteger(0));
			} catch(Exception e){}
		}
		context.assertEquals(x, list.size(), url);
		
		return list;
	}
	

	public void assertListError(String url,  TestContext context ){
		JsonArray list = getList(url); 
		log("assertListError", "" + list);
		context.assertEquals(1, list.size());
		context.assertEquals(404, list.getInteger(0));		
	}

	public void assertUserList(int size, TestContext context){
		JsonArray apps = getList("/rest/apps/");
		context.assertEquals(size, apps.size());
	}
	
	public void assertPublicList(int size, TestContext context){
		JsonArray apps = getList("/rest/apps/public");
		context.assertEquals(size, apps.size());
	}
	
	
	public void assertLogin(TestContext context, String email, String password) {
		JsonObject login = new JsonObject().put("email", email).put("password", password);
		JsonObject result = post("/rest/login",login );
		debug("assertLogin", "" + !result.containsKey("errors"));
		context.assertTrue(!result.containsKey("errors"));
		context.assertTrue(result.containsKey("_id"));
		context.assertTrue(!result.containsKey("password"));
		context.assertEquals(email, result.getString("email"));
	}
	
	public void assertLoginError(TestContext context, String email, String password) {
		JsonObject login = new JsonObject().put("email", email).put("password", password);
		JsonObject result = post("/rest/login", login);
		debug("test", "loginError > " + result);
		context.assertTrue(result.containsKey("errors"));
		context.assertTrue(!result.containsKey("_id"));
		context.assertTrue(!result.containsKey("password"));
	}
	
	
}
