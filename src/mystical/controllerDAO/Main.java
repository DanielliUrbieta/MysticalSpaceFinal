/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystical.controllerDAO;

import java.util.Date;
import java.util.List;
import mystical.model.Campeonato;
import mystical.model.Partida;
import mystical.model.Rodada;

/**
 *
 * @author Danielli
 */
public class Main {
    public static void main(String[] args) {
        
        /*
        
        //cria um capeonato
        Campeonato campeonato = new Campeonato();
        campeonato.setDataCampeonato(new Date(System.currentTimeMillis()));
        campeonato.setNomeCampeonato("Yugioh! Champion");
        campeonato.setTipoCampeonato("Suiço");
        
        //salva o campeonato
        CampeonatoDAO campDAO = new CampeonatoDAO();
        campDAO.save(campeonato);
       // campDAO.find(campeonato.getClass(), 1);
        
        
        
        
        
        //cria um capeonato
        Campeonato campeonato2 = new Campeonato();
        campeonato2.setDataCampeonato(new Date(System.currentTimeMillis()));
        campeonato2.setNomeCampeonato("Magic! Champion");
        campeonato2.setTipoCampeonato("Eliminatorio");
        
        //salva o campeonato
        campDAO.save(campeonato2);
        
        
        
       //cria uma rodada 
       Rodada rodada = new Rodada();
       //o campeonato acima é atribuido a esta rodada
       rodada.setCampeonato(campeonato);
       rodada.setNumero(1);
       rodada.setTempo("1h");
       //salva a rodada -- ainda sem nenhuma partida
       RodadaDAO rodadaDAO = new RodadaDAO();
       rodadaDAO.save(rodada);
       
       
       
       
       //cria uma rodada 
       Rodada rodada2 = new Rodada();
       //o campeonato acima é atribuido a esta rodada
       rodada2.setCampeonato(campeonato);
       rodada2.setNumero(2);
       rodada2.setTempo("2h");
       //salva a rodada -- ainda sem nenhuma partida
       
       rodadaDAO.save(rodada2);
      
       
       //cria uma rodada 
       Rodada rodada3 = new Rodada();
       //o campeonato acima é atribuido a esta rodada
       rodada3.setCampeonato(campeonato);
       rodada3.setNumero(3);
       rodada3.setTempo("3h");
       //salva a rodada -- ainda sem nenhuma partida
       
       rodadaDAO.save(rodada3);
             
       
       
       
       
       
       //cria uma rodada 
       Rodada rodada4 = new Rodada();
       //o campeonato acima é atribuido a esta rodada
       rodada4.setCampeonato(campeonato2);
       rodada4.setNumero(4);
       rodada4.setTempo("4h");
       //salva a rodada -- ainda sem nenhuma partida
       
       rodadaDAO.save(rodada4);
       
       
       
       
       //cria uma rodada 
       Rodada rodada5 = new Rodada();
       //o campeonato acima é atribuido a esta rodada
       rodada5.setCampeonato(campeonato2);
       rodada5.setNumero(5);
       rodada5.setTempo("5h");
       //salva a rodada -- ainda sem nenhuma partida
       
       rodadaDAO.save(rodada5);
       
       
       
       
       
       //cria uma rodada 
       Rodada rodada6 = new Rodada();
       //o campeonat6 acima é atribuido a esta rodada
       rodada6.setCampeonato(campeonato2);
       rodada6.setNumero(6);
       rodada6.setTempo("6h");
       //salva a rodada -- ainda sem nenhuma partida
       
       rodadaDAO.save(rodada6);
       */
       
        PartidaDAO pDAO = new PartidaDAO();
        //System.out.println("estamos na main antes de instanciar a lista");
        List<Partida> listinha =  pDAO.findAllById(13);
        
        //System.out.println("estamos na main depois de chamar o método do bd");
        
        for(Partida p : listinha){
            System.out.println("olá moço");
        }
        
        
    }
}
