package Boxes;

import javafx.scene.text.Text;

public class BadInputAlertBox extends AlertBox {
    public BadInputAlertBox(String message) {
        super("Bad input.");
        this.window.setMinHeight(300);
        Text badInput=new Text(message);
        this.layout.getChildren().add(badInput);
    }
}
