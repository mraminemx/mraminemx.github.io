package co.tenisu.atelierrestapp;

import co.tenisu.atelierrestapp.entity.Player;
import co.tenisu.atelierrestapp.entity.PlayerList;
import co.tenisu.atelierrestapp.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private PlayerRepository playerRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @PostConstruct
    public void loadData() throws IOException, URISyntaxException {
        // Charger le fichier JSON
        String filename = "players.json";
        URL resource = getClass().getClassLoader().getResource("data/" + filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            LOGGER.debug("Chargement du fichier json {} en base H2", filename);
            File jsonFile = new File(resource.toURI());
            ObjectMapper objectMapper = new ObjectMapper();
            PlayerList playerList = objectMapper.readValue(jsonFile, PlayerList.class);
    
            // Enregistrer les joueurs dans la base de données
            List<Player> players = Arrays.asList(playerList.getPlayers());
            playerRepository.saveAll(players);
        }
    }
}
