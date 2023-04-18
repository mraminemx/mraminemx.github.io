package co.tenisu.atelierrestapp.service.impl;

import co.tenisu.atelierrestapp.dto.StatisticResponseDto;
import co.tenisu.atelierrestapp.entity.Player;
import co.tenisu.atelierrestapp.exception.BusinessErrorCode;
import co.tenisu.atelierrestapp.exception.BusinessException;
import co.tenisu.atelierrestapp.repository.PlayerRepository;
import co.tenisu.atelierrestapp.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PlayerServiceImpl implements PlayerService {

    private final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired 
    PlayerRepository playerRepository;
    
    @Override 
    public List<Player> getAllPlayersOrderByRank() {
        try {
            LOGGER.info("Récupération de tous les joueurs triée du meilleur au moins bon (on se basant sur le rank)");
            return playerRepository.findAll(Sort.by("data.rank").ascending());
        } catch (Exception e) {
            String message = "Une erreur s'est produite lors de la récupération des joueurs";
            LOGGER.error(message, e);
            throw new BusinessException(BusinessErrorCode.INTERNAL_ERROR, message);
        }
    }

    @Override 
    public Player getPlayerById(Long id) {
        try {
            LOGGER.info("Récupération des informations associées au joueur avec l'id {}", id);
            Player player = playerRepository.findById(id);
            if (player != null) {
                return player;
            } else {
                throw new BusinessException(BusinessErrorCode.PLAYER_NOT_FOUND, "Joueur non trouvé");
            }
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            String message = "Une erreur s'est produite lors de la récupération d'un joueur par son id";
            LOGGER.error(message, e);
            throw new BusinessException(BusinessErrorCode.INTERNAL_ERROR, message);
        }
    }
    
    @Override
    public StatisticResponseDto getStatistics() {

        LOGGER.info("Récupération des statistiques sur les joueurs (IMC, mediane des tailles,...)");

        try {
            // Trouver le pays avec le plus grand ratio de parties gagnées
            String countryWithHighestWinRatio = playerRepository.getCountryWithHighestWinRatio();

            // Calculer l'Indice de Masse Corporel moyen (IMC) de tous les joueurs 
            Double averageIMC = playerRepository.calculateAverageIMC();

            // Calculer la médiane de la taille des joueurs
            Double medianHeight = playerRepository.calculateMedianHeight();

            // Créer la réponse
            StatisticResponseDto response = new StatisticResponseDto(countryWithHighestWinRatio, averageIMC, medianHeight);
            return response;
        } catch (Exception e) {
            String message = "Une erreur s'est produite lors de la récupération des statistiques des joueurs";
            LOGGER.error(message, e);
            throw new BusinessException(BusinessErrorCode.INTERNAL_ERROR, message);
        }
    }
}
