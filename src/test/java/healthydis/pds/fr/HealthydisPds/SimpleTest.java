/**
 * 
 */
package healthydis.pds.fr.HealthydisPds;

import static org.junit.Assert.*;
import healthydis.pds.fr.dao.GenericDaoImpl;
import healthydis.pds.fr.mongo.manipulation.DaoStructureImpl;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

/**
 * @author Riadh
 * 
 */
public class SimpleTest {

	private DBCollection collection;
	private MongoClient client;
	DaoStructureImpl daoStructureImpl;

	// private MongoServer server;

	@Before
	public void setUp() throws Exception {

		daoStructureImpl = new DaoStructureImpl("127.0.0.1", 27017, "test");

		client = new MongoClient();
	}

	/*
	 * @After public void tearDown() { client.close(); server.shutdownNow(); }
	 */
	 /*@After
	    public void tearDown() {
	        client.close();
	    }*/

	@Test
	public void testSimpleInsertQuery() throws Exception {
		daoStructureImpl.createTable("collectiontest2");

		collection = client.getDB("test").getCollection("collectiontest2");
		collection.drop();
	//	assertEquals(0, collection.count());

		// creates the database and collection in memory and insert the object
		DBObject obj = new BasicDBObject("_id", 1).append("key", "value");
		// collection insert object
		collection.insert(obj);
		// test if elements in collection equal to 1 after obj insertion
		assertEquals(1, collection.count());
		// test if the inserted element is equal to our obj
		assertEquals(obj, collection.findOne());
		// remove object
		collection.remove(obj);
	}
	/*@Test
	public void testSimpleReadQuery() throws Exception {
		
		GenericDaoImpl<User> crudGenericImpl=new GenericDaoImpl<User>("127.0.0.1", 27017,"test", "collectiontest4");
		User user=new User();
		user.setId(5);
		user.setName("HADDAD");
		user.setRole("student");
		user.setEmployee(false);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		//crudGenericImpl.insert(user);
		//crudGenericImpl.update(user);
		//crudGenericImpl.read(user);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		crudGenericImpl.update(user);
		DBObject lll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(lll.toString());
		}
		DaoStructureImpl daoStructureImpl=new DaoStructureImpl("127.0.0.1", 27017, "test");
		daoStructureImpl.createIndex("users", "id");
		crudGenericImpl.delete(user);
		crudGenericImpl.read(user);
		crudGenericImpl.update(user);
		crudGenericImpl.saveToDB("users", user);
		
		
		
		
		
		
		daoStructureImpl.createTable("collectiontest4");

		collection = client.getDB("test").getCollection("collectiontest4");
		assertEquals(0, collection.count());

		// creates the database and collection in memory and insert the object
		DBObject obj = new BasicDBObject("_id", 1).append("key", "value");
		// collection insert object
		collection.insert(obj);
		// test if elements in collection equal to 1 after obj insertion
		assertEquals(1, collection.count());
		// test if the inserted element is equal to our obj
		assertEquals(obj, collection.findOne());
		// remove object
		collection.remove(obj);
	}*/
	/*
	@Test
	public void testSimpleUpdateQuery() throws Exception {
		
		GenericDaoImpl<User> crudGenericImpl=new GenericDaoImpl<User>("127.0.0.1", 27017,"test", "collectiontest4");
		User user=new User();
		user.setId(5);
		user.setName("HADDAD");
		user.setRole("student");
		user.setEmployee(false);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		//crudGenericImpl.insert(user);
		//crudGenericImpl.update(user);
		//crudGenericImpl.read(user);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		crudGenericImpl.update(user);
		DBObject lll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(lll.toString());
		}
		DaoStructureImpl daoStructureImpl=new DaoStructureImpl("127.0.0.1", 27017, "test");
		daoStructureImpl.createIndex("users", "id");
		crudGenericImpl.delete(user);
		crudGenericImpl.read(user);
		crudGenericImpl.update(user);
		crudGenericImpl.saveToDB("users", user);
		
		
		
		
		
		
	}
	@Test
	public void testSimpleGetAllQuery() throws Exception {
		
		GenericDaoImpl<User> crudGenericImpl=new GenericDaoImpl<User>("127.0.0.1", 27017,"test", "collectiontest4");
		User user=new User();
		user.setId(5);
		user.setName("HADDAD");
		user.setRole("student");
		user.setEmployee(false);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		//crudGenericImpl.insert(user);
		//crudGenericImpl.update(user);
		//crudGenericImpl.read(user);
		DBObject ll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(ll.toString());
		}
		crudGenericImpl.update(user);
		DBObject lll=crudGenericImpl.read(user);
		if(ll !=null){
			System.out.println(lll.toString());
		}
		DaoStructureImpl daoStructureImpl=new DaoStructureImpl("127.0.0.1", 27017, "test");
		daoStructureImpl.createIndex("users", "id");
		crudGenericImpl.delete(user);
		crudGenericImpl.read(user);
		crudGenericImpl.update(user);
		crudGenericImpl.saveToDB("users", user);
		
		
		
		
		
		
		
	}
	  @Test
	    public void testUnsupportedModifier() throws Exception {
		  daoStructureImpl.createTable("collectiontest2");

			collection = client.getDB("test").getCollection("collectiontest2");
	        collection.insert(json("{}"));
	        try {
	            collection.update(json("{}"), json("$foo: {}"));
	            fail("MongoException expected");
	        } catch (MongoException e) {
	            assertEquals(e.getCode(),10147);
//	            assertThat(e.getMessage()).contains("Invalid modifier specified: $foo");
	        }
	    }

	*/
	
	
	
	 private BasicDBObject json(String string) {
	     string = string.trim();
	     if (!string.startsWith("{")) {
	         string = "{" + string + "}";
	     }
	     return (BasicDBObject) JSON.parse(string);
	 }
	
	
	
	
	
