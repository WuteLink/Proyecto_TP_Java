package pe.edu.utp.tp.log;

public class Usuario {
    private String codigo;
    private String contraseña;

    public Usuario(String codigo, String contraseña) {
        this.codigo = codigo;
        this.contraseña = contraseña;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
