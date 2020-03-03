import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSongAlertBox extends  AlertBox {

    private TextField titleField=new TextField();
    private TextField numberField=new TextField();
    private TextField setField=new TextField();
    private TextField textField=new TextField();
    private Label titleLabel=new Label("Title: ");
    private Label numberLabel=new Label("Number: ");
    private Label setLabel=new Label("Set number: ");
    private Label textLabel=new Label("Text: ");
    protected Button saveButton=new Button("Save");
    protected BandSongs bandSongs;


    public AddSongAlertBox(String title,BandSongs bandSongs) {
        super(title);
        this.bandSongs=bandSongs;
        this.layout.getChildren().addAll(titleLabel,titleField,numberLabel,
                numberField,setLabel,setField,textLabel,textField,saveButton);
        saveButton.setOnAction(e->{
            this.saveSong(e);
            this.window.close();
        });
    }

    private boolean isDataValid()
    {
        return true;
    }

    private void saveSong(ActionEvent e) {
        if(isDataValid())
        {
            this.bandSongs.addSong(Integer.parseInt(this.setField.getText()),
                    new Song(Integer.parseInt(this.numberField.getText()),this.titleField.getText(),
                    this.textField.getText()));
        }



    }
}
