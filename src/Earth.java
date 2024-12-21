import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.io.File;

public class Earth extends Group {
    private Sphere sph;
    private Rotate ry; // Rotation autour de l'axe Y

    public Earth() {
        // Création de la sphère représentant la Terre
        sph = new Sphere(300); // Rayon de 300 pixels

        // Charger l'image de texture
        String imagePath = "D:\\git_project\\java_tp\\src\\data\\earth_lights_4800.png";
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            // Charger l'image comme texture
            Image earthTexture = new Image(imageFile.toURI().toString());

            // Créer un matériau Phong et appliquer la texture
            PhongMaterial material = new PhongMaterial();
            material.setDiffuseMap(earthTexture); // Mappage de l'image

            // Appliquer le matériau à la sphère
            sph.setMaterial(material);
        } else {
            System.err.println("Image non trouvée : " + imagePath);
        }

        // Initialiser la rotation
        ry = new Rotate(0, Rotate.Y_AXIS); // Rotation sur l'axe Y
        sph.getTransforms().add(ry);

        // Ajouter la sphère au groupe
        this.getChildren().add(sph);

        // Lancer l'animation
        startRotation();
    }

    private void startRotation() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                // Mettre à jour l'angle de rotation
                double angle = (time / 1e9) * 360 / 15; // Une rotation complète en 15 secondes
                ry.setAngle(angle % 360); // Limiter l'angle à [0, 360]
            }
        };
        animationTimer.start();
    }
}