	 @Test
	    public void testBasicUpdate() throws Exception {
		 daoStructureImpl.createTable("collectiontest8");

			collection = client.getDB("test").getCollection("collectiontest8");
			collection.drop();
			assertEquals(0, collection.count());
	        collection.insert(json("_id:1"));
	        collection.insert(json("_id:2, b:5"));
	        collection.insert(json("_id:3"));
	        collection.insert(json("_id:4"));

	        collection.update(json("_id:2"), json("_id:2, a:5"));

	        Assert.assertEquals(collection.findOne(json("_id:2")),json("_id:2, a:5"));
	        collection.remove(json("_id:1"));
	        collection.remove(json("_id:2, b:5"));
	        collection.remove(json("_id:3"));
	        collection.remove(json("_id:4"));
	    }
	
	 @Test
	    public void testCountWithQueryCommand() throws Exception {
		 daoStructureImpl.createTable("collectiontest9");

			collection = client.getDB("test").getCollection("collectiontest9");
			collection.drop();
	        collection.insert(json("n:1"));
	        collection.insert(json("n:2"));
	        collection.insert(json("n:2"));
	        assertEquals((collection.count(json("n:2"))),2);
	        collection.remove(json("n:1"));
	        collection.remove(json("n:2"));
	        collection.remove(json("n:2"));
	        
	    }
	 @Test
	    public void testDropCollection() throws Exception {
		 daoStructureImpl.createTable("collectiontest10");
			collection = client.getDB("test").getCollection("collectiontest10");
	        collection.insert(json("{}"));
	        assertTrue((client.getDB("test").getCollectionNames()).contains(collection.getName()));
	        collection.drop();
	        assertFalse((client.getDB("test").getCollectionNames()).contains(collection.getName()));
	    }
	 
	 @Test
	    public void testDropCollectionAlsoDropsFromDB() throws Exception {
		 daoStructureImpl.createTable("collectiontest11");
			collection = client.getDB("test").getCollection("collectiontest11");
	        collection.insert(json("{}"));
	        collection.drop();
	        assertEquals(collection.count(), 0)  ;
	        assertFalse((client.getDB("test").getCollectionNames()).contains(collection.getName()));
	    }
	  
	 
	 
	 @Test
	    public void testInsertDuplicate() throws Exception {
		 daoStructureImpl.createTable("collectiontest12");
			collection = client.getDB("test").getCollection("collectiontest12");
			collection.drop();
	        assertEquals(collection.count(),0);

	        collection.insert(json("_id: 1"));
	        assertEquals(collection.count(),1);

	        try {
	            collection.insert(json("_id: 1"));
	            fail("MongoException expected");
	        } catch (MongoException e) {
	            assertTrue((e.getMessage()).contains("duplicate key error"));
	        }

	        try {
	            collection.insert(new BasicDBObject("_id", Double.valueOf(1.0)));
	            fail("MongoException expected");
	        } catch (MongoException e) {
	        	assertTrue((e.getMessage()).contains("duplicate key error"));
	        }

	        assertEquals(collection.count(),1);
	    }
	 @Test(expected = MongoException.class)
	    public void testInsertDuplicateThrows() throws Exception {
		 daoStructureImpl.createTable("collectiontest13");
			collection = client.getDB("test").getCollection("collectiontest13");
	        collection.insert(json("_id: 1"));
	        collection.insert(json("_id: 1"));
	        collection.drop();
	    }
	 
	 @Test(expected = MongoException.class)
	    public void testInsertDuplicateWithConcernThrows() throws Exception {
		 daoStructureImpl.createTable("collectiontest14");
			collection = client.getDB("test").getCollection("collectiontest14");
	        collection.insert(json("_id: 1"));
	        collection.insert(json("_id: 1"), WriteConcern.SAFE);
	        collection.drop();
	    }
	 
	 @Test
	    public void testQueryAll() throws Exception {
		 daoStructureImpl.createTable("collectiontest16");
			collection = client.getDB("test").getCollection("collectiontest16");
			collection.drop();
	        List<Object> inserted = new ArrayList<Object>();
	        for (int i = 0; i < 10; i++) {
	            BasicDBObject obj = new BasicDBObject("_id", i);
	            collection.insert(obj);
	            inserted.add(obj);
	        }
	        assertEquals(collection.count(),10);

	        assertEquals(collection.find().toArray(),inserted);
	    }
	 
	 
	    @Test
	    public void testRemove() throws Exception {
	   	 daoStructureImpl.createTable("collectiontest15");
			collection = client.getDB("test").getCollection("collectiontest15");
			collection.drop();
	        collection.insert(json("_id: 1"));
	        collection.insert(json("_id: 2"));
	        collection.insert(json("_id: 3"));
	        collection.insert(json("_id: 4"));

	        collection.remove(json("_id: 2"));

	        assertNull((collection.findOne(json("_id: 2"))));
	        assertEquals(collection.count(),3);

	        
	    }
	    @Test
	    public void testRemoveSingle() throws Exception {
	    	 daoStructureImpl.createTable("collectiontest21");
				collection = client.getDB("test").getCollection("collectiontest21");
				collection.drop();
	        DBObject obj = new BasicDBObject("_id", ObjectId.get());
	        collection.insert(obj);
	        collection.remove(obj);
	    }
	    
	    @Test
	    public void testUpdateNothing() throws Exception {
	    	 daoStructureImpl.createTable("collectiontest22");
				collection = client.getDB("test").getCollection("collectiontest22");
				collection.drop();
	        BasicDBObject object = json("_id: 1");
	        WriteResult result = collection.update(object, object);
	        assertEquals(result.getN(),0);
	        assertNull(result.getUpsertedId());
	    }
}