package Boxes;
import Music.BandSongs;
import Music.SetOfSongs;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSetAlertBox extends AlertBox {
    private final TextField numberField=new TextField();
    private final Label numberLabel=new Label("Number");
    protected final Button saveButton=new Button("Save");
    protected final BandSongs bandSongs;

    public AddSetAlertBox(String title, BandSongs bandSongs) {
        super(title);
        this.bandSongs=bandSongs;
        this.layout.getChildren().addAll(numberLabel,numberField,saveButton);
        saveButton.setOnAction(e->{
            this.saveSet(e);
            this.window.close();
        });
    }

    private boolean isDataValid()
    {
        return true;
    }


    private void saveSet(ActionEvent actionEvent) {
        if(isDataValid())
        {
            this.bandSongs.addSet(new SetOfSongs(Integer.parseInt(this.numberField.getText())));
        }

    }


}
