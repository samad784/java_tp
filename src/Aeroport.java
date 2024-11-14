public class Aeroport {
    // Attributs
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

    // Getter pour le code IATA
    public String getIATA() {
        return IATA;
    }

    // Getter pour la latitude
    public double getLatitude() {
        return latitude;
    }

    // Getter pour la longitude
    public double getLongitude() {
        return longitude;
    }

    public double calculDistance(Aeroport autre) {
        // Récupération des latitudes et longitudes des deux aéroports
        double theta1 = this.latitude;
        double theta2 = autre.getLatitude();
        double phi1 = this.longitude;
        double phi2 = autre.getLongitude();

        // Calcul de la norme selon la formule fournie
        double distance = Math.pow((theta2 - theta1), 2) +
                Math.pow((phi2 - phi1) * Math.cos(Math.toRadians((theta2 + theta1) / 2)), 2);

        return distance;
    }

    // Surcharge de la méthode toString
    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + IATA + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
