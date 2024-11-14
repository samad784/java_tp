public class Main {
    public static void main(String[] args) {
        // Initialiser la classe World avec le chemin du fichier CSV
        World world = new World("src\\data\\airport-codes_no_comma.csv");

        // Afficher tous les aéroports chargés
        for (Aeroport aeroport : world.getList()) {
            System.out.println(aeroport.toString());
        }
    }
}
