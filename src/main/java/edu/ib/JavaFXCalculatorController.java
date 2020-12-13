package edu.ib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class JavaFXCalculatorController {
    private boolean     dotPressed          = false;            //A flag monitoring the '.' sign usage
    private boolean     zeroPressed         = false;            //A flag monitoring the '0' input by user. inputs like: 00010 aren't allowed
    private final Calculator  calculator    = new Calculator(); // A new instance of calculator Class object
    private boolean     operationPerformed  = false;            //A flag that notifies when '=' sign have been pressed
    private boolean     operationSelected   = false;            //A flag that notifies when an mathematical operation operator have been clicked
    private boolean     percentFlag         = false;            //A flag that notifies when a percent sign have been clicke

    //A series of FXML objects being initialized and instantiated.
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FXML INIT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ /FXML INIT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //A method executing while a numeric button have been clicked.
    @FXML
    void numeric_btn_clicked(ActionEvent event) {
        Button button = (Button) event.getSource(); //Gets a reference to the button
        String id     = button.getId();             //Gets its id
        if(operationPerformed)                      //checks if the number was clicked after '=' or '%' was clicked
        {                                           //If so, it means the user has finished his previous calculations
            clear();                                //So the calculator is being cleared
        }
        double current;
        //An enhanced if statement, checks if the displayed value is different than "" if so, it is being stored into 'current' var else, current = 0;
        current = !display.getText().equals("") ? Double.parseDouble(display.getText()) : 0;

        //A statement checking if the number on display is higher than 0 OR if 0 has not been pressed yet OR a dot has been pressed
        if(current >0 || !zeroPressed|| dotPressed) {
            //If the statement is correct, a number displayed by the button clicked can be added to the number shown on display
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
        //If the user presseed the dot '.' sign
        if(id.equals("dot")){
                //If the dot has not been pressed yet and the display is not empty
                if (!dotPressed && !(display.getText().equals(""))) {
                    display.appendText("."); //Its being displayed
                    dotPressed = true;       //A flag is being set
                }
            }
        }

    //A method executing when other (operational) buttons have been clicked {% , = , - , + , * , / }
    @FXML
    void operation_btn_clicked(ActionEvent event) {
        Button button = (Button) event.getSource(); //Gets a reference to the button
        String id = button.getId();                 //Gets its id

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
                    percentFlag = true; //When the user pressed the percent sign, the equals method changes a bit
                    performEquals();    //So the flag needs to be set before calling it
                }else {clear();}
                break;

            case "clear":
                clear();
                break;

            case "equals":
                performEquals();
                break;
        }
        dotPressed = false;             //After pressing an operation symbol, user should be able to type
        zeroPressed = false;            //Any number he/she wants, so the flags are being cleared
    }

    private void operatorClicked(String id) {
        calculatorMaintenance();        //calls calculatorMaintenance() - some basic repetitive tasks
        calculator.setOperation(id);    //calls Calculator class setOperation(id) method
        operationSelected = true;       //Sets the flag telling that the user selected some mathematical operation
        percentFlag = false;            //User did not pressed the % sign so the flag is being cleared
    }


    private void signSwap() {
        //A sign swap method, everything it does is just adding or removing the '-' sign in front of the number, of course it affects the number's value
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
        calculator.reset(); //A Calculator object's reset() method is being called
        display.clear();    //The display is being cleared
        dotPressed = zeroPressed = operationPerformed = percentFlag = operationSelected = false; //All bools set to FALSE
    }

    public void performEquals(){
        //If no mathematical operation has been performed yet
        if(!operationPerformed){
            //if the display isn't empty
            if(!display.getText().equals(""))
                //Calls the Calculator class setNumber(double x) method making the calculator to store the value that was displayed on the screen
                calculator.setNumber(Double.parseDouble(display.getText()));}

        //Calling the calculator's performOperation(percentFlag) method and storing its return value in 'value' variable
        Double value = calculator.performOperation(percentFlag);
        //Scientific notation formatter
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat) nf;
        formatter.applyPattern("####0.#E0");
        //Prints the value on the display
        display.setText(formatter.format(value));
            operationPerformed = true;
    }


    public void calculatorMaintenance(){
        //A method containing a group of statements that got called frequently in the code after the operational buttons were clicked
        //Assuming the user clicks an operational button after he/she input some number
        //Checking if there was an input, if there was, its being passed to the Calculator object via its setNumber(double x)method
        if(!display.getText().equals(""))
            calculator.setNumber(Double.parseDouble(display.getText()));
        display.clear();            //display is being cleared to be ready for the second nu.ber input
        operationPerformed = false; //An operation has not been performed yet, user input only one number
        zeroPressed = false;        //User is able to input zero at the beginning of the number again
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
