package com.clabs.examples;

import com.clabs.models.Game;
import com.clabs.models.Response;
import com.clabs.stories.Games;
import com.clabs.utils.Connection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

abstract public class GamesExamples extends Common {

    public static void example(Connection connection) {
        try {

            // 1. Add a new game
            Game sampleGame = new Game()
                    .setGameRefId("-1")
                    .setName("Test Game")
                    .setDescription("Test game")
                    .setGameType("slot")
                    .setAdjustmentFactor(2f);

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
            //List<ConnectionResultWrapper> deletedResponse = Games.PermanentlyDeleteListOfGames(connection, gamesByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushAll(Connection connection) {
        try {
            Response<ArrayList<Game>> games = Games.GetListOfAllMyGames(connection);
            Games.PermanentlyDeleteListOfGames(connection, games);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void generateExampleCSV() {

        String ColumnHeader1 = "gameRefId";
        String ColumnHeader2 = "gameName";
        String ColumnHeader3 = "gameType";
        String ColumnHeader4 = "pointsStyle";
        String ColumnHeader5 = "adjustmentFactor";
        int countOfRowsToGenerate = 10000;

        float adjustmentFactor = 1.0f;

        try {
            FileWriter fileWriter = new FileWriter("export-games-sample.csv");

            fileWriter
                    .append(ColumnHeader1).append(',')
                    .append(ColumnHeader2).append(',')
                    .append(ColumnHeader3).append(',')
                    .append(ColumnHeader4).append(',')
                    .append(ColumnHeader5).append('\n');

            for (int i = 0; i < countOfRowsToGenerate; i ++) {
                fileWriter
                        .append("my-custom-game-ref-" + i).append(',')
                        .append("game-Name-" + i).append(',')
                        .append("game-Type-" + ((i%2 ==0 )? "Slots" : "Cards") ).append(',')
                        .append((i%2 ==0 )? "HighestWins" : "LowestWins" ).append(',')
                        .append(String.valueOf(adjustmentFactor)).append('\n')
                        .flush();
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
