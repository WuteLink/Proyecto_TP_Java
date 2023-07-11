package pe.edu.utp.tp;

public class Sismo {
    private int id;
    private int fecha_utc;
    private int hora_utc;
    private double latitud;
    private double longutid;
    private int profundidad;
    private float magnitud;

    public Sismo(int id, int fecha_utc, int hora_utc, double latitud, double longutid, int profundidad, float magnitud) {
        this.id = id;
        this.fecha_utc = fecha_utc;
        this.hora_utc = hora_utc;
        this.latitud = latitud;
        this.longutid = longutid;
        this.profundidad = profundidad;
        this.magnitud = magnitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFecha_utc() {
        return fecha_utc;
    }

    public void setFecha_utc(int fecha_utc) {
        this.fecha_utc = fecha_utc;
    }

    public int getHora_utc() {
        return hora_utc;
    }

    public void setHora_utc(int hora_utc) {
        this.hora_utc = hora_utc;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongutid() {
        return longutid;
    }

    public void setLongutid(double longutid) {
        this.longutid = longutid;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public float getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(float magnitud) {
        this.magnitud = magnitud;
    }
}
