package br.agrimedi.agrimediapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ciclo_Crescimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10, nullable = false) // para strings com 1000 caracteres
    private String dataPlantio;
    @Column(length = 10, nullable = false) 
    private String dataColheita;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDataPlantio() {
        return dataPlantio;
    }
    public void setDataPlantio(String dataPlantio) {
        this.dataPlantio = dataPlantio;
    }
    public String getDataColheita() {
        return dataColheita;
    }
    public void setDataColheita(String dataColheita) {
        this.dataColheita = dataColheita;
    }

    

    

    
    
}
