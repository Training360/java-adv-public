package nestedclasses.soccer;

import java.util.ArrayList;
import java.util.List;

public class Championship {

    private List<TeamStatistics> leagueTable;

    public Championship() {
        this.leagueTable = new ArrayList<>();
    }

    public void addTeam(TeamStatistics team) {
        leagueTable.add(team);
    }

    public void addGame(GameResult result) {
        TeamStatistics home = getTeam(result.teamHome);
        TeamStatistics quest = getTeam(result.teamGuest);
        home.played(result.goalHome, result.goalGuest);
        quest.played(result.goalGuest, result.goalHome);
    }

    public List<TeamStatistics> getLeagueTable() {
        return this.leagueTable;
    }

    private TeamStatistics getTeam(String teamName) {
        for (int i = 0; i < leagueTable.size(); i++) {
            if (leagueTable.get(i).getTeamName().equals(teamName)) {
                return leagueTable.get(i);
            }
        }
        TeamStatistics teamStatistics = new TeamStatistics(teamName);
        leagueTable.add(teamStatistics);
        return teamStatistics;
    }

    public static class GameResult {
        private String teamHome;
        private String teamGuest;
        private int goalHome;
        private int goalGuest;

        public GameResult(String teamHome, String teamGuest, int goalHome, int goalGuest) {
            this.teamHome = teamHome;
            this.teamGuest = teamGuest;
            this.goalHome = goalHome;
            this.goalGuest = goalGuest;
        }
    }
}
