package healthydis.pds.fr.mongo.manipulation;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;



import java.net.UnknownHostException;
import java.util.Set;

public class DaoStructureImpl implements DaoStructure {

	private DB db = null;
	private String ipAddress;
	private int port;
	private String DB;


	public DaoStructureImpl(String ipAddress, int port, String DB)
			throws UnknownHostException {
		// Connection is created as below
		this.ipAddress = ipAddress;
		this.port = port;
		this.DB = DB;
		MongoClient mongo = new MongoClient(this.ipAddress, this.port);
		db = mongo.getDB(this.DB);
	}

	// A singleton design pattern to get the connection for the outside world
/*	public static MongoDao getInstance()
			throws UnknownHostException {
		if (mongoDao == null) {
			mongoDao = new MongoDao(ipAddress, port, DB);
		}
		return mongoDao;
	}*/
//structure
	// This is how you create a table in the mongoDB
	public boolean createTable(String tableName) throws Exception {
		// If tableName doesn’t exist create it
		Set tableNames = db.getCollectionNames();
		if (!tableNames.contains(tableName)) {
			DBObject dbobject = new BasicDBObject();
			db.createCollection(tableName, dbobject);
			return true;
		}
		return false;
	}

	/*
	 * This is how you insert a record in the database Every record is a
	 * DBObject which is a map type where a key is the column name and value is
	 * column value
	 */


	// This is how you can retrieve all the table names
	public Set getTableNames() throws Exception {
		return db.getCollectionNames();
	}

	// This is how you can retrieve all the rows in the table
	public void showDB(String tableName) throws Exception {
		DBCollection dbCollection = db.getCollection(tableName);
		DBCursor cur = dbCollection.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
		}
	}

	public DBCursor getAllRows(String tableName) throws Exception {
		DBCollection dbCollection = db.getCollection(tableName);
		DBCursor cur = dbCollection.find();
		return cur;
	}

	// Below code shows how you could get the row count
	public int getRowCount(String tableName) throws Exception {
		DBCollection dbCollection = db.getCollection(tableName);
		DBCursor cur = dbCollection.find();
		return cur.count();
	}

	// Below code shows how to filter the data using where clause. A where
	// clause is a BasicDBObject type with key as the filter column name and the
	// value is the filter value.
	public DBCursor findByColumn(String tableName, DBObject whereClause)
			throws Exception {
		DBCursor result = null;
		DBCollection dbCollection = db.getCollection(tableName);
		result = dbCollection.find(whereClause);
		return result;
	}
//create  index for table
	public void createIndex(String tableName, String columnName)
			throws Exception {
		DBCollection dbCollection = db.getCollection(tableName);
		DBObject indexData = new BasicDBObject(columnName, 1);
		dbCollection.createIndex(indexData);
	}
//delete collection(table)
	public void dropTable(String collectionName) {
		db.getCollection(collectionName).drop();
	}
	
	//test Drop Database Also Drops Collection Data
	 public void testDropDatabaseAlsoDropsCollectionData() throws Exception {
	        db.dropDatabase();
	    }

}