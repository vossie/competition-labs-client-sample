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
            
            // 1. Add a new member
            List<String> group = new ArrayList<String>();
            group.add("VIP-Test");

//            Map<String,List<String>> memberMetaData = new HashMap<String, List<String>>();
//            List<String> memberMetaDataVal = new ArrayList<String>();
//
//            memberMetaDataVal.add("someValue");
//            memberMetaData.put("someKey",memberMetaDataVal);


            Member sampleMember = new Member()
                    .setMemberType("Individual")
                    .setMemberRefId("-1")
                    .setGroups(group)
                    .setName("Test user");

            Response<Boolean> insertedMemberResponse = Members.InsertMember(connection, sampleMember);
            printErrorsFromResponse(insertedMemberResponse);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the members I have uploaded.
            Response<ArrayList<Member>> members = Members.GetListOfAllMyMembers(connection);
            printErrorsFromResponse(members);

            // 4. Get a list of members based on my external reference id.
            Response<ArrayList<Member>> membersByMyId = Members.GetMembersByExternalRefId(connection,"-1");
            printErrorsFromResponse(membersByMyId);

            // 5. Update existing records
            for (int i=0; i<membersByMyId.data.size(); i++){
                Response r = Members.UpdateMemberById(connection, membersByMyId.data.get(i).setName("Name" + i));
                printErrorsFromResponse(r);
            }

            // 6. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 7. Delete the records we just inserted
            //List<ConnectionResultWrapper> deletedResponse = Members.PermanentlyDeleteListOfMembers(connection, membersByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
