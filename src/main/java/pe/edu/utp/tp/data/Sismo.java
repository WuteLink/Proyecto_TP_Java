package pe.edu.utp.tp.data;

import java.sql.Time;
import java.util.Date;


public class Sismo {
    private int id;
    private Date fecha_utc;
    private Time hora_utc;
    private double latitud;
    private double longutid;
    private int profundidad;
    private float magnitud;

    public Sismo(){
    }

    public Sismo(int id, Date fecha_utc, Time hora_utc, double latitud, double longutid, int profundidad, float magnitud) {
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

    public Date getFecha_utc() {
        return fecha_utc;
    }

    public void setFecha_utc(Date fecha_utc) {
        this.fecha_utc = fecha_utc;
    }

    public Time getHora_utc() {
        return hora_utc;
    }

    public void setHora_utc(Time hora_utc) {
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

    @Override
    public String toString() {
        return "Sismo{" +
                "id=" + id +
                ", fecha_utc=" + fecha_utc +
                ", hora_utc=" + hora_utc +
                ", latitud=" + latitud +
                ", longutid=" + longutid +
                ", profundidad=" + profundidad +
                ", magnitud=" + magnitud +
                '}';
    }
}
