package com.clabs.examples;

import com.clabs.com.clabs.utils.Connection;
import com.clabs.com.clabs.utils.ConnectionResultWrapper;
import com.clabs.models.Member;
import com.clabs.models.Response;
import com.clabs.stories.Members;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class MembersExamples {

    public static void example(Connection connection) {
        try {
            
            // 1. Add a new member
            List<String> group = new ArrayList<String>();
            group.add("VIP-Test");
            Map<String,String> memberMetaData = new HashMap<String, String>();
            memberMetaData.put("someKey","someValue");

            Member sampleMember = new Member()
                    .setMemberType("Individual")
                    .setExternalRefId(-1)
                    .setGroups(group)
                    .setName("Test user")
                    .setMetadata(memberMetaData);

            Response<Boolean> insertedMemberResponse = Members.InsertMember(connection, sampleMember);

            // 2. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 3. Get all the members I have uploaded.
            Response<ArrayList<Member>> members = Members.GetListOfAllMyMembers(connection);

            // 4. Get a list of members based on my external reference id.
            Response<ArrayList<Member>> membersByMyId = Members.GetMembersByExternalRefId(connection,"-1");

            // 5. Update existing records
            for (int i=0; i<membersByMyId.data.size(); i++){
                Members.UpdateMemberById(connection, membersByMyId.data.get(i).setName("Name" + i));
            }

            // 6. Allow the remote system to propagate the data to the edge nodes
            Thread.sleep(200);

            // 7. Delete the records we just inserted
            List<ConnectionResultWrapper> deletedResponse = Members.PermanentlyDeleteListOfMembers(connection, membersByMyId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
