package com.clabs.stories;

import com.clabs.utils.Connection;
import com.clabs.utils.ConnectionResultWrapper;
import com.clabs.utils.Json;
import com.clabs.models.Game;
import com.clabs.models.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Games {

    public  final static String RESOURCE_PATH = "games";
    private final static Type responseTypeGame = new TypeToken<Response<Game>>(){}.getType();
    private final static Type responseTypeListGames = new TypeToken<Response<ArrayList<Game>>>(){}.getType();
    private final static Type responseTypeBoolean = new TypeToken<Response<Boolean>>(){}.getType();

    /**
     * This shows how you retrieve all the games you have
     * We need to get a count of all the records without returning results.
     * The default result set is defaulted to skip=0, limit=20. We will override this so we can get all
     * the records in one go. The MAX to get in one request is 10,000
     * @param connection The http connection handler
     * @return Response object with a list of Game objects
     * @throws Exception
     */
    public static Response<ArrayList<Game>> GetListOfAllMyGames(Connection connection) throws Exception {

        Response<ArrayList<Game>> count = GetACountOfAllGames(connection);
        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, count.getTotalRecordsFound());

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListGames);
    }

    /**
     * This gets a count of all the games you have uploaded and does not return any game objects as part of the response.
     * @param connection The http connection handler
     * @return Response object with an empty list of Game objects and metadata with total record count
     * @throws Exception
     */
    public static Response<ArrayList<Game>> GetACountOfAllGames(Connection connection) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, 0);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListGames);
    }

    /**
     * This shows how a filter can be used to get records matching the filter criteria.
     * @param connection The http connection handler
     * @param externalGameRefId The parameter to filter the resultset by.
     * @return Response object with a list of Game objects matching the filter criteria
     * @throws Exception
     */
    public static Response<ArrayList<Game>> GetGamesByExternalRefId(Connection connection, String externalGameRefId) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, "gameRefId="+externalGameRefId);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeListGames);
    }

    /**
     * Shows how a single game object can be added to your space.
     * @param connection The http connection handler
     * @param game The game to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertGame(Connection connection, Game game) throws Exception {

        ArrayList<Game> newGame = new ArrayList<Game>();
        newGame.add(game);

        return InsertListOfGames(connection, newGame);
    }

    /**
     * Shows how a list of new game records can be added to your space.
     * @param connection The http connection handler
     * @param games The games to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertListOfGames(Connection connection, List<Game> games) throws Exception {

        String body = Json.GSON.toJson(games);
        ConnectionResultWrapper out = connection.sendPost(RESOURCE_PATH, body);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeBoolean);
    }

    /**
     * Shows how a game records can be modified.
     * @param connection connection The http connection handler
     * @param game
     * @return
     * @throws Exception
     */
    public static Response<Game> UpdateGameById(Connection connection, Game game) throws Exception {

        String json = Json.GSON.toJson(game);
        String resourcePath = RESOURCE_PATH + "/" + game.id;

        ConnectionResultWrapper out = connection.sendPut(resourcePath, json);

        return Json.toResponsefromConnectionResultWrapper(out, responseTypeGame);
    }

    /**
     * Shows how a game records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param games
     * @return
     * @throws Exception
     */
    public static List<ConnectionResultWrapper> PermanentlyDeleteListOfGames(Connection connection, Response<ArrayList<Game>> games) throws Exception {

        List responses = new ArrayList<ConnectionResultWrapper>();

        for(Game game: games.data) {
            responses.add( PermanentlyDeleteAGameById(connection, game.id) );
        }

        return responses;
    }

    /**
     * Shows how a game records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param id
     * @return
     * @throws Exception
     */
    public static ConnectionResultWrapper PermanentlyDeleteAGameById(Connection connection, int id) throws Exception {
        String resourcePath = RESOURCE_PATH + "/" + id;

        return connection.sendDelete(resourcePath, "");
    }
}
