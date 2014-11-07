/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controllerDAO;

import java.util.List;
import mystical.model.Campeonato;

/**
 *
 * @author Danielli
 */
public class CampeonatoDAO extends AbstractDAO {
    public CampeonatoDAO() {
        super();
    }

    /**
     * Insert a new Partida into the database.
     * @param obj
     */
    public void save(Campeonato obj){
        if(!super.save(obj))
            System.out.println("Erro ao salvar Partida");
    }

    /**
     * Updates a new Partida into the database.
     * @param obj
     */
    public void update(Campeonato obj){
        if(!super.update(obj))
            System.out.println("Erro ao atualizar partida");
    }

    /**
     * Delete a detached partida from the database.
     * @param obj
     */
    public void delete(Campeonato obj){
        if(!super.delete(obj))
            System.out.println("Erro ao Deletar Partida");
    }

    /**
     * Find an Event by its primary key.
     * @param id
     * @return
     */
    public Campeonato find(Long id){
        return (Campeonato) super.find(Campeonato.class, id);
    }

    /**
     * Lista todos as Partidas do banco de dados
     * @return
     */
    public List findAll(){
        return super.findAll(Campeonato.class);
    }

}
