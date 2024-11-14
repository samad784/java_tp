public class Aeroport {
    // Attributs de la classe
    private String IATA;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    // Constructeur
    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getIATA() {
        return IATA;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // Surcharge de la méthode toString pour afficher les informations de l'aéroport
    @Override
    public String toString() {
        return "Aeroport {" +
                "IATA='" + IATA + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
