import com.clabs.examples.GamesExamples;
import com.clabs.examples.MembersExamples;
import com.clabs.examples.ScoresExamples;
import com.clabs.utils.Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final String  SPACE_NAME  = "REPLACE-ME-WITH-YOUR-SPACE-NAME";
    public static final String  API_KEY     = "REPLACE-ME-WITH-YOUR-API-KEY";

    public static final String  ROOT_URI    = "https://app.competitionlabs.com";
    public static final Integer PORT        = 9443;

    public static final Connection HTTP_CONNECTION = new Connection(ROOT_URI, SPACE_NAME, API_KEY, PORT);

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter an number:\n1 - to run the API example\n2 - To generate sample VCS files\n> ");
            int i = Integer.parseInt(br.readLine());

            if(i==1) {
                System.out.print("[START] Running API examples.");

                GamesExamples.example(HTTP_CONNECTION);

                MembersExamples.example(HTTP_CONNECTION);

                ScoresExamples.example(HTTP_CONNECTION);

                System.out.print("[COMPLETE] Running API examples.");

            }
            else if (i==2){
                System.out.print("[START] Example CSV generation.");

                MembersExamples.generateExampleCSV();

                GamesExamples.generateExampleCSV();

                ScoresExamples.generateExampleCSV();

                System.out.print("[COMPLETE] Example CSV generation.");
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
    }
}
