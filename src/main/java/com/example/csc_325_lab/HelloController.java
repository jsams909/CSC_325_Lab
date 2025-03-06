package com.example.csc_325_lab;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

public class HelloController {
    // instance variables for interacting with GUI components
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    // instance variables for managing
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    public void initialize() {
        // Create bidirectional bindings between TextFields and Sliders
        createBidirectionalBinding(redTextField, redSlider, 0);
        createBidirectionalBinding(greenTextField, greenSlider, 0);
        createBidirectionalBinding(blueTextField, blueSlider, 0);
        createBidirectionalBinding(alphaTextField, alphaSlider, 2);

        // listeners that set Rectangle's fill based on Slider changes
        redSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        red = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        greenSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        green = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        blueSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        blue = newValue.intValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
        alphaSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov,
                                        Number oldValue, Number newValue) {
                        alpha = newValue.doubleValue();
                        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                    }
                }
        );
    }

    // Helper method to create bidirectional bindings with format control
    private void createBidirectionalBinding(TextField textField, Slider slider, int decimalPlaces) {
        // Unbind any existing binding (important when changing from unidirectional to bidirectional)
        textField.textProperty().unbind();

        // Create string converter for bidirectional binding
        StringConverter<Number> converter = new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                if (number == null) {
                    return "";
                }
                if (decimalPlaces == 0) {
                    return String.format("%.0f", number.doubleValue());
                } else {
                    return String.format("%." + decimalPlaces + "f", number.doubleValue());
                }
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    // Return current value if parsing fails
                    return slider.getValue();
                }
            }
        };

        // Create bidirectional binding
        textField.textProperty().bindBidirectional(slider.valueProperty(), converter);
    }
}