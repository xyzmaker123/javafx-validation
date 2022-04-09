package com.javafxvalidation.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// TODO: check possibility to extend TextField instead of VBox
public class BasicTextFieldWrapper extends VBox {
    // FXML
    protected final Label label;
    protected final TextField input;
    protected final Label errorInfo;
    
    // Model
    protected final StringProperty errorProperty = new SimpleStringProperty(null);
    
    
    public BasicTextFieldWrapper(String labelText) {
        super();
        setMaxWidth(300);
        label = new Label(labelText);
        input = new TextField();
        errorInfo = new Label();
        
        getChildren().addAll(label, input, errorInfo);
        
        addBindings();
    }

    public StringProperty textProperty() {
        return this.input.textProperty();
    }
    
    public StringProperty errorProperty() {
        return this.errorProperty;
    }
    
    private void addBindings() {
        errorInfo.visibleProperty().bind(Bindings.isNotNull(errorProperty));
        errorInfo.textProperty().bind(errorProperty);
    }
}
