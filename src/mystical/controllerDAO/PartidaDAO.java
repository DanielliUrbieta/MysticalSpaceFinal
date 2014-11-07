/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controllerDAO;

import java.util.List;
import mystical.model.Partida;

/**
 *
 * @author Pedro
 */
public class PartidaDAO extends AbstractDAO {

    public PartidaDAO() {
        super();
    }

    /**
     * Insert a new Partida into the database.
     *
     * @param partida
     * @return 
     */
    public boolean save(Partida partida) {
        if (!super.save(partida)) {
            System.out.println("Erro ao salvar Partida");
            return true;
        }
        return false;
    }

    /**
     * Updates a new Partida into the database.
     *
     * @param partida
     */
    public void update(Partida partida) {
        if (!super.update(partida)) {
            System.out.println("Erro ao atualizar partida");
        }
    }

    /**
     * Delete a detached partida from the database.
     *
     * @param partida
     */
    public void delete(Partida partida) {
        if (!super.delete(partida)) {
            System.out.println("Erro ao Deletar Partida");
        }
    }

    /**
     * Find an Event by its primary key.
     *
     * @param id
     * @return
     */
    public Partida find(Long id) {
        return (Partida) super.find(Partida.class, id);
    }

    /**
     * Lista todos as Partidas do banco de dados
     *
     * @return
     */
    public List findAll() {
        return super.findAll(Partida.class);
    }
    
    /**
     *
     * @param id
     * @return
     */
    
    public List findAllById(int id){
        String query = "from "+Partida.class.getName()+" where rodada_id_rodada = "+id;
        return super.findAllById(id, query);
    }

    public List findAllPartidasByCampeonato(int i) {
        
        
        String q = "select p.id_partida, p.tipo_resultado, p.vencedor,"
                + " p.rodada_id_rodada from partida as p, campeonato as c,"
                + " rodada as r where r.campeonato_id_campeonato = c.id_campeonato"
                + " and p.rodada_id_rodada = r.id_rodada and c.id_campeonato = "+i;
        
        System.out.println("Oi vc chegou na DAO");
        return super.findAllPartidasByCampeonato(q);
    }
    
    
   
}
