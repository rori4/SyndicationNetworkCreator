package sample;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    private boolean err_b;

    @FXML
    private BorderPane root;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView btn_menu;

    @FXML
    private JFXButton btn_choose;

    @FXML
    private JFXButton btn_generate;

    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXTextField first_name;

    @FXML
    private JFXTextField last_name;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXDatePicker birthday;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXComboBox<String> country;

    @FXML
    private JFXButton btn_submit;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label err_birthday;

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAllCountriesToComboBox();
        gender.getItems().addAll("Male", "Female");

        btn_submit.setOnMouseClicked(event -> {
            checkInput();
        });


        btn_menu.setOnMouseClicked(event -> {
            System.out.println("You clicked me");
            if (!drawer.isShown()) {
                drawer.open();
            }
        });
        root.setOnMouseClicked(event1 -> {
            if (drawer.isShown()) {
                drawer.close();
            }
        });

        try {
            AnchorPane box = FXMLLoader.load(getClass().getResource("SideMenu.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        Circle shape = new Circle(75, 75, 75);
        profilePic.setClip(shape);
    }

    private void checkInput() {
        if (first_name.getText().equals("")) {
            first_name.setPromptText("This field is mandatory");
            first_name.setStyle("-fx-prompt-text-fill: red;");
        }
        if (last_name.getText().equals("")) {
            last_name.setPromptText("This field is mandatory");
            last_name.setStyle("-fx-prompt-text-fill: red;");
        }

        if (username.getText().length() <= 6 || username.getText().length() >= 20) {
            username.setPromptText("Please use between 6 and 30 characters.");
            username.setStyle("-fx-prompt-text-fill: red;");
            username.clear();
        }

        if (password.getText().length() <= 8) {
            password.setPromptText("Password needs to be at least 8 characters.");
            password.setStyle("-fx-prompt-text-fill: red;");
            password.clear();
        }

        if (birthday.getValue() == null) {
            birthday.setPromptText("Please choose a birthday");
            birthday.setStyle("-fx-prompt-text-fill: red;");
        }

        System.out.println(gender.getValue());
        if (gender.getValue() == null) {
            gender.setPromptText("Please set a gender");
            gender.setStyle("-fx-prompt-text-fill: red;");
        }
        if (country.getValue() == null) {
            country.setPromptText("Please choose a country");
            country.setStyle("-fx-prompt-text-fill: red;");
        }

//            if (password.getText().length()<=8){
//                password.setPromptText("Password needs to be at least 8 characters.");
//                password.setStyle("-fx-prompt-text-fill: red;");
//                password.clear();
//            }
//
//            if (password.getText().length()<=8){
//                password.setPromptText("Password needs to be at least 8 characters.");
//                password.setStyle("-fx-prompt-text-fill: red;");
//                password.clear();
//            }
    }

    private void setAllCountriesToComboBox() {
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            String countryName = obj.getDisplayCountry();
            if (countryName.length() >= 35) {
                String substring = countryName.substring(0, 28);
                countryName = substring;
            }
            country.getItems().add(countryName);
        }
    }

    @FXML
    public void fadeIn(MouseEvent event, Node node, int Time) {
        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(Time), node);
        fadeTransition.setFromValue(0.0f);
        fadeTransition.setToValue(1.0f);
        fadeTransition.play();
    }

    @FXML
    public void fadeOut(MouseEvent event, Node node, int Time) {
        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(Time), node);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.0f);
        fadeTransition.play();
    }
}
