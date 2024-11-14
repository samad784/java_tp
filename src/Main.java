public class Main {
    public static void main(String[] args) {
        // Création d'un objet Aeroport pour tester
        Aeroport aeroport = new Aeroport("CDG", "Charles de Gaulle", "France", 49.0097, 2.5479);

        // Affichage des informations de l'aéroport en utilisant toString
        System.out.println(aeroport.toString());
        
        // Test des getters
        System.out.println("IATA: " + aeroport.getIATA());
        System.out.println("Name: " + aeroport.getName());
        System.out.println("Country: " + aeroport.getCountry());
        System.out.println("Latitude: " + aeroport.getLatitude());
        System.out.println("Longitude: " + aeroport.getLongitude());
    }
}
