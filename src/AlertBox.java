import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    protected final Stage window=new Stage();
    protected final VBox layout=new VBox();
    Scene scene =new Scene(layout);
    public AlertBox(String title) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        layout.setAlignment(Pos.CENTER);
    }

    public void display()
    {
        window.setScene(scene);
        window.showAndWait();
    }
}