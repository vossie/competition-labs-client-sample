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
    public static final Integer PORT        = 443;

    public static final Connection HTTP_CONNECTION = new Connection(ROOT_URI, SPACE_NAME, API_KEY, PORT);

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print(
                    "Enter a number:\n" +
                    "1 - Run the API examples\n" +
                    "2 - To generate sample CSV files\n" +
                    "3 - Flush all data from members, scores and, games\n" +
                    "> "
            );

            int i = Integer.parseInt(br.readLine());

            if(i==1) {
                System.out.print("[START] Running the API examples.\n");

                GamesExamples.example(HTTP_CONNECTION);

                MembersExamples.example(HTTP_CONNECTION);

                ScoresExamples.example(HTTP_CONNECTION);

                System.out.print("[COMPLETE] Running the API examples.\n");

            }
            else if (i==2){
                System.out.print("[START] Example CSV generation.\n");

                MembersExamples.generateExampleCSV();

                GamesExamples.generateExampleCSV();

                ScoresExamples.generateExampleCSV();

                System.out.print("[COMPLETE] Example CSV generation.\n");
            }
            else if (i==3){
                System.out.print("[START] Flush all data.\n");

                MembersExamples.flushAll(HTTP_CONNECTION);

                GamesExamples.flushAll(HTTP_CONNECTION);

                ScoresExamples.flushAll(HTTP_CONNECTION);

                System.out.print("[COMPLETE] Flush all data.\n");
            }
            else {
                System.out.print("[ERROR] Unknown selection, exiting.\n");
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch(NumberFormatException nfe){
            System.err.println("Invalid Format!\n");
        }
    }
}
