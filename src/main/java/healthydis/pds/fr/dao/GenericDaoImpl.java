/**
 * 
 */
package healthydis.pds.fr.dao;

import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

/**
 * @author Riadh
 * 
 */

public class GenericDaoImpl<T> implements GenericDao<T> {

	private String ipAddress;
	private int port;
	private String DB;
	private String collection;
	DBCollection col;
	DB db;
	MongoClient mongo;
	List<DBObject> cursors;

	public GenericDaoImpl(String ipAddress, int port, String DB,
			String collection) throws UnknownHostException {
		super();
		this.ipAddress = ipAddress;
		this.port = port;
		this.collection = collection;
		this.DB = DB;
		// connection initialisation
		mongo = new MongoClient(ipAddress, port);
		// specify the data base
		db = mongo.getDB(DB);
		// specify the collection (means table)
		col = db.getCollection(collection);
	}

	// insert object
	public void insert(T genericObject) throws UnknownHostException {
		// DBObject dbObject = (DBObject)JSON.parse(s)
		DBObject doc = createDBObject(genericObject);
		// connection initialisation
		// specify the collection (means table)
		DBCollection col = db.getCollection(collection);
		col.insert(doc);

	}

	// read object
	public DBObject read(T genericObject) throws UnknownHostException {
		DBObject uu = null;
		// use gson to transform genericObject to format json
		Gson gson = new Gson();
		String genericTogson = gson.toJson(genericObject);
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> data = new Gson().fromJson(genericTogson, type);
		Iterator<Entry<String, String>> entries = data.entrySet().iterator();
		// get the key of object ,in our case the key is the id of each
		// genericObject
		if (entries.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry thisEntry = (Entry) entries.next();
			// get key of object (like primary key)
			Object key = thisEntry.getKey();
			// get it's value to be used in Query
			Object value = thisEntry.getValue();
			// create query
			DBObject query = BasicDBObjectBuilder
					.start()
					.add("_" + key.toString(), Integer.parseInt((String) value))
					.get();
			// execute find
			DBCursor cursor = col.find(query);
			if (cursor.hasNext()) {
				 uu=cursor.next();	
			}
			
			

		}
		return uu;

	}

	// update object
	public void update(T genericObject) throws UnknownHostException {
		BasicDBObject searchQuery = null;
		// create BasicDBObject that will be updated in document
		BasicDBObject newDocument = new BasicDBObject();
		// call gson to transform genericObject to format JSON
		Gson gson = new Gson();
		String genericTogson = gson.toJson(genericObject);
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> data = new Gson().fromJson(genericTogson, type);
		Iterator<Entry<String, String>> entries = data.entrySet().iterator();
		// get the key of object ,in our case the key is the id of each
		// genericObject
		if (entries.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry thisEntry = (Entry) entries.next();
			// get key of object (like primary key)
			Object key = thisEntry.getKey();
			// get it's value to be used in Query
			Object value = thisEntry.getValue();
			searchQuery = new BasicDBObject().append("_" + key.toString(),
					Integer.parseInt((String) value));
		}
		while (entries.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry thisEntry = (Entry) entries.next();

			Object key = thisEntry.getKey();
			Object value = thisEntry.getValue();
			// update all fields that are changed
			newDocument.append("$set",
					new BasicDBObject().append(key.toString(), value));
			// update
			col.update(searchQuery, newDocument);
		}

	}

	// delete object
	public void delete(T genericObject) throws UnknownHostException {
		Gson gson = new Gson();
		String genericTogson = gson.toJson(genericObject);
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> data = new Gson().fromJson(genericTogson, type);
		Iterator<Entry<String, String>> entries = data.entrySet().iterator();

		if (entries.hasNext()) {
			@SuppressWarnings("rawtypes")
			Entry thisEntry = (Entry) entries.next();

			Object key = thisEntry.getKey();
			Object value = thisEntry.getValue();
			DBObject query = BasicDBObjectBuilder
					.start()
					.add("_" + key.toString(), Integer.parseInt((String) value))
					.get();
			col.remove(query);
			//mongo.close();

		}

	}

	// create json DBObject from generic using Gson
	public DBObject createDBObject(T genericObject) {
		// first methode use
		Gson gson = new Gson();
		// convert genericobject to format json
		String genericTogson = gson.toJson(genericObject);
		// create DBObject from json
		DBObject dbObject = (DBObject) JSON.parse(genericTogson);
		return dbObject;

	}

	// show all DBObjects from collection 
	public List<DBObject> getAllItems() {
		try {
			 cursors=new ArrayList<DBObject>();
			DBCursor cursor = col.find();
			while (cursor.hasNext()) {
				cursors.add(cursor.next());
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return cursors;
	}

	// insert object in specific table
	public void saveToDB(String tableName, T dbObject)
			throws UnknownHostException {
		DBObject doc = createDBObject(dbObject);
		// specify the collection (means table)
		DBCollection dbCollection = db.getCollection(tableName);
		// insert in collection
		dbCollection.insert(doc);
	}
	private  DBObject selectSingleRecordAndFieldByRecordNumber(T dbObject) {
		DBObject dd = null;
	    BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("employeeId", 5);
	    DBCursor cursor = col.find(whereQuery);
	    while (cursor.hasNext()) {
	        dd = cursor.next();
	    }
		return dd;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the dB
	 */
	public String getDB() {
		return DB;
	}

	/**
	 * @param dB
	 *            the dB to set
	 */
	public void setDB(String dB) {
		DB = dB;
	}

	/**
	 * @return the collection
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	// seconde methode for creating DBObject is to use BasicDBObjectBuilder
	/*
	 * public DBObject createDBObject(T genericObject) { Gson gson = new Gson();
	 * String genericTogson = gson.toJson(genericObject); BasicDBObjectBuilder
	 * docBuilder = BasicDBObjectBuilder.start(); Type type = new
	 * TypeToken<Map<String, String>>() { }.getType(); Map<String, String> data
	 * = new Gson().fromJson(genericTogson, type); Iterator<Entry<String,
	 * String>> entries = data.entrySet().iterator(); while (entries.hasNext())
	 * {
	 * 
	 * @SuppressWarnings("rawtypes") Entry thisEntry = (Entry) entries.next();
	 * Object key = thisEntry.getKey();
	 * System.out.println("----------------------" + key.toString()); Object
	 * value = thisEntry.getValue(); System.out.println(value);
	 * docBuilder.append(key.toString(), value); return docBuilder.get(); }
	 * return docBuilder.get(); }
	 */
}
