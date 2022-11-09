package nestedclasses.soccer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChampionshipTest {

    List<TeamStatistics> teamStatistics;

    TeamStatistics team = new TeamStatistics("Valencia");

    @BeforeEach
    void init() {
        Championship championship = new Championship();
        championship.addTeam(team);
        championship.addGame(new Championship.GameResult("Real", "Barcelona", 2, 2));
        championship.addGame(new Championship.GameResult("Atletico", "Valencia", 0, 2));
        championship.addGame(new Championship.GameResult("Real", "Atletico", 1, 0));
        championship.addGame(new Championship.GameResult("Valencia", "Barcelona", 1, 2));
        teamStatistics = championship.getLeagueTable();
    }

    @Test
    void testAddGame() {
        assertEquals(4,  teamStatistics.size());
        assertTrue(teamStatistics.contains(team));
    }

    @Test
    void testOneTeam() {
        int index = teamStatistics.indexOf(team);
        TeamStatistics valencia = teamStatistics.get(index);

        assertEquals(2, valencia.getPlayed());
        assertEquals(1, valencia.getWon());
        assertEquals(1, valencia.getLost());
        assertEquals(0, valencia.getTied());
        assertEquals(3, valencia.getGoalsFor());
        assertEquals(2, valencia.getGoalsAgainst());
        assertEquals(3, valencia.getPoints());
    }
}