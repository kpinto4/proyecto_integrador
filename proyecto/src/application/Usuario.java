package application;

public class Usuario {
	private String CedUsuario;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Usuario;
    private String Contraseña;
    private String Cargo;
	
	
	
	public Usuario(String CedUsuario, String Nombre, String Apellido, String Direccion, String Telefono, String Usuario, String Contraseña, String Cargo) {
        this.CedUsuario = CedUsuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Cargo = Cargo;
       
    }



	public String getCedUsuario() {
		return CedUsuario;
	}



	public void setCedUsuario(String cedUsuario) {
		CedUsuario = cedUsuario;
	}



	public String getNombre() {
		return Nombre;
	}



	public void setNombre(String nombre) {
		Nombre = nombre;
	}



	public String getApellido() {
		return Apellido;
	}



	public void setApellido(String apellido) {
		Apellido = apellido;
	}



	public String getDireccion() {
		return Direccion;
	}



	public void setDireccion(String direccion) {
		Direccion = direccion;
	}



	public String getTelefono() {
		return Telefono;
	}



	public void setTelefono(String telefono) {
		Telefono = telefono;
	}



	public String getUsuario() {
		return Usuario;
	}



	public void setUsuario(String usuario) {
		Usuario = usuario;
	}



	public String getContraseña() {
		return Contraseña;
	}



	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}



	public String getCargo() {
		return Cargo;
	}



	public void setCargo(String cargo) {
		Cargo = cargo;
	}

}
