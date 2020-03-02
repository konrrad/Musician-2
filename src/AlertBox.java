import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    protected Stage window=new Stage();
    protected VBox layout=new VBox();
    public AlertBox(String title) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        layout.setAlignment(Pos.CENTER);

    }

    public void display()
    {
        Scene s=new Scene(layout);
        window.setScene(s);
        window.showAndWait();


    }
    protected boolean isDataValid() {
        return true;
    }
}