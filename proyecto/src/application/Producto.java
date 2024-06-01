package application;

/**
 * La clase Producto representa un producto en el sistema.
 * @author Kevin Santiago
 *
 */
public class Producto {
    private String referencia;
    private String descripcion;
    private String categoria_id;
    private String stock;
    private String valor;
    

    /**
     * Constructor para inicializar un objeto Producto con sus propiedades
     * @param referencia
     * @param descripcion
     * @param categoria_id
     * @param stock
     * @param valor
     */
    public Producto(String referencia, String descripcion, String categoria_id,String stock, String valor) {
        this.referencia = referencia;
        this.descripcion = descripcion;
        this.categoria_id = categoria_id;
        this.stock = stock;
        this.valor = valor;
    }

    // Métodos getter y setter para referencia
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    // Métodos getter y setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos getter y setter para categoria
    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    // Métodos getter y setter para stock
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    // Métodos getter y setter para valor
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

       
}