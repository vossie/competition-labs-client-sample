package com.clabs.examples;

import com.clabs.com.clabs.utils.Connection;
import com.clabs.com.clabs.utils.ConnectionResultWrapper;
import com.clabs.models.Game;
import com.clabs.models.Response;
import com.clabs.stories.Games;

import java.util.ArrayList;
import java.util.List;

abstract public class GamesExamples {

    public static void example(Connection connection) {
        try {

            // 1. Add a new game
            Game sampleGame = new Game()
                    .setExternalGameRefId(-1)
                    .setName("Test Game")
                    .setDescription("Test game")
                    .setGameType("slot")
                    .setAdjustmentFactor("2")
                    .setPointsStyle("HighestWins");

            Games.InsertGame(connection, sampleGame);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the games I have uploaded.
            Response<ArrayList<Game>> games = Games.GetListOfAllMyGames(connection);

            // 4. Get a list of games based on my external reference id.
            Response<ArrayList<Game>> gamesByMyId = Games.GetGamesByExternalRefId(connection,"-1");

            // 5. Update and existing records
            for (int i=0; i<gamesByMyId.data.size(); i++){
                Games.UpdateGameById(connection, gamesByMyId.data.get(i).setDescription("Updated to " + i));
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