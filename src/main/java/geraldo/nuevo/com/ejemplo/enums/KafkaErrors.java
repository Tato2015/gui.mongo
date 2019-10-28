package geraldo.nuevo.com.ejemplo.enums;

public enum KafkaErrors {

	PRODUCER_NOT_CONNECTED("Not alive connection available");
	
	private String message;
	
	private KafkaErrors(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }
	
}
