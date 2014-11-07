/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "RODADA")
public class Rodada implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rodada")
    private int idRodada;
    
    @Column(name = "numero")
    private int numero;
    
    @Column(name = "tempo")
    private String tempo;
    
    @OneToMany(mappedBy="rodada",cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Partida> partidas = new ArrayList<Partida>();
    
    
    
    @ManyToOne
    private Campeonato campeonato;

    public Rodada() {
    }

    public int getIdRodada() {
        return idRodada;
    }

    public void setIdRodada(int idRodada) {
        this.idRodada = idRodada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Collection<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Collection<Partida> partidas) {
        this.partidas = partidas;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }
    
    @Override
        public String toString() {
            
            return String.valueOf(numero);
        }
    
}
