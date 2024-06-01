package application;

/**
 * La clase Cliente representa a un cliente en el sistema y almacena sus datos personales, como cédula, nombre, dirección y teléfono.
 * @author Kevin Santiago
 */
public class Cliente {
    private String Cedula;
    private String Nombre;
    private String Direccion;
    private String Telefono;
    

    /**
     * Constructor para inicializar un objeto Cliente con sus propiedades.
     * @param cedula La cédula del cliente.
     * @param nombre El nombre del cliente.
     * @param direccion La dirección del cliente.
     * @param telefono El teléfono del cliente.
     */
    public Cliente(String Cedula, String Nombre, String Direccion, String Telefono) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
       
    }
    
    // Métodos getter y setter para cedula
	public String getCedula() {
		return Cedula;
	}

	public void setCedula(String cedula) {
		Cedula = cedula;
	}

	// Métodos getter y setter para nombre
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	// Métodos getter y setter para direccion
	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	// Métodos getter y setter para telefono
	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

}
