import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSetAlertBox extends AlertBox {
    //private TextField titleField=new TextField();
    private TextField numberField=new TextField();
    //private Label titleLabel=new Label("Title");
    private Label numberLabel=new Label("Number");
    protected Button saveButton=new Button("Save");
    protected BandSongs bandSongs;
    public AddSetAlertBox(String title,BandSongs bandSongs) {
        super(title);
        this.bandSongs=bandSongs;
        this.layout.getChildren().addAll(numberLabel,numberField,saveButton);
        saveButton.setOnAction(e->{
            this.saveSet(e);
            this.window.close();
        });
    }

    private void saveSet(ActionEvent actionEvent) {
        if(isDataValid())
        {
            this.bandSongs.addSet(new SetOfSongs(Integer.parseInt(this.numberField.getText())));
        }

    }


}
