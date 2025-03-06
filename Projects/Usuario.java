package principal;

public class Usuario {
    
    private String nombre;
    private String email;
    private Tarea[] tareas;
    private int contadorTareas;

    
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.tareas = new Tarea[10]; // Capacidad inicial de 10
        this.contadorTareas = 0;
    }

    
    public void crearTarea(String descripcion, String fechaLimite, int prioridad) {
        Tarea nuevaTarea = new Tarea(descripcion, fechaLimite, prioridad);
        tareas[contadorTareas++] = nuevaTarea;
    }

    public void editarTarea(int i, String nuevaDescripcion, String nuevaFecha, int nuevaPrioridad) {
        if (i >= 0 && i < contadorTareas) {
            Tarea tarea = tareas[i];
            tarea.editarDescripcion(nuevaDescripcion);
            tarea.editarFechaLimite(nuevaFecha);
            tarea.editarPrioridad(nuevaPrioridad);
        }
    }

    public void eliminarTarea(int i) {
        if (i >= 0 && i < contadorTareas - 1) {
            for (int j = i; j < contadorTareas; j++) {
                tareas[j] = tareas[j + 1];
            }
            contadorTareas--;
        }
    }

    public void obtenerListaDeTareas() {
        for (int i = 0; i < contadorTareas; i++) {
        	System.out.println((i + 1) + ". " + tareas[i]);
        }
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String datosParaGuardar() {
        StringBuilder datos = new StringBuilder();
        datos.append(nombre).append(";").append(email).append("\n");
        datos.append(contadorTareas).append("\n"); 

        for (int i = 0; i < contadorTareas; i++) {
            Tarea tarea = tareas[i];
            datos.append(tarea.getDescripcion()).append(";")
                 .append(tarea.getFechaLimite()).append(";")
                 .append(tarea.getPrioridad()).append("\n");
        }

        return datos.toString();  
    }
    
}
