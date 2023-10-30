package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.FootballClub;
import entities.MatchPlayed;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;
import services.PremierLeagueManager;
import utils.ApplicationUtil;
import utils.SortByGF;
import utils.SortByWins;
import java.io.IOException;
import java.util.List;

public class LeagueController extends Controller {

    private final PremierLeagueManager leagueManager = PremierLeagueManager.getInstance();

    public Result listClubs(String sortMethod){
        try {
            leagueManager.readFile();
            List<FootballClub> clubList = leagueManager.getClubList();
            if (sortMethod.equals("wins")){
                SortByWins sortByWins = new SortByWins();
                clubList.sort(sortByWins.reversed());
            }
            if (sortMethod.equals("gf")){
                SortByGF sortByGF = new SortByGF();
                clubList.sort(sortByGF.reversed());
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObject = mapper.convertValue(clubList,JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonObject,true));
        } catch (IOException | ClassNotFoundException e) {
            return internalServerError(ApplicationUtil.createResponse("Unable to read data",false));
        }
    }

    public Result listMatches(){
        try{
            leagueManager.readFile();
            List<MatchPlayed> matchPlayedList = leagueManager.getMatchesPlayedList();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonObject = mapper.convertValue(matchPlayedList, JsonNode.class);
            return ok(ApplicationUtil.createResponse(jsonObject,true));
        } catch (IOException | ClassNotFoundException e) {
            return internalServerError(ApplicationUtil.createResponse("Unable to read data",false));
        }
    }

    public Result createRandomMatch(){
        try {
            MatchPlayed matchPlayed = leagueManager.createRandomMatch();
            JsonNode jsonObject = Json.toJson(matchPlayed);
            leagueManager.saveToFile();
            return ok(ApplicationUtil.createResponse(jsonObject,true));
        } catch (IllegalArgumentException e) {
            return badRequest(ApplicationUtil.createResponse(
                    e.getMessage(),false));
        } catch (IOException e) {
            return internalServerError(ApplicationUtil.createResponse("Data was not saved due to a server error",false));
        }
    }
}
