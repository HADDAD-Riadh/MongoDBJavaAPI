/**
 * 
 */
package healthydis.pds.fr.mongo.manipulation;

/**
 * @author Riadh
 *
 */

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.Set;

public interface DaoStructure {

	public boolean createTable(String tableName) throws Exception;


	// This is how you can retrieve all the table names
	public Set getTableNames() throws Exception;

	// This is how you can retrieve all the rows in the table
	public void showDB(String tableName) throws Exception;

	public DBCursor getAllRows(String tableName) throws Exception;

	// Below code shows how you could get the row count
	public int getRowCount(String tableName) throws Exception;

	// Below code shows how to filter the data using where clause. A where
	// clause is a BasicDBObject type with key as the filter column name and the
	// value is the filter value.
	public DBCursor findByColumn(String tableName, DBObject whereClause)
			throws Exception;
	//Bellow methode shows how create index
	public void createIndex(String tableName, String columnName)
			throws Exception;

	//Bellow methode shows how delete table (collection )
	public void dropTable(String collectionName);
	//test Drop Database Also Drops Collection Data
	 public void testDropDatabaseAlsoDropsCollectionData() throws Exception;
}
