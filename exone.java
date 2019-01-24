import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class exone {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the OMDB Java App!");
        System.out.println("Enter a movie title to get its IMDB information");
        Scanner userInput = new Scanner(System.in);
        String title = userInput.nextLine();

        connection(title);

    }

    // Send request
    static void connection(String movieTitle) throws Exception {
        System.out.println("Searching for: " + movieTitle);

        // API key
        String key = "trilogy";

        // API URL
        String url = "https://www.omdbapi.com/?t=" + movieTitle + "&plot=short&apikey=" + key;
        URL obj = new URL(url);

        // Create instance of connection to URL
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // Set HTTP method to GET
        connection.setRequestMethod("GET");

        // Set HTTP response code
        int respCode = connection.getResponseCode();
        System.out.println("Response Code is: " + respCode);

        // Read the response
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        // Store the response into response StringBuffer
        while ((inputLine = input.readLine()) != null) {
            response.append(inputLine);
        }
        input.close();

        // Response to String
        String stringifyResp = response.toString();

        // Simple REGEX formatting
        String formatResp = stringifyResp.replaceAll("\\{", " ");

        // print results
        System.out.println(formatResp);

    }
}