package co.tenisu.atelierrestapp.repository;

import co.tenisu.atelierrestapp.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    
    // Querydsl to get player by id
    Player findById(Long id);

    // Native query to calculate median of height
    @Query(value = 
            "SELECT AVG(height) " +
            "FROM (" 
            + "     SELECT height " +
            "       FROM player " +
            "       ORDER BY height " +
            "       LIMIT 2 - (SELECT COUNT(*) FROM player) % 2 " +
            "       OFFSET (SELECT (COUNT(*) - 1) / 2 FROM player)" 
            + ") AS heightSubQuery",
            nativeQuery = true)
    Double calculateMedianHeight();

    // Native query to get country with highest win ratio
    @Query(value = 
            "SELECT p.country_code " + 
            "FROM  player_last pl " +
            "JOIN player p ON p.id = pl.player_id " +
            "GROUP BY p.country_code " +
            "HAVING SUM(pl.last) = ( " +
            "    SELECT MAX(total_last) " +
            "    FROM (SELECT p2.country_code, SUM(pl2.last) AS total_last " + 
            "          FROM player_last pl2 " + 
            "          JOIN player p2 ON p2.id = pl2.player_id " +
            "          GROUP BY p2.country_code) AS subquery) " +
            "LIMIT 1 ",
            nativeQuery = true)
    String getCountryWithHighestWinRatio();

    // Native query to calculate average IMC
    @Query(value = 
            "SELECT AVG(weight / (height/100.0 * height/100.0)) " + 
            "FROM player " +
            "WHERE height IS NOT NULL " +
            "LIMIT 1"
            , nativeQuery = true)
    Double calculateAverageIMC();
}
