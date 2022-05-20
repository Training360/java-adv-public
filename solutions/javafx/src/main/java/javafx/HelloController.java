package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label label;

    @FXML
    private void buttonHasPressed() {
        label.setText("Hello World!");
    }
}
