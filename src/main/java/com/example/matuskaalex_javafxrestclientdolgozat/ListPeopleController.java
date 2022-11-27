package com.example.matuskaalex_javafxrestclientdolgozat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.google.gson.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class ListPeopleController extends Controller{

    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableColumn<Person, Integer> idCol;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, Integer> ageCol;
    @FXML
    private TableView<Person> peopletable;
    @FXML
    private Label welcomeText;
    @FXML
    private TableColumn<Person, String> meret;
    @FXML
    private TableColumn<Person, String> minosites;
    @FXML
    private TableColumn<Person, Boolean> altalanos8;

    @FXML
    private void initialize(){
        loadPeopleFromServer();
    }

    private void loadPeopleFromServer() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); //getId() függvény eredményét
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nev"));
        meret.setCellValueFactory(new PropertyValueFactory<>("meret"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("kor"));
        Platform.runLater(() ->{
            try {
                LoadPeopleFromServer();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hiba");
                alert.setHeaderText("Hiba történt a lekérdezés során");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                Platform.exit();
            }
        });
    }

    private void LoadPeopleFromServer() throws IOException {
        Response response =  RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Person[] people = converter.fromJson(content, Person[].class);
        peopletable.getItems().clear();
        for (Person person: people){
            peopletable.getItems().add(person);
        }
    }

    @FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-person-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create person");
            stage.setScene(scene);
            stage.setOnCloseRequest(event ->{
                try {
                    LoadPeopleFromServer();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("hiba");
                    alert.setHeaderText("Nem ment tesom");
                    alert.showAndWait();
                }
            });
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hiba tortent az urlap betoltese soran");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {

        Person selected = peopletable.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Torleshez valaszzon ki egy elemet");
            alert.showAndWait();
            return;
        }

        Alert conmfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        conmfirmationAlert.setTitle("Biztos?");
        conmfirmationAlert.setHeaderText("Biztos torlod tesom? "  + selected.getNev());
        Optional<ButtonType> optionalButtonType =  conmfirmationAlert.showAndWait();
        if (optionalButtonType.isPresent() && optionalButtonType.get().equals(ButtonType.OK)){
            String url = App.BASE_URL + "/" + selected.getId();
            try{
                RequestHandler.delete(url);
                LoadPeopleFromServer();
            }catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("hiba");
                alert.setHeaderText("Nem ment tesom");
                alert.showAndWait();
            }

        }
    }


}