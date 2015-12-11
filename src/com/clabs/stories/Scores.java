package com.clabs.stories;

import com.clabs.com.clabs.utils.Connection;
import com.clabs.com.clabs.utils.ConnectionResultWrapper;
import com.clabs.com.clabs.utils.Json;
import com.clabs.models.Response;
import com.clabs.models.Score;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Scores {

    public final static String RESOURCE_PATH = "scores";
    private final static Type responseTypeScore = new TypeToken<Response<Score>>() {}.getType();
    private final static Type responseTypeListScores = new TypeToken<Response<ArrayList<Score>>>() {}.getType();
    private final static Type responseTypeBoolean = new TypeToken<Response<Boolean>>() {}.getType();

    /**
     * This shows how you retrieve all the scores you have
     * We need to get a count of all the records without returning results.
     * The default result set is defaulted to skip=0, limit=20. We will override this so we can get all
     * the records in one go. The MAX to get in one request is 10,000
     *
     * @param connection The http connection handler
     * @return Response object with a list of Score objects
     * @throws Exception
     */
    public static Response<ArrayList<Score>> GetListOfAllMyScores(Connection connection) throws Exception {

        Response<ArrayList<Score>> count = GetACountOfAllScores(connection);
        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, count.getTotalRecordsFound());

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListScores);
    }

    /**
     * This gets a count of all the scores you have uploaded and does not return any score objects as part of the response.
     *
     * @param connection The http connection handler
     * @return Response object with an empty list of Score objects and metadata with total record count
     * @throws Exception
     */
    public static Response<ArrayList<Score>> GetACountOfAllScores(Connection connection) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, 0);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListScores);
    }

    /**
     * This shows how a filter can be used to get records matching the filter criteria.
     *
     * @param connection          The http connection handler
     * @param externalScoreRefId The parameter to filter the resultset by.
     * @return Response object with a list of Score objects matching the filter criteria
     * @throws Exception
     */
    public static Response<ArrayList<Score>> GetScoresByExternalRefId(Connection connection, String externalScoreRefId) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, "externalScoreRefId=" + externalScoreRefId);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListScores);
    }

    /**
     * Shows how a single score object can be added to your space.
     *
     * @param connection The http connection handler
     * @param score     The score to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertScore(Connection connection, Score score) throws Exception {

        ArrayList<Score> newScore = new ArrayList<Score>();
        newScore.add(score);

        return InsertListOfScores(connection, newScore);
    }

    /**
     * Shows how a list of new score records can be added to your space.
     *
     * @param connection The http connection handler
     * @param scores    The scores to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertListOfScores(Connection connection, List<Score> scores) throws Exception {

        String body = Json.GSON.toJson(scores);
        ConnectionResultWrapper out = connection.sendPost(RESOURCE_PATH, body);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeBoolean);
    }

    /**
     * Shows how a score records can be modified.
     *
     * @param connection connection The http connection handler
     * @param score
     * @return
     * @throws Exception
     */
    public static Response<Score> UpdateScoreById(Connection connection, Score score) throws Exception {

        String json = Json.GSON.toJson(score);
        String resourcePath = RESOURCE_PATH + "/" + score.id;

        ConnectionResultWrapper out = connection.sendPut(resourcePath, json);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeScore);
    }

    /**
     * Shows how a score records can be permanently deleted.
     *
     * @param connection connection The http connection handler
     * @param scores
     * @return
     * @throws Exception
     */
    public static List<ConnectionResultWrapper> PermanentlyDeleteListOfScores(Connection connection, Response<ArrayList<Score>> scores) throws Exception {

        List responses = new ArrayList<ConnectionResultWrapper>();

        for (Score score : scores.data) {
            responses.add(PermanentlyDeleteAScoreById(connection, score.id));
        }

        return responses;
    }

    /**
     * Shows how a score records can be permanently deleted.
     *
     * @param connection connection The http connection handler
     * @param id
     * @return
     * @throws Exception
     */
    public static ConnectionResultWrapper PermanentlyDeleteAScoreById(Connection connection, int id) throws Exception {
        String resourcePath = RESOURCE_PATH + "/" + id;

        return connection.sendDelete(resourcePath, "");
    }
}
