package com.clabs.examples;

import com.clabs.utils.Connection;
import com.clabs.utils.ConnectionResultWrapper;
import com.clabs.models.Game;
import com.clabs.models.Response;
import com.clabs.stories.Games;

import java.util.ArrayList;
import java.util.List;

abstract public class GamesExamples extends Common {

    public static void example(Connection connection) {
        try {

            // 1. Add a new game
            Game sampleGame = new Game()
                    .setGameRefId("-1")
                    .setName("Test Game")
                    .setDescription("Test game")
                    .setGameType("slot")
                    .setAdjustmentFactor(2f)
                    .setPointsStyle("HighestWins");

            Response insertedGamesResponse = Games.InsertGame(connection, sampleGame);
            printErrorsFromResponse(insertedGamesResponse);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the games I have uploaded.
            Response<ArrayList<Game>> games = Games.GetListOfAllMyGames(connection);
            printErrorsFromResponse(games);

            // 4. Get a list of games based on my external reference id.
            Response<ArrayList<Game>> gamesByMyId = Games.GetGamesByExternalRefId(connection,"-1");
            printErrorsFromResponse(gamesByMyId);

            // 5. Update and existing records
            for (int i=0; i<gamesByMyId.data.size(); i++){
                Response r = Games.UpdateGameById(connection, gamesByMyId.data.get(i).setDescription("Updated to " + i));
                printErrorsFromResponse(r);
            }

            // 6. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 7. Delete the records we just inserted
            List<ConnectionResultWrapper> deletedResponse = Games.PermanentlyDeleteListOfGames(connection, gamesByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
