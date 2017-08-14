package com.clabs.stories;

import com.clabs.models.Event;
import com.clabs.models.Response;
import com.clabs.utils.Connection;
import com.clabs.utils.ConnectionResultWrapper;
import com.clabs.utils.Json;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Events {

    public final static String RESOURCE_PATH = "events";
    private final static Type responseTypeBoolean = new TypeToken<Response<Boolean>>() {}.getType();

    /**
     * Shows how a single event object can be added to your space.
     *
     * @param connection The http connection handler
     * @param event     The event to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertEvent(Connection connection, Event event) throws Exception {

        ArrayList<Event> newEvent = new ArrayList<Event>();
        newEvent.add(event);

        return InsertListOfEvents(connection, newEvent);
    }

    /**
     * Shows how a list of new event records can be added to your space.
     *
     * @param connection The http connection handler
     * @param events    The events to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertListOfEvents(Connection connection, List<Event> events) throws Exception {

        String body = Json.GSON.toJson(events);
        ConnectionResultWrapper out = connection.sendPost(RESOURCE_PATH, body);

        return Json.toResponseFromConnectionResultWrapper(out, responseTypeBoolean);
    }
}
