import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**************************************************************
 * Assignment 3 
 * 
 * Samridhi Girdhar
 * v1
 * 13/02/24
 ************************************************************ */
public class MyCalculator extends Application 
{
    private TextArea inputDisplay;
    private TextArea outputDisplay;
    private TextArea errorDisplay;
    private ToggleGroup gradeToggleGroup;

    private int grade = 4; // Default to grade 4 math

    private String operand1 = "";
    private String operand2 = "";
    private String operator = "";

    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setTitle("Samridhi's Integer Calculator");

        // Top brand label
        Label brandLabel = new Label("Calc-you-later");
        brandLabel.setStyle("-fx-font-size: 20px;");

        // Number display
        inputDisplay = createDisplay();
        outputDisplay = createDisplay();
        errorDisplay = createDisplay();
        VBox displayArea = new VBox(inputDisplay, outputDisplay, errorDisplay);
        displayArea.setAlignment(Pos.CENTER_RIGHT);

        // Grade toggle buttons
        RadioButton grade4Button = new RadioButton("Grade 4 Math");
        RadioButton grade5Button = new RadioButton("Grade 5 Math");
        RadioButton grade6Button = new RadioButton("Grade 6 Math");
        gradeToggleGroup = new ToggleGroup();
        grade4Button.setToggleGroup(gradeToggleGroup);
        grade5Button.setToggleGroup(gradeToggleGroup);
        grade6Button.setToggleGroup(gradeToggleGroup);
        
        HBox gradeToggleBox = new HBox(10, grade4Button, grade5Button, grade6Button);
        gradeToggleBox.setAlignment(Pos.CENTER);
        gradeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> 
        {
            if (newValue == grade4Button) 
            {
                grade = 4;
            } 
            else if (newValue == grade5Button) 
            {
                grade = 5;
            } 
            else if (newValue == grade6Button) 
            {
                grade = 6;
            }
        });
        gradeToggleGroup.selectToggle(grade4Button);

        // Number and operator buttons
        GridPane buttonsPane = new GridPane();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setHgap(5);
        buttonsPane.setVgap(5);

        String[] numbers = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
        for (int i = 0; i < numbers.length; i++) 
        {
            Button button = new Button(numbers[i]);
            button.setOnAction(e -> addToInput(((Button) e.getSource()).getText()));
            buttonsPane.add(button, i % 3, i / 3);
        }

        String[] operators = {"+", "-", "×", "÷", "="};
        for (int i = 0; i < operators.length; i++) 
        {
            Button button = new Button(operators[i]);
            button.setOnAction(e -> handleOperator(((Button) e.getSource()).getText()));
            buttonsPane.add(button, 3, i);
        }

        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clear());

        // Layout
        VBox root = new VBox(10, brandLabel, displayArea, gradeToggleBox, buttonsPane, clearButton);
        root.setAlignment(Pos.CENTER);
        
        // Set a fixed size for the root VBox
        root.setPrefSize(300, 400);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea createDisplay() 
    {
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setPrefRowCount(1);
        display.setPrefColumnCount(12);
        display.setStyle("-fx-font-size: 18px;");
        return display;
    }

    private void addToInput(String text) 
    {
        inputDisplay.appendText(text);
    }

    private void handleOperator(String text) 
    {
        if (text.equals("=")) 
        {
            calculate();
        } 
        else 
        {
            operator = text;
            operand1 = inputDisplay.getText();
            inputDisplay.clear();
        }
    }

    private void calculate() 
    {
        operand2 = inputDisplay.getText();
        try 
        {
            int num1 = Integer.parseInt(operand1);
            int num2 = Integer.parseInt(operand2);
            switch (operator) 
            {
                case "+":
                    outputDisplay.setText(Integer.toString(num1 + num2));
                    break;
                case "-":
                    if (grade == 4 && num1 < num2) 
                    {
                        errorDisplay.setText("Error: Operand 1 < Operand 2");
                    } 
                    else 
                    {
                        outputDisplay.setText(Integer.toString(num1 - num2));
                    }
                    break;
                case "×":
                    outputDisplay.setText(Integer.toString(num1 * num2));
                    break;
                case "÷":
                    if (grade == 4 && num1 % num2 != 0) 
                    {
                        errorDisplay.setText("Error: Remainder in division");
                    } 
                    else if (grade == 5) 
                    {
                        int quotient = num1 / num2;
                        int remainder = num1 % num2;
                        outputDisplay.setText(quotient + " R " + remainder);
                    } 
                    else if (grade == 6) 
                    {
                        double result = (double) num1 / num2;
                        outputDisplay.setText(String.format("%.4f", result));
                    }
                    break;
            }
        } 
        catch (NumberFormatException e) 
        {
            errorDisplay.setText("Error: Invalid Input");
        }
    }

    private void clear() 
    {
        operand1 = "";
        operand2 = "";
        operator = "";
        inputDisplay.clear();
        outputDisplay.clear();
        errorDisplay.clear();
    }
}



       
