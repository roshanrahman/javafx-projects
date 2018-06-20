package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import com.udojava.evalex.*;


public class Controller {

    @FXML
    TextField outputField;
    @FXML
    Label statusLabel;

    String bufferString = "";
    String eval = "";
    String operation = "none";
    boolean firstOp = false;
    boolean secondOp = false;



    private void updateField() {
        outputField.setText(bufferString);
        statusLabel.setText(eval);
    }

    public void handleAbout(ActionEvent actionEvent) {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setHeaderText("Simple Calculator");
        aboutAlert.setContentText("Written in Java. Uses EvalEx 2.0 Expression module for Java. Uses JavaFX UI Framework.");
        aboutAlert.setTitle("About");
        aboutAlert.showAndWait();
    }

    public void handleClearCharButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false)
        bufferString = bufferString.substring(0, bufferString.length()-1);
        updateField();
    }

    public void handleClearEntryButton(ActionEvent actionEvent) {
        bufferString = "";
        updateField();
    }

    public void handleClearButton(ActionEvent actionEvent) {
        bufferString = "";
        eval = "";
        updateField();
    }

    public void handleSignButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Double.parseDouble(bufferString);
            if(temp > 0) {
                temp = -temp;
            } else if(temp < 0){
                temp = Math.abs(temp);
            }
            bufferString = String.valueOf(temp);
            updateField();
        }
    }

    public void handleRootButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Double.parseDouble(bufferString);
            temp = Math.sqrt(Math.abs(temp));
            bufferString = String.valueOf(temp);
            updateField();
        }
    }

    public void handle7Button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("7");
        updateField();
    }



    public void handle8Button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("8");
        updateField();
    }

    public void handle9Button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("9");
        updateField();
    }

    public void handleDivideButton(ActionEvent actionEvent) {

            eval = eval.concat(bufferString + "/");
            bufferString = "";
            updateField();
    }

    public void handleModuloButton(ActionEvent actionEvent) {
          eval = eval.concat(bufferString + "%");
        bufferString = "";
        updateField();

    }

    public void handle4button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("4");
        updateField();
    }

    public void handle5button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("5");
        updateField();
    }

    public void handle6button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("6");
        updateField();
    }

    public void handleMultiplyButton(ActionEvent actionEvent) {
         eval = eval.concat(bufferString + "*");
        bufferString = "";
        updateField();
    }

    public void handleFractionButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Double.parseDouble(bufferString);
            temp = ( 1 / temp);
            bufferString = String.valueOf(temp);
            updateField();
        }
    }

    public void handle1button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("1");
        updateField();
    }

    public void handle2button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("2");
        updateField();
    }

    public void handle3button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("3");
        updateField();
    }

    public void handleMinusButton(ActionEvent actionEvent) {
        eval = eval.concat(bufferString + "-");
        bufferString = "";
        updateField();

    }

    public void handleDotButton(ActionEvent actionEvent) {
        if(bufferString.lastIndexOf(".") != -1)
            return;

        if(bufferString.isEmpty() == false) {
                bufferString = bufferString.concat(".");

        } else {
            bufferString = bufferString.concat("0.");
        }
        updateField();
    }

    public void handlePlusButton(ActionEvent actionEvent) {
        eval = eval.concat(bufferString + "+");
        bufferString = "";
        updateField();

    }



    public void handle0Button(ActionEvent actionEvent) {
        bufferString = bufferString.concat("0");
        updateField();
    }

    public void handleEqualsButton() {
        if(eval.isEmpty())
            return;
        eval = eval.concat(bufferString);
        updateField();
        Expression e = new Expression(eval);
        try {
            bufferString = String.valueOf(e.eval());
            eval = "";
        } catch (Exception exp) {
            Alert exp1 = new Alert(Alert.AlertType.ERROR);
            exp1.setHeaderText("Invalid expression");
            exp1.setContentText(exp.getLocalizedMessage());
            exp1.showAndWait();
            eval = "";
        }
        updateField();
        
    }

    public void handleSinButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Math.sin(Double.parseDouble(bufferString));
            bufferString = String.valueOf(temp);
        }updateField();
    }

    public void handleCosButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Math.cos(Double.parseDouble(bufferString));
            bufferString = String.valueOf(temp);
        }updateField();
    }

    public void handleTanButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Math.tan(Double.parseDouble(bufferString));
            bufferString = String.valueOf(temp);
        }updateField();
    }

    public void handleLnButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Math.log10(Double.parseDouble(bufferString));
            bufferString = String.valueOf(temp);
        }updateField();
    }

    public void handleSquareButton(ActionEvent actionEvent) {
        if(bufferString.isEmpty() == false) {
            double temp = Math.pow(Double.parseDouble(bufferString), 2);
            bufferString = String.valueOf(temp);
        }updateField();
    }
}
