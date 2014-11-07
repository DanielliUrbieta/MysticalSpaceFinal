/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "CAMPEONATO")
public class Campeonato implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_campeonato")
    private int idCampeonato;
    
    @Column(name = "come_campeonato")
    private String nomeCampeonato;
    
    @Column(name = "tipo_campeonato")
    private String tipoCampeonato;
    
    @Column(name = "data_campeonato")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCampeonato;
    
    @OneToMany(mappedBy="campeonato",cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Rodada> rodadas =  new ArrayList<Rodada>();
    
    /*
    @OneToMany
    @JoinTable(name = "CAMPEONATO_FUNCIONARIO",
                joinColumns = @JoinColumn(name = "CAMPEONATO_ID"),
                inverseJoinColumns = @JoinColumn(name = "FUNCIONARIO_ID"))
    private Collection<Funcionario> funcionarios =  new ArrayList<Funcionario>();
    */

    public Campeonato() {
    }

    public int getIdCampeonato() {
        return idCampeonato;
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public String getTipoCampeonato() {
        return tipoCampeonato;
    }

    public void setTipoCampeonato(String tipoCampeonato) {
        this.tipoCampeonato = tipoCampeonato;
    }

    public Date getDataCampeonato() {
        return dataCampeonato;
    }

    public void setDataCampeonato(Date dataCampeonato) {
        this.dataCampeonato = dataCampeonato;
    }

    public Collection<Rodada> getRodadas() {
        return rodadas;
    }

    public void setRodadas(Collection<Rodada> rodadas) {
        this.rodadas = rodadas;
    }
    
    @Override
        public String toString() {
            return nomeCampeonato;
        }
    
}
