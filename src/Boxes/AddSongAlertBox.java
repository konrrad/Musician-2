package Boxes;

import Music.BandSongs;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSongAlertBox extends  AlertBox {

    private final TextField titleField=new TextField();
    private final TextField numberField=new TextField();
    private final TextField setField=new TextField();
    private final Label titleLabel=new Label("Title: ");
    private final Label numberLabel=new Label("Number: ");
    private final Label setLabel=new Label("Set number: ");
    protected final Button saveButton=new Button("Save");
    protected final BandSongs bandSongs;


    public AddSongAlertBox(String title, BandSongs bandSongs) {
        super(title);
        this.bandSongs=bandSongs;
        this.layout.getChildren().addAll(titleLabel,titleField,numberLabel,
                numberField,setLabel,setField,saveButton);
        saveButton.setOnAction(e->{
            this.saveSong(e);
            this.window.close();
        });
    }

    //TODO data validation and re-labeling
    private boolean isDataValid()
    {
        return true;
    }

    private void saveSong(ActionEvent e) {
        if(isDataValid())
        {
            this.bandSongs.addSong(Integer.parseInt(this.setField.getText()),
                    Integer.parseInt(this.numberField.getText()),this.titleField.getText());
        }



    }
}
