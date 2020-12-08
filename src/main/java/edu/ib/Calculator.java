package edu.ib;

public class Calculator {

    private double oldNumber = Double.NaN;
    private double newNumber = 0;
    private String operation="";

    public Calculator() {
    }

    public void setOperation(String operation) {this.operation = operation;}


    public void setNumber(double x){
        if(Double.isNaN(oldNumber))
            oldNumber = x;
        else{
            newNumber = x;
        }
    }


    public double performOperation(boolean percents){
        double result = 0;
        if(!percents){
            switch(operation){
                case "sum"      -> result = oldNumber + newNumber;
                case "subtract" -> result = oldNumber - newNumber;
                case "multiply" -> result = oldNumber * newNumber;
                case "divide"   -> result = oldNumber / newNumber;
            }
        } else {
            switch(operation){
                case "sum"      -> result = oldNumber + newNumber/100 * oldNumber;
                case "subtract" -> result = oldNumber - newNumber/100 *oldNumber;
                case "multiply" -> result = oldNumber * newNumber/100;
                case "divide"   -> result = oldNumber / newNumber/100;
            }
        }
        oldNumber = result;
        return result;
    }


    public void reset() {
        oldNumber = Double.NaN;
        newNumber = 0;
        operation="";
    }
}
