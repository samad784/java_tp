public class Main {
    public static void main(String[] args) {
        World w = new World("src\\data\\airport-codes_no_comma.csv");
        System.out.println("Found " + w.getList().size() + " airports.");

        Aeroport paris = w.findNearestAirport(48.866, 2.316);
        Aeroport cdg = w.findByCode("CDG");

        double distance = w.distance(2.316, 48.866, paris.getLongitude(), paris.getLatitude());
        System.out.println(paris);
        System.out.println("Distance to nearest airport: " + distance);

        double distanceCDG = w.distance(2.316, 48.866, cdg.getLongitude(), cdg.getLatitude());
        System.out.println(cdg);
        System.out.println("Distance to CDG: " + distanceCDG);
    }
}