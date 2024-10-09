package org.example.practica;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "casetas" })
public class Casetas {
    @XmlElementWrapper(name = "casetas")
    @XmlElement(name = "caseta")
    private ArrayList<CasetaFeria> casetas;
    public ArrayList<CasetaFeria> getCasetas() {
        return casetas;
    }
    public void setCasetas(ArrayList<CasetaFeria> casetas) {
        this.casetas = casetas;
    }

    public Casetas() {
        casetas = new ArrayList<CasetaFeria>();
    }
    public Casetas(ArrayList<CasetaFeria> casetas) {
        this.casetas = casetas;
    }
}
