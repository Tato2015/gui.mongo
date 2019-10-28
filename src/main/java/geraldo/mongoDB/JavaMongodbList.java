package geraldo.mongoDB;


import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * Class tests for MongoDB queries by using collections.
 * Clase de pruebas para las consultas a MongoDB mediante el uso de colecciones.
 * @author xules
 */
public class JavaMongodbList { 
    private MongoClient mongoClient;    // Java MongoDB client (Cliente Java MongoDB)
    private MongoDatabase mongodb;      // Database object (Objeto base de datos)
    /**
     * We establish the connection with the database <b>test</b>.
     * Establecemos la conexión con la base de datos <b>test</b>.
     */
    public void connectDatabase(){
        setMongoClient(new MongoClient());             
        setMongodb(getMongoClient().getDatabase("ToDo"));
    }
    /**
     * Get the list of all the restaurants (each is a document) in the database
     * and shown on screen.
     * Obtenemos la lista de todos los restaurantes (cada uno es un documento)
     * de la base de datos y los mostramos por pantalla.
     */
    public void listRestaurants(){ 
        // To return all documents in a collection, call the find method without a criteria document.
        // Para devolver todos los documentos en una colección, llamamos al método find sin ningún documento <b>criteria</b>
        FindIterable<Document> iterable = getMongodb().getCollection("restaurants").find();
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });   
    }
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMongodb() {
        return mongodb;
    }

    public void setMongodb(MongoDatabase mongodb) {
        this.mongodb = mongodb;
    }
    /**
     * We execute methods to display restaurants.
     * Ejecutamos los métodos para mostrar los restaurantes.
     * @param args 
     */
    
    public void listRestaurantsByCuisine(int id){ 
        // We return documents with the find method by setting a <b>criteria</ b> element equal to the cuisine.
        // Devolvemos los documentos con el método find estableciendo un <b>criteria</b> igual para el elemento cuisine.
        FindIterable<Document> iterable = getMongodb().getCollection("ToDO").find(new Document("id", id));
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    } 
    
    public static void main(String args[]){
        JavaMongodbList javaMongodbList = new JavaMongodbList();
        javaMongodbList.connectDatabase();
        // javaMongodbList.listRestaurants();
        javaMongodbList.listRestaurantsByCuisine(8);
    }
}