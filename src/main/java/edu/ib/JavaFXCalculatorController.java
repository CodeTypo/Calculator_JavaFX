package edu.ib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class JavaFXCalculatorController {
    private boolean     dotPressed          = false; //A flag monitoring the '.' sign usage
    private boolean     zeroPressed         = false; //A flag monitoring the '0' input by user. inputs like: 00010 aren't allowed
    private final Calculator  calculator    = new Calculator(); // A new instance of calculator Class object
    private boolean     operationPerformed  = false; //A flag that notifies when '=' sign have been pressed
    private boolean     operationSelected   = false; //A flag that notifies when an mathematical operation operator have been clicked
    private boolean     percentFlag         = false; //A flag that notifies when a percent sign have been clicke

    //A serie of FXML objects being initialized and instantinated.

    @FXML
    private TextField display;

    @FXML
    private Button clear;

    @FXML
    private Button sign_swap;

    @FXML
    private Button divide;

    @FXML
    private Button percent;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button multiply;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button subtract;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button sum;

    @FXML
    private Button zero;

    @FXML
    private Button dot;

    @FXML
    private Button equals;

    //A method executing while a numeric button have been clicked.
    @FXML
    void numeric_btn_clicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String id     = button.getId();
        if(operationPerformed)
        {
            clear();
        }
        double current;
        current = !display.getText().equals("") ? Double.parseDouble(display.getText()) : 0;
        if(current >0 || !zeroPressed|| dotPressed) {
            switch (id) {
                case "zero"     -> {display.appendText("0");zeroPressed = true;}
                case "one"      -> display.appendText("1");
                case "two"      -> display.appendText("2");
                case "three"    -> display.appendText("3");
                case "four"     -> display.appendText("4");
                case "five"     -> display.appendText("5");
                case "six"      -> display.appendText("6");
                case "seven"    -> display.appendText("7");
                case "eight"    -> display.appendText("8");
                case "nine"     -> display.appendText("9");
            }
        }
        if(id.equals("dot")){
                if (!dotPressed && !(display.getText().equals(""))) {
                    display.appendText(".");
                    dotPressed = true;
                }
            }
        }

    //A method executing when other buttons have been clicked
    @FXML
    void operation_btn_clicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String id = button.getId();
        switch (id){
            case "sum":
            case "subtract":
            case "multiply":
            case "divide":
                operatorClicked(id);
                break;

            case "sign_swap":
                signSwap();
                break;

            case "percent":
                if(operationSelected){
                    percentFlag = true;
                    performEquals();
                }
                break;

            case "clear":
                clear();
                break;

            case "equals":
                performEquals();
                break;
        }
        dotPressed = false;
        zeroPressed = false;
    }

    private void operatorClicked(String id) {
        calculatorMaintenance();
        calculator.setOperation(id);
        operationSelected = true;
        percentFlag = false;
    }


    private void signSwap() {
        if(!display.getText().equals("")){
            String number="";
            if(!display.getText().startsWith("-")){
                number =("-"+display.getText());
            } else {
                StringBuilder sb = new StringBuilder(display.getText());
                number = sb.deleteCharAt(0).toString();
            }
            calculator.reset();
            calculator.setNumber(Double.parseDouble(number));
            display.setText(number);
        }
    }

    public void clear(){
        calculator.reset();
        display.clear();
        dotPressed = false;
        zeroPressed = false;
        operationPerformed = false;
        percentFlag = false;
        operationSelected = false;
    }

    public void performEquals(){
        if(!operationPerformed){
            if(!display.getText().equals(""))
                calculator.setNumber(Double.parseDouble(display.getText()));}
            double test = calculator.performOperation(percentFlag);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println(test);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            display.setText(String.valueOf(test));//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            operationPerformed = true;
    }


    public void calculatorMaintenance(){
        if(!display.getText().equals(""))
            calculator.setNumber(Double.parseDouble(display.getText()));
        display.clear();
        operationPerformed = false;
        zeroPressed = false;
    }

    @FXML
    void initialize() {
        assert display != null : "fx:id=\"display\" was not injected: check your FXML file 'calculator.fxml'.";
        assert clear != null : "fx:id=\"clear\" was not injected: check your FXML file 'calculator.fxml'.";
        assert sign_swap != null : "fx:id=\"sign_swap\" was not injected: check your FXML file 'calculator.fxml'.";
        assert divide != null : "fx:id=\"divide\" was not injected: check your FXML file 'calculator.fxml'.";
        assert percent != null : "fx:id=\"percent\" was not injected: check your FXML file 'calculator.fxml'.";
        assert seven != null : "fx:id=\"seven\" was not injected: check your FXML file 'calculator.fxml'.";
        assert eight != null : "fx:id=\"eight\" was not injected: check your FXML file 'calculator.fxml'.";
        assert nine != null : "fx:id=\"nine\" was not injected: check your FXML file 'calculator.fxml'.";
        assert multiply != null : "fx:id=\"multiply\" was not injected: check your FXML file 'calculator.fxml'.";
        assert four != null : "fx:id=\"four\" was not injected: check your FXML file 'calculator.fxml'.";
        assert five != null : "fx:id=\"five\" was not injected: check your FXML file 'calculator.fxml'.";
        assert six != null : "fx:id=\"six\" was not injected: check your FXML file 'calculator.fxml'.";
        assert subtract != null : "fx:id=\"subtarct\" was not injected: check your FXML file 'calculator.fxml'.";
        assert one != null : "fx:id=\"one\" was not injected: check your FXML file 'calculator.fxml'.";
        assert two != null : "fx:id=\"two\" was not injected: check your FXML file 'calculator.fxml'.";
        assert three != null : "fx:id=\"three\" was not injected: check your FXML file 'calculator.fxml'.";
        assert sum != null : "fx:id=\"sum\" was not injected: check your FXML file 'calculator.fxml'.";
        assert zero != null : "fx:id=\"zero\" was not injected: check your FXML file 'calculator.fxml'.";
        assert dot != null : "fx:id=\"dot\" was not injected: check your FXML file 'calculator.fxml'.";
        assert equals != null : "fx:id=\"equals\" was not injected: check your FXML file 'calculator.fxml'.";

    }
}
