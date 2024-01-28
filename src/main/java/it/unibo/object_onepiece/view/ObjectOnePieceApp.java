package it.unibo.object_onepiece.view;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.controlsfx.control.spreadsheet.SpreadsheetView;
import org.controlsfx.control.tableview2.TableColumn2;
import org.controlsfx.control.tableview2.TableView2;

import eu.lestard.grid.GridModel;
import eu.lestard.grid.GridView;
import it.unibo.object_onepiece.view.EntityView.EntityType;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.WritableIntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Sample JavaFX application.
 */
public final class ObjectOnePieceApp extends Application {

    private static final int MAP_ROWS = 10;
    private static final int MAP_COLUMNS = 10;

    private static final Color CELL_BORDER_COLOR = Color.rgb(66, 138, 245);
    private static final Color DEFAULT_COLOR = Color.rgb(2, 127, 222);
    private static final int RIGHT_ANGLE = 90;

    public enum Entity {
        ENEMY,
        PLAYER,
        BARREL,
        WATER;
    }

    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT;
    }

    private final GridModel<Pair<Entity, Optional<Direction>>> gridModel = new GridModel<>();
    private final GridView<Pair<Entity, Optional<Direction>>> gridView = new GridView<>();

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Object One Piece!");

        BorderPane borderPane = new BorderPane();

        gridModel.setDefaultState(new Pair<>(Entity.WATER, Optional.empty()));
        gridModel.setNumberOfColumns(MAP_COLUMNS);
        gridModel.setNumberOfRows(MAP_ROWS);
        gridView.setGridModel(gridModel);
        gridModel.getCells().forEach(i -> gridView.addColorMapping(new Pair<>(Entity.WATER, Optional.empty()), DEFAULT_COLOR));
        gridView.cellBorderColorProperty().set(CELL_BORDER_COLOR);

        Stream.of(Entity.values()).forEach(i -> {
            String entityName = i.toString().toLowerCase();
            URL imgPath = getClass().getResource("/img/sprites/" + entityName + "/" + entityName + ".png");
            if (imgPath != null) {
                Stream.of(Direction.values()).forEach(j -> {
                    gridView.addNodeMapping(new Pair<>(i, Optional.of(j)), cell -> {
                        Image img = new Image(imgPath.toString());
                        ImageView entityImage = new ImageView(img);
                        if (cell.getState().getY().isPresent()) {
                            var direction = cell.getState().getY().get();
                            entityImage.setRotate(RIGHT_ANGLE * direction.ordinal());
                        }
                        entityImage.setPreserveRatio(true);
                        entityImage.fitWidthProperty().bind(gridView.cellSizeProperty());
                        entityImage.fitHeightProperty().bind(gridView.cellSizeProperty());
                        return entityImage;
                    });
                });
            }
            
        });
        
        Canvas health = new Canvas(100, 100);
        GraphicsContext healthGC = health.getGraphicsContext2D();
        Rectangle healthBar = new Rectangle(20, 100);
        drawHealthBar(healthGC, healthBar);

        Label healthPoints = new Label("100");

        VBox healthContainer = new VBox();
        healthContainer.getChildren().addAll(health, healthPoints);


        borderPane.setCenter(gridView);
        borderPane.setRight(healthContainer);
        Scene scene = new Scene(borderPane, 600, 600);
        scene.getStylesheets().add("/css/ObjectOnePieceApp.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawEntity(int row, int col, Entity e, Optional<Direction> d) {
        gridModel.getCell(col, row).changeState(new Pair<>(e, d));
    }

    private void drawHealthBar(GraphicsContext gc,Rectangle rect) {
        gc.setFill(Color.GREEN);
        gc.fillRect(rect.getX(),      
               rect.getY(), 
               rect.getWidth(), 
               rect.getHeight());
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
    }

    /*public class Entity {
        private StringProperty name;
        private IntegerProperty health;
        private Canvas healthBar;
        private GraphicsContext gc;

        public Entity(final String string, final int health) {
            this.name = new SimpleStringProperty(string);
            this.health = new SimpleIntegerProperty(health);
        }

    }*/

    /**
     * Program's entry point.
     * @param args
     */
    public static void run(final String... args) {
        launch(args);
    }

    // Defining the main methods directly within JavaFXApp may be problematic:
    // public static void main(final String[] args) {
    //        run();
    // }

    /**
     * Entry point's class.
     */
    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        /**
         * Program's entry point.
         * @param args
         */
        public static void main(final String... args) {
            Application.launch(ObjectOnePieceApp.class, args);
            /* 
            The following line raises: Error: class it.unibo.samplejavafx.JavaFXApp$Main 
            is not a subclass of javafx.application.Application
            Because if you do not provide the Application subclass to launch() it will consider the enclosing class)
            */
            // JavaFXApp.launch(args);
            // Whereas the following would do just fine:
            // JavaFXApp.run(args)
        }
    }
}
