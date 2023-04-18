package co.tenisu.atelierrestapp.service;

import co.tenisu.atelierrestapp.dto.StatisticResponseDto;
import co.tenisu.atelierrestapp.entity.Player;
import java.util.List;

public interface PlayerService {

    /**
     * Retourne tous les joueurs triée du meilleur au moins bon (on se basant sur le rank)
     *
     * @return la liste des joueurs triée avec l'ensemble de leurs informations
     */
    List<Player> getAllPlayersOrderByRank();
    
    /**
     * Retourne les informations associées au joueur avec l'id en paramêtre
     *
     * @param id id du joueur
     *
     * @return l'objet joueur avec l'ensemble de ses informations
     */
    Player getPlayerById(Long id);

    /**
     * Retourne des statistiques sur les joueurs
     *
     * @return un objet contenant les states des joueurs
     */
    StatisticResponseDto getStatistics();
}
