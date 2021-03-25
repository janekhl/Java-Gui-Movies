package sample;
import javafx.scene.control.Button;


public class Movie{
    private String title;
    private double rating;
    private String director, leadStar;
    private Button removeButton;
    private Button plusButton;
    private Button minusButton;

    public Movie() {
    }

    public Movie(String title, double rating, String director, String leadStar, Button removeButton, Button plusButton, Button minusButton) {
        this.title = title;
        this.rating = rating;
        this.director = director;
        this.leadStar = leadStar;
        this.removeButton = removeButton;
        this.plusButton= plusButton;
        this.minusButton=minusButton;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadStar() {
        return leadStar;
    }

    public void setLeadStar(String leadStar) {
        this.leadStar = leadStar;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }

    public Button getPlusButton(){
        return plusButton;
    }

    public Button getMinusButton(){
        return minusButton;
    }

}
