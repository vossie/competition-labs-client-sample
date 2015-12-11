import com.clabs.utils.Connection;
import com.clabs.examples.GamesExamples;
import com.clabs.examples.MembersExamples;
import com.clabs.examples.ScoresExamples;

public class Main {

    public static final String  SPACE_NAME  = "REPLACE-ME-WITH-YOUR-SPACE-NAME";
    public static final String  API_KEY     = "REPLACE-ME-WITH-YOUR-API-KEY";

    public static final String  ROOT_URI    = "https://app.competitionlabs.com";
    public static final Integer PORT        = 9443;

    public static final Connection HTTP_CONNECTION = new Connection(ROOT_URI, SPACE_NAME, API_KEY, PORT);

    public static void main(String[] args) {

        GamesExamples.example(HTTP_CONNECTION);

        MembersExamples.example(HTTP_CONNECTION);

        ScoresExamples.example(HTTP_CONNECTION);
    }
}
