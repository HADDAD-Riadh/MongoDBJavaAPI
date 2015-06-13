/**
 * 
 */
package healthydis.pds.fr.dao;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.Cursor;
import com.mongodb.DBObject;

/**
 * @author Riadh
 *
 */
public interface GenericDao<T> {
	//Bellow methode shows how insert an object
	public void insert(T genericObject) throws UnknownHostException;
	//Bellow methode shows how read an object
	public DBObject read(T genericObject) throws UnknownHostException;
	//Bellow methode shows how to update an object
	public void update(T genericObject)throws UnknownHostException;
	//Bellow methode shows how to delete an object
	public void delete(T genericObject) throws UnknownHostException ;
	//Bellow methode shows how to create an object (Json to builder)
	public DBObject createDBObject(T genericObject) ;
	public List<DBObject> getAllItems() throws UnknownHostException ;
	//Bellow methode shows how to insert an object in a gived table
	public void saveToDB(String tableName, T dbObject)
			throws  UnknownHostException ;
}
