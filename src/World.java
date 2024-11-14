import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Aeroport> aeroportList;

    public World(String filePath) {
        aeroportList = new ArrayList<>();
        loadAirports(filePath);
    }

    public List<Aeroport> getList() {
        return aeroportList;
    }

    private void loadAirports(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Ignorer l'en-tête
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (fields.length >= 12) {
                    String iataCode = fields[9]; // iata_code
                    String name = fields[2]; // name
                    String country = fields[5]; // iso_country
                    String coordinatesField = fields[11].replace("\"", ""); // Retirer les guillemets

                    String[] coordinates = coordinatesField.split(",");

                    if (iataCode != null && !iataCode.isEmpty() && coordinates.length == 2) {
                        try {
                            double longitude = Double.parseDouble(coordinates[0].trim());
                            double latitude = Double.parseDouble(coordinates[1].trim());
                            Aeroport aeroport = new Aeroport(iataCode, name, country, latitude, longitude);
                            aeroportList.add(aeroport);
                        } catch (NumberFormatException e) {
                            System.err.println("Coordonnées incorrectes pour la ligne : " + line);
                        }
                    }
                } else {
                    System.err.println("Ligne ignorée, nombre de champs insuffisants : " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Aeroport findNearestAirport(double latitude, double longitude) {
        Aeroport refAeroport = new Aeroport("REF", "Reference", "Unknown", latitude, longitude);
        Aeroport nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Aeroport aeroport : aeroportList) {
            double distance = distance(longitude, latitude, aeroport.getLongitude(), aeroport.getLatitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = aeroport;
            }
        }
        return nearest;
    }

    public Aeroport findByCode(String code) {
        for (Aeroport aeroport : aeroportList) {
            if (aeroport.getIATA().equalsIgnoreCase(code)) {
                return aeroport;
            }
        }
        return null; // Retourne null si aucun aéroport avec ce code n'est trouvé
    }

    public static double distance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double deltaTheta = latitude2 - latitude1;
        double deltaPhi = longitude2 - longitude1;
        double cosAverageTheta = Math.cos(Math.toRadians((latitude2 + latitude1) / 2));

        return Math.pow(deltaTheta, 2) + Math.pow(deltaPhi * cosAverageTheta, 2);
    }
}
