package com.clabs.examples;

import com.clabs.models.Member;
import com.clabs.models.Response;
import com.clabs.stories.Members;
import com.clabs.utils.Connection;

import java.io.FileWriter;
import java.io.IOException;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushAll(Connection connection) {
        try {
            Response<ArrayList<Member>> members = Members.GetListOfAllMyMembers(connection);
            Members.PermanentlyDeleteListOfMembers(connection, members);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateExampleCSV() {

        String ColumnHeader1 = "memberRefId";
        String ColumnHeader2 = "name";
        String ColumnHeader3 = "group";
        int countOfRowsToGenerate = 10000;

        try {
                FileWriter fileWriter = new FileWriter("export-members-sample.csv");

                fileWriter
                        .append(ColumnHeader1).append(',')
                        .append(ColumnHeader2).append(',')
                        .append(ColumnHeader3).append('\n');

                for (int i = 0; i < countOfRowsToGenerate; i ++) {
                    fileWriter
                            .append("my-custom-member-ref-" + i).append(',')
                            .append("Member-Display-Name-" + i).append(',')
                            .append("my-customer-segment-" + ((i<5)? "VIP" : "BigSpender") ).append('\n')
                            .flush();
                }

                fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
