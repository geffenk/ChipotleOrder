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

public class ETACalculator {
    private static Dotenv dotenv = Dotenv.load();

    // Replace with your Google Maps API key
    private static final String API_KEY = dotenv.get("GOOGLE_API_KEY");

    // Method to get coordinates (latitude and longitude) from an address
    public static JsonObject getCoordinatesFromAddress(String address) throws IOException {
        
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress + "&key=" + API_KEY;

        // Make an HTTP GET request
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                JsonArray results = jsonResponse.getAsJsonArray("results");
                if (results.size() > 0) {
                    JsonObject location = results.get(0).getAsJsonObject()
                            .getAsJsonObject("geometry")
                            .getAsJsonObject("location");

                    double lat = location.get("lat").getAsDouble();
                    double lng = location.get("lng").getAsDouble();

                    JsonObject coordinates = new JsonObject();
                    coordinates.addProperty("lat", lat);
                    coordinates.addProperty("lng", lng);
                    return coordinates;
                }
            }
        }
        return null;
    }

    // Method to get ETA from user's location to a predetermined destination
    public static String getETA(double originLat, double originLng, double destLat, double destLng) throws IOException {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%f,%f&destinations=%f,%f&key=%s",
                originLat, originLng, destLat, destLng, API_KEY
        );

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
                        return element.getAsJsonObject("duration").get("text").getAsString(); // e.g., "15 mins"
                    }
                }
            }
        }
        return "ETA not available";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their address
        System.out.print("Enter your address: ");
        String userAddress = scanner.nextLine();

        try {
            // Step 1: Convert user address to coordinates
            JsonObject userCoordinates = getCoordinatesFromAddress(userAddress);
            JsonObject restaurantCoordinates = getCoordinatesFromAddress("6654 Koll Center Pkwy, Pleasanton, CA 94566");
            if (userCoordinates != null) {
                double userLat = userCoordinates.get("lat").getAsDouble();
                double userLng = userCoordinates.get("lng").getAsDouble();

                // Step 2: Define the restaurant's coordinates (predetermined location)
                double restaurantLat = restaurantCoordinates.get("lat").getAsDouble();  // Example: Googleplex, Mountain View
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
