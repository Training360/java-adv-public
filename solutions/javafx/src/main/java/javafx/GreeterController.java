package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GreeterController {

    @FXML
    private TextField textField;

    @FXML
    private Label label;

    @FXML
    private void buttonHasPressed() {
        label.setText(new Greeter().sayHello(textField.getText()));
    }
}
