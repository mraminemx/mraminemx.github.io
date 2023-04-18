package co.tenisu.atelierrestapp.controller;

import co.tenisu.atelierrestapp.dto.ResponseApi;
import co.tenisu.atelierrestapp.dto.StatisticResponseDto;
import co.tenisu.atelierrestapp.entity.Player;
import co.tenisu.atelierrestapp.exception.BusinessErrorCode;
import co.tenisu.atelierrestapp.exception.BusinessException;
import co.tenisu.atelierrestapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping(value = "/joueurs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * Retourne tous les joueurs triée du meilleur au moins bon (on se basant sur le rank)
     *
     * @return la liste des joueurs triée avec l'ensemble de leurs informations
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllPlayersOrderByRank() {
        try {
            List<Player> players = playerService.getAllPlayersOrderByRank();
            ResponseApi responseApi = new ResponseApi(BusinessErrorCode.OK, players);
            return ResponseEntity.ok(responseApi);
        } catch (BusinessException e) {
            ResponseApi responseApi = new ResponseApi(e.getCode(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi);
        }
    }

    /**
     * Retourne les informations associées au joueur avec l'id en paramêtre
     *
     * @param id id du joueur
     *
     * @return l'objet joueur avec l'ensemble de ses informations
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPlayerById(@PathVariable("id") Long id) {
        try {
            Player player = playerService.getPlayerById(id);
            ResponseApi responseApi = new ResponseApi(BusinessErrorCode.OK, player);
            return ResponseEntity.ok(responseApi);
        } catch (BusinessException e) {
            ResponseApi responseApi = new ResponseApi(e.getCode(), e.getMessage());
            if(e.getCode().equals(BusinessErrorCode.PLAYER_NOT_FOUND)) 
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseApi);
            else 
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi);
        }
    }
    
    /**
     * Retourne des statistiques sur les joueurs
     *
     * @return un objet contenant les states des joueurs
     */
    @GetMapping("/statistics")
    public ResponseEntity<Object> getStatistics() {
        try {
            StatisticResponseDto statisticsObj = playerService.getStatistics();
            ResponseApi responseApi = new ResponseApi(BusinessErrorCode.OK, statisticsObj);
            return ResponseEntity.ok(responseApi);
        } catch (BusinessException e) {
            ResponseApi responseApi = new ResponseApi(e.getCode(), e.getMessage());
            if(e.getCode().equals(BusinessErrorCode.PLAYER_NOT_FOUND))
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseApi);
            else
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseApi);
        }
    }

}
