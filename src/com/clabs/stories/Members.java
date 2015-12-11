package com.clabs.stories;

import com.clabs.com.clabs.utils.Connection;
import com.clabs.com.clabs.utils.ConnectionResultWrapper;
import com.clabs.com.clabs.utils.Json;
import com.clabs.models.Member;
import com.clabs.models.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Members {

    public final static String RESOURCE_PATH = "members";
    private final static Type responseTypeMember = new TypeToken<Response<Member>>(){}.getType();
    private final static Type responseTypeListMembers = new TypeToken<Response<ArrayList<Member>>>(){}.getType();
    private final static Type responseTypeBoolean = new TypeToken<Response<Boolean>>(){}.getType();

    /**
     * This shows how you retrieve all the members you have
     * We need to get a count of all the records without returning results.
     * The default result set is defaulted to skip=0, limit=20. We will override this so we can get all
     * the records in one go. The MAX to get in one request is 10,000
     * @param connection The http connection handler
     * @return Response object with a list of Member objects
     * @throws Exception
     */
    public static Response<ArrayList<Member>> GetListOfAllMyMembers(Connection connection) throws Exception {

        Response<ArrayList<Member>> count = GetACountOfAllMembers(connection);
        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, count.getTotalRecordsFound());

        return new Gson().fromJson(out.getBody(), responseTypeListMembers);
    }

    /**
     * This gets a count of all the members you have uploaded and does not return any member objects as part of the response.
     * @param connection The http connection handler
     * @return Response object with an empty list of Member objects and metadata with total record count
     * @throws Exception
     */
    public static Response<ArrayList<Member>> GetACountOfAllMembers(Connection connection) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, 0, 0);

        return new Gson().fromJson(out.getBody(), responseTypeListMembers);
    }

    /**
     * This shows how a filter can be used to get records matching the filter criteria.
     * @param connection The http connection handler
     * @param externalMemberRefId The parameter to filter the resultset by.
     * @return Response object with a list of Member objects matching the filter criteria
     * @throws Exception
     */
    public static Response<ArrayList<Member>> GetMembersByExternalRefId(Connection connection, String externalMemberRefId) throws Exception {

        ConnectionResultWrapper out = connection.sendGet(RESOURCE_PATH, "externalRefId="+externalMemberRefId);

        return new Gson().fromJson(out.getBody(), responseTypeListMembers);
    }

    /**
     * Shows how a single member object can be added to your space.
     * @param connection The http connection handler
     * @param member The member to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertMember(Connection connection, Member member) throws Exception {

        ArrayList<Member> newMember = new ArrayList<Member>();
        newMember.add(member);

        return InsertListOfMembers(connection, newMember);
    }

    /**
     * Shows how a list of new member records can be added to your space.
     * @param connection The http connection handler
     * @param members The members to add to your space
     * @return List of error in the response or a boolean indicating success.
     * @throws Exception
     */
    public static Response<Boolean> InsertListOfMembers(Connection connection, List<Member> members) throws Exception {

        String body = Json.GSON.toJson(members);
        ConnectionResultWrapper out = connection.sendPost(RESOURCE_PATH, body);

        return new Gson().fromJson(out.getBody(), responseTypeBoolean);
    }

    /**
     * Shows how a member records can be modified.
     * @param connection connection The http connection handler
     * @param member
     * @return
     * @throws Exception
     */
    public static Response<Member> UpdateMemberById(Connection connection, Member member) throws Exception {

        String json = Json.GSON.toJson(member);
        String resourcePath = RESOURCE_PATH + "/" + member.id;

        ConnectionResultWrapper out = connection.sendPut(resourcePath, json);

        return Json.GSON.fromJson(out.getBody(), responseTypeMember);
    }

    /**
     * Shows how a member records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param members
     * @return
     * @throws Exception
     */
    public static List<ConnectionResultWrapper> PermanentlyDeleteListOfMembers(Connection connection, Response<ArrayList<Member>> members) throws Exception {

        List responses = new ArrayList<ConnectionResultWrapper>();

        for(Member member: members.data) {
            responses.add( PermanentlyDeleteAMemberById(connection, member.id) );
        }

        return responses;
    }

    /**
     * Shows how a member records can be permanently deleted.
     * @param connection connection The http connection handler
     * @param id
     * @return
     * @throws Exception
     */
    public static ConnectionResultWrapper PermanentlyDeleteAMemberById(Connection connection, int id) throws Exception {
        String resourcePath = RESOURCE_PATH + "/" + id;

        return connection.sendDelete(resourcePath, "");
    }
}
