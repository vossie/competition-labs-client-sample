package com.clabs.examples;

import com.clabs.models.Response;
import com.clabs.models.Score;
import com.clabs.stories.Scores;
import com.clabs.utils.Connection;
import com.clabs.utils.DateTime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

abstract public class ScoresExamples extends Common{

    public static void example(Connection connection) {
        try {
            // 1. Add a new score
            Score sampleScore = new Score()
                    .setScoreRefId("-1")
                    .setGameRefId("-1")
                    .setMemberRefId("-1")
                    .setSourceValue(2.5f)
                    .setTransactionTimestamp(DateTime.now());

            Response<Boolean> insertedScoreResponse = Scores.InsertScore(connection, sampleScore);
            printErrorsFromResponse(insertedScoreResponse);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the scores I have uploaded.
            Response<ArrayList<Score>> scores = Scores.GetListOfAllMyScores(connection);
            printErrorsFromResponse(scores);

            // 4. Get a list of scores based on my external score reference id.
            Response<ArrayList<Score>> scoresByScoreRefId = Scores.GetScoresByScoreRefId(connection, "-1");
            printErrorsFromResponse(scoresByScoreRefId);

            // 5. Get a list of scores based on my external member reference id.
            Response<ArrayList<Score>> scoresBymemberRefId = Scores.GetScoresByMemberRefId(connection, "-1");
            printErrorsFromResponse(scoresBymemberRefId);

            // 6. Get a list of scores based on my external game reference id.
            Response<ArrayList<Score>> scoresByGameRefId = Scores.GetScoresByGameRefId(connection, "-1");
            printErrorsFromResponse(scoresByGameRefId);

            // 7. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 8. Delete the records we just inserted
            //List<ConnectionResultWrapper> deletedResponse = Scores.PermanentlyDeleteListOfScores(connection, scoresByScoreRefId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushAll(Connection connection) {
        try {
            Response<ArrayList<Score>> scores = Scores.GetListOfAllMyScores(connection);
            Scores.PermanentlyDeleteListOfScores(connection, scores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateExampleCSV() {

        String ColumnHeader1 = "memberRefId";
        String ColumnHeader2 = "gameRefId";
        String ColumnHeader3 = "scoreRefId";
        String ColumnHeader4 = "sourceValue";
        String ColumnHeader5 = "transactionTimestamp";
        int countOfRowsToGenerate = 10000;

        try {
            FileWriter fileWriter = new FileWriter("export-scores-sample.csv");

            fileWriter
                    .append(ColumnHeader1).append(',')
                    .append(ColumnHeader2).append(',')
                    .append(ColumnHeader3).append(',')
                    .append(ColumnHeader4).append(',')
                    .append(ColumnHeader5).append('\n');

            for (int i = 0; i < countOfRowsToGenerate; i ++) {

                fileWriter
                        .append("my-custom-member-ref-" + i).append(',')
                        .append("my-custom-game-ref-" + i).append(',')
                        .append("my-custom-score-ref-" + i).append(',')
                        .append(String.valueOf(new Random().nextInt(1000)/100.5)).append(',') // generate random source values for testing
                        .append(DateTime.dateMinusMillisAsString(10000)).append('\n')
                        .flush();
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
