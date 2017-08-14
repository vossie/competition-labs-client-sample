package com.clabs.examples;

import com.clabs.models.Response;
import com.clabs.models.Event;
import com.clabs.stories.Events;
import com.clabs.utils.Connection;
import com.clabs.utils.DateTime;

abstract public class EventsExamples extends Common{

    public static void example(Connection connection) {
        try {
            // 1. Add a new event
            Event sampleEvent = new Event()
                    .setMemberRefId("-1")
                    .setEntityRefId("-1") // which corresponds to your game-ref-id
                    .setEventRefId("-1")
                    .setAction("win")
                    .setSourceValue(2.5f)
                    .setTransactionTimestamp(DateTime.now());

            Response<Boolean> insertedEventResponse = Events.InsertEvent(connection, sampleEvent);
            printErrorsFromResponse(insertedEventResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
