package iwb.repository.helpers;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import javax.inject.Named;

import org.jongo.Jongo;

import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.mongo.MongoModule;

@Component @Named("mongoHelper")
public class MongoHelper {
	
	private MongoClient mongoClient;
	private DB db;
	private Jongo jongo;
	private String dbName;
	
	public MongoHelper(@Named(MongoModule.MONGO_CLIENT_NAME) MongoClient client , @Named(MongoModule.MONGO_DB_NAME) String name){
		this.mongoClient = client;
		this.db = mongoClient.getDB(name);
		this.jongo = new Jongo(db);
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	
	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}
	
	public DB getDb() {
		return db;
	}
	
	public void setDb(DB db) {
		this.db = db;
	}

	public Jongo getJongo() {
		return jongo;
	}

	public void setJongo(Jongo jongo) {
		this.jongo = jongo;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public JongoCollection getJongoCollection(String name){
		return (JongoCollection) jongo.getCollection(name);
	}
	
}
