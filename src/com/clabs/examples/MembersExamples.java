package com.clabs.examples;

import com.clabs.models.Member;
import com.clabs.models.Response;
import com.clabs.stories.Members;
import com.clabs.utils.Connection;

import java.util.ArrayList;
import java.util.List;

abstract public class MembersExamples extends Common {

    public static void example(Connection connection) {
        try {

            /** 1. Add new members */
            for (int i=1; i<3; i++) {
                List<String> group = new ArrayList<String>();
                group.add("VIP-Test"+i);

                Member sampleMember = new Member()
                        .setMemberType("Individual")
                        .setMemberRefId("-1")
                        .setGroups(group)
                        .setName("Test user"+i);

                Response<Boolean> insertedMemberResponse = Members.InsertMember(connection, sampleMember);
                printErrorsFromResponse(insertedMemberResponse);
            }


            /** 2. Allow the remote system to propagate the data to the edge nodes */
            Thread.sleep(200);


            /** 3. Get all the members I have uploaded */
            Response<ArrayList<Member>> members = Members.GetListOfAllMyMembers(connection);
            printErrorsFromResponse(members);


             /** 4. Get a list of members based on my external reference id */
            Response<ArrayList<Member>> membersByMyId = Members.GetMembersByExternalRefId(connection,"-1");
            printErrorsFromResponse(membersByMyId);


            /** 5. Get a list of members based on my external reference id */
            ArrayList<String> groupsToFilterBy = new ArrayList<String>();
            groupsToFilterBy.add("VIP-Test1");
            groupsToFilterBy.add("VIP-Test2");
            Response<ArrayList<Member>> membersByGroups = Members.GetMembersByGroups(connection,groupsToFilterBy);
            printErrorsFromResponse(membersByGroups);


            /** 6. Update existing records */
            for (int i=0; i<membersByMyId.data.size(); i++){
                Response r = Members.UpdateMemberById(connection, membersByMyId.data.get(i).setName("Name" + i));
                printErrorsFromResponse(r);
            }


            /** 7. Allow the remote system to propagate the data to the edge nodes */
            Thread.sleep(200);


            /** 7. Delete the records we just inserted */
            //List<ConnectionResultWrapper> deletedResponse = Members.PermanentlyDeleteListOfMembers(connection, membersByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
