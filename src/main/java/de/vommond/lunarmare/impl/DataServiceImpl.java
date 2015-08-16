package de.vommond.lunarmare.impl;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;

import java.util.List;

import de.vommond.lunarmare.ACL;
import de.vommond.lunarmare.DataService;
import de.vommond.lunarmare.DataServiceResult;
import de.vommond.lunarmare.Schema;
import de.vommond.lunarmare.acl.DefaultACL;

/**
 * DataService Implementation which performs schema checking and ACL validation, 
 * before it delegates the request to the real data service.
 * 
 * Its is somehow a simply chain of responsibility, but with less objects.
 */
public class DataServiceImpl implements DataService{

	protected final ACL acl;

	protected final Schema schema;
	
	protected final DataService dataservice;
	
	public DataServiceImpl( DataService dataService){
		this(dataService, new DefaultACL(), null);
	}
	
	public DataServiceImpl(DataService dataService, ACL acl){
		this(dataService, acl, null);
	}
	
	public DataServiceImpl(DataService dataService, ACL acl, Schema schema){
		this.acl = acl;
		this.schema = schema;
		this.dataservice = dataService;
	}

	@Override
	public void create(User user, JsonObject object, Handler<DataServiceResult<String>> handler) {

		if(schema!=null){
			List<String> errors = schema.validate(object);
			if(errors.size() > 0){
				handler.handle(new DataServiceResult<String>(errors));
			}
		}
		
		/**
		 * now check acl
		 */
		acl.canCreate(user, object, result -> {
			if(result){
				/**
				 * delegate to data service
				 */
				this.dataservice.create(user, object, handler);
				
			} else {
				handler.handle(new DataServiceResult<String>(schema.getName() +".create.not.allowed"));
			}
		});
		
		
	}
	
	
	
	
	
	@Override
	public void update(User user, JsonObject object,Handler<DataServiceResult<String>> handler) {
	
		if(schema!=null){
			List<String> errors = schema.validate(object);
			if(errors.size() > 0){
				handler.handle(new DataServiceResult<String>(errors));
			}
		}
		
		/**
		 * now check acl
		 */
		acl.canUpdate(user, object, result -> {
			if(result){
				this.dataservice.update(user, object, handler);
			}else {
				handler.handle(new DataServiceResult<String>(schema.getName() +".update.not.allowed"));
			}
		});
		
	}


	@Override
	public void updatePartial(User user, JsonObject object, Handler<DataServiceResult<String>> handler) {

		
		if(schema!=null){
			List<String> errors = schema.validate(object, true);
			if(errors.size() > 0){
				handler.handle(new DataServiceResult<String>(null, errors));
			}
		}
		
		/**
		 * now check acl
		 */
		acl.canUpdate(user, object, result -> {
			if(result){
				this.dataservice.updatePartial(user, object, handler);
			}else {
				handler.handle(new DataServiceResult<String>(schema.getName() +".update.not.allowed"));
			}
			
		});
		
	}
	


	@Override
	public void get(User user, String id,Handler<DataServiceResult<JsonObject>> handler) {
	
		/**
		 * now check acl
		 */
		acl.canRead(user, id, result -> {
			if(result){
				this.dataservice.get(user, id, handler);
			}else {
				handler.handle(new DataServiceResult<JsonObject>(schema.getName() +".read.not.allowed"));
			}
		});
		
	}


	@Override
	public void find(User user, JsonObject object,	Handler<DataServiceResult<List<JsonObject>>> handler) {
		/**
		 * now check acl
		 */
		acl.canFind(user, object, result -> {
			if(result){
				this.dataservice.find(user, object, handler);
			}else {
				handler.handle(new DataServiceResult<List<JsonObject>>(schema.getName() +".read.not.allowed"));
			}
		});
		
	}

	@Override
	public void delete(User user, String id, Handler<DataServiceResult<Boolean>> handler) {
		

		/**
		 * now check acl
		 */
		acl.canDelete(user, id, result -> {
			if(result){
				this.dataservice.delete(user, id, handler);
			}else {
				handler.handle(new DataServiceResult<Boolean>(schema.getName() +".read.not.allowed"));
			}
		});
		
	}

	
	
	
	
}
