package com.clabs.examples;

import com.clabs.models.Response;
import com.clabs.models.Score;
import com.clabs.stories.Scores;
import com.clabs.utils.Connection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

abstract public class ScoresExamples extends Common{

    public static void example(Connection connection) {
        try {
            // 1. Add a new score
            Score sampleScore = new Score()
                    .setScoreRefId("-1")
                    .setGameRefId("-1")
                    .setMemberRefId("-1")
                    .setSourceValue(2.5f)
                    .setTransactionTimestamp(new Date(LocalDate.now().toEpochDay()));

            Response<Boolean> insertedScoreResponse = Scores.InsertScore(connection, sampleScore);
            printErrorsFromResponse(insertedScoreResponse);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the scores I have uploaded.
            Response<ArrayList<Score>> scores = Scores.GetListOfAllMyScores(connection);
            printErrorsFromResponse(scores);

            // 4. Get a list of scores based on my external reference id.
            Response<ArrayList<Score>> scoresByMyId = Scores.GetScoresByExternalRefId(connection, "-1");
            printErrorsFromResponse(scoresByMyId);

            // 5. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 6. Delete the records we just inserted
            //List<ConnectionResultWrapper> deletedResponse = Scores.PermanentlyDeleteListOfScores(connection, scoresByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
