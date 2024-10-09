package org.example.practica;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "casetas")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "nombre", "titular", "aforo", "tipocaseta" })

public class CasetaFeria {
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "titular")
    private String titular;
    @XmlElement(name = "aforo")
    private int aforo;
    @XmlElement(name = "tipocaseta")
    private String tipocaseta;

    public CasetaFeria() {}

    public CasetaFeria(int id, String nombre, String titular, int aforo, String tipocaseta) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.titular = titular;
        this.aforo = aforo;
        this.tipocaseta = tipocaseta;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }
    public int getAforo() {
        return aforo;
    }
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
    public String getTipocaseta() {
        return tipocaseta;
    }
    public void setTipocaseta(String tipocaseta) {
        this.tipocaseta = tipocaseta;
    }

    @Override
    public String toString() {
        return "CasetaFeria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titular='" + titular + '\'' +
                ", aforo=" + aforo +
                ", tipocaseta='" + tipocaseta + '\'' +
                '}';
    }
}
