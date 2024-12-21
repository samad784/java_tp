import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.input.PickResult;

public class Interface extends Application {
    private PerspectiveCamera camera;
    private World world; // Objet de la classe World pour gérer les aéroports
    private double lastMouseY; // Pour gérer le zoom

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Terre en 3D : Zoom, Rotation et Clic Droit");

        // Création de la Terre
        Earth earth = new Earth();

        // Création de la caméra
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000); // Position initiale
        camera.setNearClip(0.1); // Distance minimale visible
        camera.setFarClip(2000.0); // Distance maximale visible
        camera.setFieldOfView(35); // Champ de vision

        // Création de la scène
        Scene scene = new Scene(earth, 800, 600, true);
        scene.setCamera(camera);

        // Chargement des aéroports dans le monde
        world = new World("D:/java/TP-java/src/data/airport-codes_no_comma.csv");

        // Gestion des événements pour le zoom et le clic droit
        scene.addEventHandler(MouseEvent.ANY, event -> {
            // Gestion du zoom avec le bouton gauche de la souris
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED && event.getButton() == MouseButton.PRIMARY) {
                lastMouseY = event.getSceneY(); // Enregistrer la position Y initiale
            }

            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && event.getButton() == MouseButton.PRIMARY) {
                // Calculer le déplacement vertical pour effectuer un zoom
                double deltaY = event.getSceneY() - lastMouseY;
                double newZ = camera.getTranslateZ() + deltaY;
                if (newZ >= -2000 && newZ <= 0) { // Limiter le zoom
                    camera.setTranslateZ(newZ);
                }
                lastMouseY = event.getSceneY(); // Mettre à jour la position Y
            }

            // Gestion du clic droit pour récupérer les coordonnées géographiques
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();

                if (pickResult.getIntersectedNode() != null && pickResult.getIntersectedNode() instanceof Sphere) {
                    System.out.println("PickResult récupéré avec succès !");
                    System.out.println("Nœud intersecté : " + pickResult.getIntersectedNode());

                    // Récupération des coordonnées texturées (x, y)
                    double x = pickResult.getIntersectedTexCoord().getX();
                    double y = pickResult.getIntersectedTexCoord().getY();

                    // Afficher les coordonnées texturées
                    System.out.println("Coordonnées texturées (x, y) : (" + x + ", " + y + ")");

                    // Conversion en latitude et longitude
                    double latitude = 180 * (0.5 - y);  // θ = 180 * (0.5 - Y)
                    double longitude = 360 * (x - 0.5); // φ = 360 * (X - 0.5)

                    // Affichage des coordonnées géographiques
                    System.out.println("Latitude calculée : " + latitude + "°");
                    System.out.println("Longitude calculée : " + longitude + "°");

                    // Recherche de l'aéroport le plus proche
                    Aeroport nearest = world.findNearestAirport(latitude, longitude);
                    if (nearest != null) {
                        System.out.println("Aéroport le plus proche : " + nearest);
                    } else {
                        System.out.println("Aucun aéroport trouvé à proximité.");
                    }
                } else {
                    System.out.println("Aucun nœud intersecté.");
                }
            }
        });

        // Configuration de la fenêtre
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
