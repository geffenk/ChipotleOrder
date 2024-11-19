import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.cdimascio.dotenv.Dotenv;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

public class EtaCalculator {
    private static Dotenv dotenv = Dotenv.load();

    // Replace with your Google Maps API key
    private static final String API_KEY = dotenv.get("GOOGLE_API_KEY"); // retrieve API KEY from .env

    // Method to get coordinates (latitude and longitude) from an address
    public static JsonObject getCoordinatesFromAddress(String address) throws IOException { // has potential to throw
                                                                                            // IOException

        String encodedAddress = URLEncoder.encode(address, "UTF-8"); // takes user input address and encodes it in UTF-8
                                                                     // (like a ASCII table)
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY; // creates
                                                                                                                        // a
                                                                                                                        // url
                                                                                                                        // with
                                                                                                                        // string
                                                                                                                        // concat

        // Make an HTTP GET request
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { // new HttpClient instance named httpClient
            HttpGet request = new HttpGet(url); // a new HttpGet instance named request
            try (CloseableHttpResponse response = httpClient.execute(request)) { // execute the Http GET command and set
                                                                                 // response
                String responseBody = EntityUtils.toString(response.getEntity()); // get entity from Http response and
                                                                                  // converts it to a string object
                                                                                  // named responseBody
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject(); // parse responseBody
                                                                                                  // and converts it to
                                                                                                  // a Json Objected
                                                                                                  // named to
                                                                                                  // jsonResponse

                JsonArray results = jsonResponse.getAsJsonArray("results"); // jsonResponse has property "results".
                                                                            // converts results into JsonArray named
                                                                            // results
                if (results.size() > 0) { // checks if results is avaliable
                    JsonObject location = results.get(0).getAsJsonObject()
                            .getAsJsonObject("geometry")
                            .getAsJsonObject("location"); // get index 0 of results and extracts location from it.

                    double lat = location.get("lat").getAsDouble(); // get lat from location as double
                    double lng = location.get("lng").getAsDouble(); // get lng from location as double

                    JsonObject coordinates = new JsonObject();
                    coordinates.addProperty("lat", lat);
                    coordinates.addProperty("lng", lng); // creates Json object named coordinates with properties lat
                                                         // and lng
                    return coordinates;
                }
            }
        }
        return null; // if unable to locate this address, return null
    } // method has 3 possible outputs: coordinates, null, or IOException

    // Method to get ETA from user's location to a predetermined destination
    public static String getETA(double originLat, double originLng, double destLat, double destLng) throws IOException {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%f,%f&destinations=%f,%f&key=%s",
                originLat, originLng, destLat, destLng, API_KEY); // %f is placeholder for number variables
                                                                  // %s is for string variables

        // Make an HTTP GET request
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                JsonArray rows = jsonResponse.getAsJsonArray("rows");
                if (rows.size() > 0) {
                    JsonArray elements = rows.get(0).getAsJsonObject().getAsJsonArray("elements");
                    if (elements.size() > 0) {
                        JsonObject element = elements.get(0).getAsJsonObject();
                        return element.getAsJsonObject("duration").get("text").getAsString(); // extracts duration from
                                                                                              // response
                    }
                }
            }
        }
        return "ETA not available";
    }

    public static int restaurantETA() {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their address
        System.out.print("Enter your address: ");
        String userAddress = scanner.nextLine();
        scanner.close();

        try { // starts a try block
              // Step 1: Convert user address to coordinates
            JsonObject userCoordinates = getCoordinatesFromAddress(userAddress); // creates a new Json object called
                                                                                 // userCoordinates
            JsonObject restaurantCoordinates = getCoordinatesFromAddress("6654 Koll Center Pkwy, Pleasanton, CA 94566");
            if (userCoordinates != null) {
                double userLat = userCoordinates.get("lat").getAsDouble(); // retrieve lat and lng back from
                                                                           // userCoordinates
                double userLng = userCoordinates.get("lng").getAsDouble();

                // Step 2: Define the restaurant's coordinates (predetermined location)
                double restaurantLat = restaurantCoordinates.get("lat").getAsDouble(); // retrieve lat and lng back from
                                                                                       // restaurantCoordinates
                double restaurantLng = restaurantCoordinates.get("lng").getAsDouble();

                // Step 3: Calculate ETA between user and restaurant
                String eta = getETA(userLat, userLng, restaurantLat, restaurantLng);
                System.out.println("Estimated Time of Arrival: " + eta);
                System.out.println("Your order has been placed");
                return 15;
            } else {
                System.out.println("Unable to find coordinates for the provided address.");
                return 12;
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return 12;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their address
        System.out.print("Enter your address: ");
        String userAddress = scanner.nextLine();
        scanner.close();
        try { // starts a try block
              // Step 1: Convert user address to coordinates
            JsonObject userCoordinates = getCoordinatesFromAddress(userAddress); // creates a new Json object called
                                                                                 // userCoordinates
            JsonObject restaurantCoordinates = getCoordinatesFromAddress("6654 Koll Center Pkwy, Pleasanton, CA 94566");
            if (userCoordinates != null) {
                double userLat = userCoordinates.get("lat").getAsDouble(); // retrieve lat and lng back from
                                                                           // userCoordinates
                double userLng = userCoordinates.get("lng").getAsDouble();

                // Step 2: Define the restaurant's coordinates (predetermined location)
                double restaurantLat = restaurantCoordinates.get("lat").getAsDouble(); // retrieve lat and lng back from
                                                                                       // restaurantCoordinates
                double restaurantLng = restaurantCoordinates.get("lng").getAsDouble();

                // Step 3: Calculate ETA between user and restaurant
                String eta = getETA(userLat, userLng, restaurantLat, restaurantLng);
                System.out.println("Estimated Time of Arrival: " + eta);
            } else {
                System.out.println("Unable to find coordinates for the provided address.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}