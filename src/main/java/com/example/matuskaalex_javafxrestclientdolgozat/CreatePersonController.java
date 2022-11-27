package com.example.matuskaalex_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CreatePersonController extends Controller{
    @FXML
    private TextField textFieldName;
    @FXML
    private Spinner<Integer> spinnerAge;
    @FXML
    private Button buttonSubmit;
    @FXML
    private TextField textFieldMinosites;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField textFieldMeret;

    @FXML
    private void initialize(){
        SpinnerValueFactory.IntegerSpinnerValueFactory valuefactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 30);
        spinnerAge.setValueFactory(valuefactory);
    }

    @FXML
    public void submitClick(ActionEvent actionEvent) {
        String nev = textFieldName.getText();
        String minosites = textFieldMinosites.getText();
        String meret = textFieldMeret.getText();
        Boolean alt8 = checkBox.isSelected();
        int kor = spinnerAge.getValue();
        if (nev.isEmpty()){
            warning("Nev megadasa kotelezo");
            return;
        }
        if (minosites.isEmpty()){
            warning("Email megadasa kotelezo");
            return;
        }
        Person newPerson = new Person(0, nev, kor, minosites, alt8, meret);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newPerson);
        try {
            Response respone = RequestHandler.post(App.BASE_URL, json);
            if (respone.getResponseCode() == 201){
                textFieldName.setText("");
                textFieldMinosites.setText("");
                textFieldMeret.setText("");
                checkBox.setSelected(false);
                spinnerAge.getValueFactory().setValue(30);

            }else {
                error("Hiba a felvetel soran tesom", respone.getContent());
            }

        } catch (IOException e) {
            error("Nem ment tesom","");
        }


    }
}
