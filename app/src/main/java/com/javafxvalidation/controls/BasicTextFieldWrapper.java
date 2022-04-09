package com.javafxvalidation.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

// TODO: check possibility to extend TextField instead of VBox
public class BasicTextFieldWrapper extends VBox {
    // Internal config, will be part of API in future
    private final boolean SHOW_ERROR_ON_BLUR = true;
    
    // FXML
    protected final Label label;
    protected final TextField input;
    protected final Label errorInfo;
    
    // Model
    protected final StringProperty errorProperty = new SimpleStringProperty(null);
    protected final BooleanProperty touchedProperty = new SimpleBooleanProperty(false);
    
    public BasicTextFieldWrapper(String labelText) {
        super();
        setMaxWidth(300);
        label = new Label(labelText);
        input = new TextField();
        errorInfo = new Label();
        
        getChildren().addAll(label, input, errorInfo);
        
        addBindings();

        input.focusedProperty().addListener((e, o, n) -> {
            if (!n && !touchedProperty.get()) {
                touchedProperty.set(true);
            }
        });
    }

    public StringProperty textProperty() {
        return this.input.textProperty();
    }
    
    public StringProperty errorProperty() {
        return this.errorProperty;
    }
    
    private void addBindings() {
        errorInfo.textProperty().bind(errorProperty);
        
        if (SHOW_ERROR_ON_BLUR) {
            errorInfo.visibleProperty().bind(Bindings.isNotNull(errorProperty).and(touchedProperty));
        } else {
            errorInfo.visibleProperty().bind(Bindings.isNotNull(errorProperty));
        }
    }
}
