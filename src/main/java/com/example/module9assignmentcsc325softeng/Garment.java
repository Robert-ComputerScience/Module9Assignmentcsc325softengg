package com.example.module9assignmentcsc325softeng;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * A JavaFX Garment application.
 * This application demonstrates the Abstract Factory design pattern to create
 * families of matching garments (Tops, Pants, Shoes) for different styles
 * (Professional, Casual, Party).
 */
public class Garment extends Application {

    // --- 1. Abstract Product Interfaces ---
    // These interfaces define the type of objects the factory can create.

    /** Abstract Product for Tops */
    interface Top {
        Image getImage();
    }

    /** Abstract Product for Pants */
    interface Pant {
        Image getImage();
    }

    /** Abstract Product for Shoes */
    interface Shoe {
        Image getImage();
    }

    // --- 2. Concrete Product Implementations ---
    // These are the specific variations of garments for each style.

    // Professional Garments
    class ProfessionalTop implements Top {
        @Override
        public Image getImage() {
            // NOTE: The image paths are placeholders.
            // You would replace "professional_top.png" with your actual image file path.
            return loadImage("professional_top.png");
        }
    }

    class ProfessionalPant implements Pant {
        @Override
        public Image getImage() {
            return loadImage("professional_pants.png");
        }
    }

    class ProfessionalShoe implements Shoe {
        @Override
        public Image getImage() {
            return loadImage("professional_shoes.png");
        }
    }

    // Casual Garments
    class CasualTop implements Top {
        @Override
        public Image getImage() {
            return loadImage("casual_top.png");
        }
    }

    class CasualPant implements Pant {
        @Override
        public Image getImage() {
            return loadImage("casual_pants.png");
        }
    }

    class CasualShoe implements Shoe {
        @Override
        public Image getImage() {
            return loadImage("casual_shoes.png");
        }
    }

    // Party Garments
    class PartyTop implements Top {
        @Override
        public Image getImage() {
            return loadImage("party_top.png");
        }
    }

    class PartyPant implements Pant {
        @Override
        public Image getImage() {
            return loadImage("party_pants.png");
        }
    }

    class PartyShoe implements Shoe {
        @Override
        public Image getImage() {
            return loadImage("party_shoes.png");
        }
    }

    // --- 3. Abstract Factory Interface ---
    // This interface declares a set of methods for creating each abstract product.

    /**
     * The Abstract Factory interface declares methods to create a family of related products.
     */
    interface GarmentFactory {
        Top createTop();
        Pant createPant();
        Shoe createShoe();
    }

    // --- 4. Concrete Factory Implementations ---
    // Each concrete factory corresponds to a specific variant (style) and creates
    // a consistent family of products.

    /** Concrete Factory for creating Professional garments. */
    class ProfessionalGarmentFactory implements GarmentFactory {
        @Override
        public Top createTop() {
            return new ProfessionalTop();
        }
        @Override
        public Pant createPant() {
            return new ProfessionalPant();
        }
        @Override
        public Shoe createShoe() {
            return new ProfessionalShoe();
        }
    }

    /** Concrete Factory for creating Casual garments. */
    class CasualGarmentFactory implements GarmentFactory {
        @Override
        public Top createTop() {
            return new CasualTop();
        }
        @Override
        public Pant createPant() {
            return new CasualPant();
        }
        @Override
        public Shoe createShoe() {
            return new CasualShoe();
        }
    }

    /** Concrete Factory for creating Party garments. */
    class PartyGarmentFactory implements GarmentFactory {
        @Override
        public Top createTop() {
            return new PartyTop();
        }
        @Override
        public Pant createPant() {
            return new PartyPant();
        }
        @Override
        public Shoe createShoe() {
            return new PartyShoe();
        }
    }


    // --- 5. JavaFX UI and Driver Logic ---
    // The client code that uses the factory to create and display garments.
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Garment");

        // UI Components
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Garment Style Selector");
        titleLabel.setFont(new Font("Arial", 24));

        ComboBox<String> styleComboBox = new ComboBox<>();
        styleComboBox.getItems().addAll("Professional", "Casual", "Party");
        styleComboBox.setValue("Professional"); // Default value

        Button generateButton = new Button("Generate Outfit");

        // ImageViews to display the selected outfit
        GridPane outfitGrid = new GridPane();
        outfitGrid.setHgap(20);
        outfitGrid.setVgap(10);
        outfitGrid.setAlignment(Pos.CENTER);

        ImageView topImageView = new ImageView();
        ImageView pantsImageView = new ImageView();
        ImageView shoesImageView = new ImageView();
        topImageView.setFitHeight(150);
        topImageView.setFitWidth(150);
        pantsImageView.setFitHeight(150);
        pantsImageView.setFitWidth(150);
        shoesImageView.setFitHeight(150);
        shoesImageView.setFitWidth(150);

        outfitGrid.add(new Label("Top"), 0, 0);
        outfitGrid.add(topImageView, 0, 1);
        outfitGrid.add(new Label("Pants"), 1, 0);
        outfitGrid.add(pantsImageView, 1, 1);
        outfitGrid.add(new Label("Shoes"), 2, 0);
        outfitGrid.add(shoesImageView, 2, 1);

        // Event Handler for the button
        generateButton.setOnAction(event -> {
            String selectedStyle = styleComboBox.getValue();
            GarmentFactory factory = getFactory(selectedStyle);

            if (factory != null) {
                // Use the factory to create a matching set of garments
                Top top = factory.createTop();
                Pant pant = factory.createPant();
                Shoe shoe = factory.createShoe();

                // Display the garments in the UI
                topImageView.setImage(top.getImage());
                pantsImageView.setImage(pant.getImage());
                shoesImageView.setImage(shoe.getImage());
            }
        });

        // Initial outfit generation
        generateButton.fire();

        HBox controls = new HBox(10, styleComboBox, generateButton);
        controls.setAlignment(Pos.CENTER);

        root.getChildren().addAll(titleLabel, controls, outfitGrid);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Helper method to select the correct factory based on user input.
     * The client code doesn't know which specific factory it's getting;
     * it just knows it will get a factory that can create garments.
     * @param style The selected style ("Professional", "Casual", "Party")
     * @return A GarmentFactory instance for the chosen style.
     */
    private GarmentFactory getFactory(String style) {
        switch (style) {
            case "Professional":
                return new ProfessionalGarmentFactory();
            case "Casual":
                return new CasualGarmentFactory();
            case "Party":
                return new PartyGarmentFactory();
            default:
                return null;
        }
    }

    /**
     * Utility method to load images. This helps handle cases where an image might be missing.
     * @param name The file name of the image.
     * @return An Image object or null if not found.
     */
    private Image loadImage(String name) {
        try {
            // Assumes images are in a resources folder.
            // Update this path if your images are located elsewhere.
            // This now looks in the correct subfolder
            InputStream stream = getClass().getResourceAsStream(name);
            if(stream == null) throw new Exception();
            return new Image(stream);
        } catch (Exception e) {
            System.err.println("Warning: Could not load image: " + name);
            // Return a placeholder image if the real one is not found
            return new Image("https://via.placeholder.com/150/FFFFFF/000000?text=Not+Found");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}