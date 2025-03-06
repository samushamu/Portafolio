package principal;

public class Tarea {

    private String descripcion;
    private String fechaLimite; 
    private int prioridad;


    public Tarea(String descripcion, String fechaLimite, int prioridad) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
    }

    public void editarDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

    public void editarFechaLimite(String nuevaFecha) {
        this.fechaLimite = nuevaFecha;
    }

    public void editarPrioridad(int nuevaPrioridad) {
        this.prioridad = nuevaPrioridad;
    }
    
    
    public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	// Se podria llevar a cabo con getters en la clase usuario
    @Override
    public String toString() {
        return "Tarea: " + descripcion + ", Fecha límite: " + fechaLimite + ", Prioridad: " + prioridad;
    }
}


