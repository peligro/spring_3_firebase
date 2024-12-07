package cl.tamila.firebase.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern.Flag;
public class CotizacionesDto {
    @NotEmpty(message = "El campo nombre es obligatorio.")
    private String nombre;
    @NotEmpty(message = "El campo telefono es obligatorio.")
    private String telefono;
    @NotEmpty(message = "El campo correo es obligatorio.")
    @Email(message = "El correo ingresado no es válido", flags = { Flag.CASE_INSENSITIVE })
    private String correo;
    @NotEmpty(message = "El campo ciudad es obligatorio.")
    private String ciudad;
    @NotEmpty(message = "El campo direccion es obligatorio.")
    private String direccion;
    @NotEmpty(message = "El campo detalle es obligatorio.")
    private String detalle;
    public CotizacionesDto() {

    }

    public CotizacionesDto(String nombre, String telefono, String correo, String ciudad, String direccion, String detalle) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
