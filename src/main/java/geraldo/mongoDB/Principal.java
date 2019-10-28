package geraldo.mongoDB;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.DBCursor;

import geraldo.kafka.pojo.ObjectoPojo;

public class Principal {

	public static void main(String[] args) {
		
		
		Connection conexion = new Connection();
		DBCursor cursor = conexion.mostrarById(8, "Jugar");		
				
		
	
		
		
//		conexion.insertar(8, "Jugar");
//		conexion.mostrar();
//		conexion.mostrarById(8,"Jugar");
//		conexion.actualizar("Saltar", "Comer");
//		conexion.mostrar();
//		conexion.insertar("Saltar");
//		conexion.insertar("Llamar");
		

	}

}
