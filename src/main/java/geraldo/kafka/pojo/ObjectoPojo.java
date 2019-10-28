package geraldo.kafka.pojo;

public class ObjectoPojo {
	private ObjectoJson _id;
	private int id;
	private String accion;
	
	
	
	
	


	public ObjectoJson get_id() {
		return _id;
	}
	public void set_id(ObjectoJson _id) {
		this._id = _id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	@Override
	public String toString() {
		return "ObjectoPojo [_id=" + _id + ", id=" + id + ", accion=" + accion + "]";
	}

	

}
