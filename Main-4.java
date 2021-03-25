package sample;


import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.event.ChangeListener;


public class Main extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a border pane
        BorderPane pane = new BorderPane();

        // Place nodes in the pane
        pane.setTop(getHBox());
        pane.setLeft(getVBox());
        pane.setStyle("-fx-border-color: pink");

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("My Movie Theatre"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);
    }

    Movie[] movies = new Movie[50];


    private HBox getHBox() {
        HBox hBox = new HBox(15); // Create an HBox with 15px spacing
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: #eaf8fd");
        Label headingText = new Label ("Welcome To Your Movie List!");
        headingText.setTextFill(Color.TURQUOISE);
        headingText.setFont(Font.font("Comic Sans",FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 35));
        hBox.getChildren().add(createMovieShape());
        hBox.getChildren().add(new Label(" "));
        hBox.getChildren().add(headingText);
        return hBox;
    }

    private Polygon createPlayIcon() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                0.0, 0.0,
                0.0, 100.0,
                75.0, 50.0});

        polygon.setFill(Color.GREEN);
        return polygon;
    }

    private Rectangle createRectangleShape(){
        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(200);
        rectangle.setHeight(200);
        rectangle.setFill(Color.DARKGREY);
        return rectangle;
    }

    private StackPane createMovieShape(){
        StackPane movieShape = new StackPane();
        movieShape.getChildren().addAll(
                createRectangleShape(),
                createPlayIcon()
        );
        return movieShape;
    }


    private VBox getVBox() {
        VBox vBox = new VBox(15); // Create a VBox with 15px spacing
        vBox.setStyle("-fx-background-color: rgb(246,246,246,1)");
        vBox.setPadding(new Insets(15));
        Label movieOrderTitle = new Label ("Order By: ");
        movieOrderTitle.setFont(Font.font("Arial",FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 25));
        movieOrderTitle.setTextFill(Color.rgb(0,114,198,1));

        String[] orderBy = {"Title", "Director", "Leading Star", "Rating"};

        VBox movieColumn = new VBox(15); // Create a VBox with 15px spacing
        movieColumn.setPadding(new Insets(15));
        Label movieLabel = new Label("movie");
        movieColumn.getChildren().add(displayMovies());

        ComboBox<String> orderChosen = new ComboBox<>();
        orderChosen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Integer orderByIndex=orderChosen.getSelectionModel().getSelectedIndex();
                sortMovies(orderByIndex);
                movieColumn.getChildren().clear();
                movieColumn.getChildren().add(displayMovies());
            }
        });
        ObservableList<String> items = FXCollections.observableArrayList(orderBy);
        orderChosen.getItems().addAll(items); // Add items to combo box
        orderChosen.setPromptText("Make a selection:");


        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");

        TextField directorInput = new TextField();
        directorInput.setPromptText("Director");

        TextField leadingStarInput = new TextField();
        leadingStarInput.setPromptText("Leading Star");

        TextField ratingInput = new TextField();
        ratingInput.setPromptText("Rating");

        HBox inputFields = new HBox();
        inputFields.getChildren().addAll(titleInput,new Label(" "),directorInput,new Label(" "),leadingStarInput,new Label(" "),ratingInput);

        Button submit = new Button("Submit");

        Label notification = new Label();
        notification.setTextFill(Color.RED);


        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER); // Set center alignment
        pane.setPadding(new Insets(0, 0, 0, 0));
        pane.setHgap(10);
        pane.setVgap(5.5);
        pane.setMaxWidth(10000);
        vBox.getChildren().add(notification);

        pane.add(movieColumn, 0, 0);


        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(orderChosen.getSelectionModel().getSelectedIndex() );
                System.out.println(titleInput.getText() );
                if (ratingInput.getText() !=null) {
                    if (directorInput.getText() != "" && titleInput.getText() != "" && leadingStarInput.getText() != "" && Double.parseDouble(ratingInput.getText()) > -1) {

                        notification.setText("");
                        movieColumn.getChildren().clear();
                        String titleText = titleInput.getText();
                        String leadingStarText = leadingStarInput.getText();
                        String directorText = directorInput.getText();
                        Double ratingText = Double.parseDouble(ratingInput.getText());
                        addMovie(titleText, directorText, leadingStarText, ratingText, movieColumn);
                        movieColumn.getChildren().add(displayMovies());
                    } else {
                        notification.setText("Please add all of the info and try again! Make sure rating field is a number value!");
                    }
                }
            }

        });



        vBox.getChildren().add(new Label("Add a movie to your list!"));
        vBox.getChildren().add(inputFields);
        vBox.getChildren().add(movieOrderTitle);
        vBox.getChildren().add(orderChosen);
        vBox.getChildren().add(submit);
        vBox.getChildren().add(pane);
        return vBox;
    }


    private void addMovie(String titleInput,String leadingStarInput, String directorInput, double ratingInput, VBox movieColumn ) {
        String title = titleInput;
        String director = directorInput;
        String leadStar = leadingStarInput;
        double rating = ratingInput;
        Button removeButton = new Button("remove "+title);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                removeMovie(removeButton);
                movieColumn.getChildren().clear();
                movieColumn.getChildren().add(displayMovies());
            }
        });
        Button plusButton = new Button("+");
        plusButton.setId(title);
        plusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    plusRating(plusButton);
                movieColumn.getChildren().clear();
                movieColumn.getChildren().add(displayMovies());
            }
        });
        Button minusButton = new Button ("-");
        minusButton.setId(title);
        minusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    minusRating(minusButton);
                movieColumn.getChildren().clear();
                movieColumn.getChildren().add(displayMovies());
            }
        });
        for (int i = 0; i < movies.length; i++) {

            if (movies[i] == null) {
                movies[i] = new Movie(title, rating, director, leadStar,removeButton, plusButton,minusButton);
                System.out.println(title + " has been added");
                return;
            }
        }
        System.out.println("List is full, cannot add a movie");
    }

    private void removeMovie(Button removeButton) {
        String buttonText = removeButton.getText();
        String title = buttonText.replace("remove ", "");
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getTitle().equalsIgnoreCase(title)) {
                    movies[i] = null;
                    System.out.println(title + " has been removed");
                    return;
                }
            }
        }

        System.out.println("Could not find " + title);
    }


    private void plusRating(Button plusButton) {
        System.out.println(plusButton.getId());
        String buttonId = plusButton.getId();
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getTitle().equalsIgnoreCase(buttonId)) {
                    movies[i].setRating(movies[i].getRating() + Double.parseDouble("0.1"));
                    return;
                }
            }
        }

    }

    private void minusRating(Button minusButton) {
        System.out.println(minusButton.getId());
        String buttonId = minusButton.getId();
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                if (movies[i].getTitle().equalsIgnoreCase(buttonId)) {
                    movies[i].setRating(movies[i].getRating() - Double.parseDouble("0.1"));
                    return;
                }
            }
        }
    }




    private void sortMovies(Integer orderByIndex) {
        int n = movies.length;
        switch (orderByIndex) {

            case 0:
                for (int i = 0; i < n - 1; i++) {
                    int min_idx = i;
                        for (int j = i + 1; j < n; j++) {
                            if (movies[j] != null && movies[min_idx] != null) {
                            {
                                if (movies[j].getTitle().compareTo(movies[min_idx].getTitle()) < 0)
                                    min_idx = j;
                            }
                        }
                    }
                    //put the new minimum in the i-th position.
                    Movie temp = movies[min_idx];
                    movies[min_idx] = movies[i];
                    movies[i] = temp;
                }
                   return;


            case 1:
                for (int i = 0; i < n - 1; i++) {
                    int min_idx = i;
                    for (int j = i + 1; j < n; j++) {
                        if (movies[j] != null && movies[min_idx] != null) {
                            {
                                if (movies[j].getDirector().compareTo(movies[min_idx].getDirector()) < 0)
                                    min_idx = j;
                            }
                        }
                    }
                    //put the new minimum in the i-th position.
                    Movie temp = movies[min_idx];
                    movies[min_idx] = movies[i];
                    movies[i] = temp;
                }
                return;



            case 2:
                for (int i = 0; i < n - 1; i++) {
                    int min_idx = i;
                    for (int j = i + 1; j < n; j++) {
                        if (movies[j] != null && movies[min_idx] != null) {
                            {
                                if (movies[j].getLeadStar().compareTo(movies[min_idx].getLeadStar()) < 0)
                                    min_idx = j;
                            }
                        }
                    }
                    //put the new minimum in the i-th position.
                    Movie temp = movies[min_idx];
                    movies[min_idx] = movies[i];
                    movies[i] = temp;
                }
                return;

            case 3:
                for (int i = 0; i < n - 1; i++) {
                    int min_idx = i;
                    for (int j = i + 1; j < n; j++) {
                        if (movies[j] != null && movies[min_idx] != null) {
                            if (movies[j].getRating() < movies[min_idx].getRating()) {
                                min_idx = j;
                            }
                        }
                    }
                    Movie temp = movies[min_idx];
                    movies[min_idx] = movies[i];
                    movies[i] = temp;
                }
                return;
        }
    }

    private VBox displayMovies() {
        VBox movieLine = new VBox();
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                GridPane movieEntry = new GridPane();
                movieEntry.getColumnConstraints().add(new ColumnConstraints(150)); // column 0 is 100 wide
                movieEntry.getColumnConstraints().add(new ColumnConstraints(150));
                movieEntry.getColumnConstraints().add(new ColumnConstraints(150));
                movieEntry.getColumnConstraints().add(new ColumnConstraints(150));
                movieEntry.getColumnConstraints().add(new ColumnConstraints(150));
                movieEntry.getColumnConstraints().add(new ColumnConstraints(50));
                movieEntry.getColumnConstraints().add(new ColumnConstraints(50));



                Label title = new Label("Title: " + movies[i].getTitle());
                Label director = new Label("Director: " + movies[i].getDirector());
                Label leadingStar = new Label("Leading Star: " + movies[i].getLeadStar());
                Label rating = new Label("Rating " + movies[i].getRating());
                Button removeButton = movies[i].getRemoveButton();
                Button plusButton = movies[i].getPlusButton();
                Button minusButton = movies[i].getMinusButton();
                movieEntry.add(title, 0, 0);
                movieEntry.add(director, 1, 0);
                movieEntry.add(leadingStar, 2, 0);
                movieEntry.add(rating, 3, 0);
                movieEntry.add(removeButton, 4, 0);
                movieEntry.add(plusButton, 5, 0);
                movieEntry.add(minusButton, 6, 0);
                movieLine.getChildren().add(movieEntry);
            }
        }
        return movieLine;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
