package geraldo.mongoDB;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import geraldo.kafka.pojo.ObjectoPojo;

public class Connection {

	DB BaseDatos;
	DBCollection collection;
	BasicDBObject document = new BasicDBObject();

	@SuppressWarnings("deprecation")
	public Connection() {
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			BaseDatos = mongo.getDB("ToDo");
			collection = BaseDatos.getCollection("ToDo");
			System.out.println("Conexion to database succesfull");

		} catch (Exception e) {

		}
	}

	public boolean insertar(Integer id,String accion) {
		document.put("id", id);
		document.put("accion", accion);
		collection.insert(document);
		return true;
	}

	public void mostrar() {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println("--> " + cursor.next());
		}
	}
	
	public DBCursor mostrarById(int id,String accion) {		
		DBObject query = new BasicDBObject();
		  query.put("id", id);
		  query.put("accion", accion);
		DBCursor cursor = collection.find(query);
		Gson gson = new Gson();
		
		
		cursor.forEach(
				e -> {
//					System.out.println(e.toString());
//					System.out.println("ID -->"+e.get("id"));
//					System.out.println("ACCION -->"+e.get("accion"));				
					String json = e.toString();
					ObjectoPojo staff = gson.fromJson(json, ObjectoPojo.class);
					System.out.println("Objecto pojo --> "+staff.toString());
					
				}				
				);
		
		while (cursor.hasNext()) {
			System.out.println("Desde mostrarById--> " + cursor.next());			
		}
		return cursor;
	}

	public boolean actualizar(String accionVieja, String accionNueva) {
		document.put("accion", accionVieja);
		BasicDBObject documentNuevo = new BasicDBObject();
		documentNuevo.put("accion", accionNueva);
		collection.findAndModify(document, documentNuevo);
		return true;
	}

	
	public boolean eliminar(String accion) {
		document.put("accion", accion);
		collection.remove(document);						
		return true;
	}
	
}

