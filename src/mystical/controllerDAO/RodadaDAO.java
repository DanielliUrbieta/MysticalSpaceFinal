/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controllerDAO;

import java.util.List;
import mystical.model.Rodada;

/**
 *
 * @author Pedro
 */
public class RodadaDAO extends AbstractDAO {

    public RodadaDAO() {
        super();
    }

    /**
     * Insert a new Partida into the database.
     *
     * @param obj
     */
    public void save(Rodada obj) {
        if (!super.save(obj)) {
            System.out.println("Erro ao salvar Partida");
        }
    }

    /**
     * Updates a new Partida into the database.
     *
     * @param obj
     */
    public void update(Rodada obj) {
        if (!super.update(obj)) {
            System.out.println("Erro ao atualizar partida");
        }
    }

    /**
     * Delete a detached partida from the database.
     *
     * @param obj
     */
    public void delete(Rodada obj) {
        if (!super.delete(obj)) {
            System.out.println("Erro ao Deletar Partida");
        }
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Rodada find(Long id) {
        return (Rodada) super.find(Rodada.class, id);
    }

    /**
     * Lista todos as Partidas do banco de dados
     *
     * @return
     */
    public List findAll() {
        return super.findAll(Rodada.class);
    }

    
    public List findAllById(int id){
        String query = "from "+Rodada.class.getName()+" where campeonato_id_campeonato = "+id;
        return super.findAllById(id, query);
    }
}
