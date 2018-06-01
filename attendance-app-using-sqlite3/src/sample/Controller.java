package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.xml.transform.Result;
import java.sql.*;

public class Controller {

    Connection conn = null;
    String sql;
    @FXML
    TextArea recordView;
        String date = null;
        String prevDate;
        @FXML
        Button presentButton;
        @FXML
        AnchorPane dbchoice;
        @FXML
        VBox vb;
    @FXML
    DatePicker dateSelector;
@FXML
    Label statusLabel;

    public boolean connect(boolean isStored) {

        try {
            // db parameters
            String url;
            if(isStored)
                url= "jdbc:sqlite:store.db";
            else
                url = "jdbc:sqlite::memory:";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite database has been established.");
            return true;

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return false;
        }
    }

    public void createTable() {
        sql = "CREATE TABLE IF NOT EXISTS attendance (id integer PRIMARY KEY AUTOINCREMENT, entry_date TEXT NOT NULL UNIQUE, status TEXT NOT NULL);";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Query executed");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void showTable() {
        sql = "SELECT * FROM attendance;";
        try {
            Statement stm = conn.createStatement();
            ResultSet rm = stm.executeQuery(sql);
            System.out.println("Query executed. SELECT fetched.");
            while(rm.next()){
                recordView.appendText(rm.getInt("id") + "\t" + rm.getString("entry_date") + "\t" + rm.getString("status") + "\n");
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

        @FXML
        public void handleDate () {
            date = String.valueOf(dateSelector.getValue());
            statusLabel.setText("Selected date: " + date);
        }

        @FXML
        public void handlePresentButton () {

            if(date != null) {
                if(prevDate != date)
                    markAsPresent(date);
                else
                    replaceAsPresent(date);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Select a date");
                alert.setHeaderText(null);
                alert.setContentText("You forgot to select a date");
                alert.showAndWait();
            }

        }

    private void markAsPresent(String date) {
        sql = "INSERT INTO attendance VALUES (NULL, '" + date + "', 'PRESENT');";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Query executed");
            prevDate = date;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void markAsAbsent(String date) {
        sql = "INSERT INTO attendance VALUES (NULL, '" + date + "', 'ABSENT');";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Query executed");
            prevDate = date;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
        public void handleAbsentButton () {
            if(date != null) {
                if(prevDate != date)
                    markAsAbsent(date);
                else
                    replaceAsAbsent(date);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Select a date");
                alert.setHeaderText(null);
                alert.setContentText("You forgot to select a date");
                alert.showAndWait();
            }

        }

    private void replaceAsAbsent(String date) {
        sql = "UPDATE attendance SET status = 'ABSENT' where entry_date = '" + date + "';";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Query executed");
            prevDate = date;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void replaceAsPresent(String date) {
        sql = "UPDATE attendance SET status = 'PRESENT' where entry_date = '" + date + "';";
        try {
            Statement stm = conn.createStatement();
            stm.execute(sql);
            System.out.println("Query executed");
            prevDate = date;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception occurred");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
        public void handleRecordView () {
            refreshView();
        }

    public void handleStoredDB(ActionEvent actionEvent) {
        if (connect(true)) {
            createTable();
            vb.getChildren().remove(dbchoice);
        }
    }

    public void handleMemoryDB(ActionEvent actionEvent) {
        if(connect(false)) {
            createTable();
            vb.getChildren().remove(dbchoice);
    }
        }

    public void refreshView() {
            recordView.setText("");
            showTable();
    }
}
