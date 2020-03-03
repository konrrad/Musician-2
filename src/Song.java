import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Song extends TreeNode {
    StringProperty text;
    StringProperty chords;
    IntegerProperty tempo;
    private final static Transposer transposer=new Transposer();
    private final static Metronome metronome=new Metronome();

    public Song(int number,String title, String text) {
        super(number);
        this.text = new SimpleStringProperty();
        this.text.setValue(text);
        this.title= new SimpleStringProperty();
        this.title.setValue(title);
        this.chords=new SimpleStringProperty();
        this.chords.setValue("");
        this.tempo= new SimpleIntegerProperty();
        this.tempo.setValue(0);
    }

    @Override
    protected Object computeValue() {
        return this.title;
    }

    private AlertBox callBadInputAlert(String message)
    {
        return new BadInputAlertBox(message);
    }

    public String toString()
    {
        return this.number.getValue()+". "+this.title.getValue();
    }

    public Node present()
    {

        //GENERAL
        BorderPane result=new BorderPane();

        result.getStylesheets().add("Style.css");
        result.getStyleClass().add("songPresentation");


        //TITLE HBOX
        VBox titleHBox=new VBox();
        TextArea titleTextArea=new TextArea(this.title.getValue());
        titleTextArea.setPrefHeight(100);
        titleTextArea.setWrapText(true);
        titleTextArea.getStyleClass().add("titleTextArea");
        titleHBox.getStyleClass().add("titleHBox");
        titleHBox.getChildren().add(titleTextArea);
        titleHBox.setAlignment(Pos.CENTER);
        result.setTop(titleHBox);

        //TEMPO
        TextArea tempoTextArea=new TextArea(this.tempo.getValue().toString());
        HBox tempoHBox=new HBox(tempoTextArea);
        titleHBox.getChildren().add(tempoHBox);
        tempoTextArea.setWrapText(true);
        tempoTextArea.getStyleClass().add("tempoTextArea");
        tempoTextArea.setMaxSize(30,15);
        tempoHBox.setAlignment(Pos.CENTER_RIGHT);


        //CHORDS
        VBox chordsVBox=new VBox();
        TextArea chordsTextArea=new TextArea();
        chordsTextArea.setText(this.chords.getValue());
        chordsTextArea.setWrapText(true);
        chordsVBox.getChildren().add(chordsTextArea);

        //TEXT VBOX
        VBox textVBox=new VBox();
        TextArea textArea=new TextArea(this.text.getValue());
        textArea.setWrapText(true);
        textArea.setPrefHeight(500);
        textVBox.getStyleClass().add("textVBox");
        textVBox.getChildren().addAll(chordsTextArea,textArea);
        result.setCenter(textVBox);
        textVBox.setAlignment(Pos.TOP_CENTER);




        //BUTTONS
            //LEFT
        VBox left=new VBox();
        left.setSpacing(100);
        Button prev=new Button("Back");
        prev.getStyleClass().addAll("button","button-left");
        Button listenToTempo=new Button("Tempo");
        listenToTempo.getStyleClass().addAll("button","button-left");
        listenToTempo.setOnAction(e->metronome.play(this.tempo.getValue()));
        left.getChildren().addAll(prev,listenToTempo);
        left.setAlignment(Pos.TOP_RIGHT);
        result.setLeft(left);
            //RIGHT
        VBox right=new VBox();
        right.setSpacing(20);
        Button transUpButton=new Button("+1");
        Button transDownButton=new Button("-1");
        transUpButton.setOnAction(e-> {
            try
            {
                this.chords.setValue(transposer.transpose(this.chords.getValue(),true));
                chordsTextArea.setText(this.chords.getValue());
            }
            catch (RuntimeException ex)
            {
                callBadInputAlert("Bad chords input.").display();
            }
        });
        transDownButton.setOnAction(e-> {
            try {
                this.chords.setValue(transposer.transpose(this.chords.getValue(),false));
                chordsTextArea.setText(this.chords.getValue());
            }
            catch (RuntimeException ex)
            {
                callBadInputAlert("Bad chords input.").display();
            }

        });
        Button next=new Button("Next");
        Button save=new Button("Save");
        save.getStyleClass().add("button");
        save.setOnAction(e->{
            this.text.setValue(textArea.getText());
            this.title.setValue(titleTextArea.getText());
            this.chords.setValue(chordsTextArea.getText());
            try
            {
                this.tempo.setValue(Integer.parseInt(tempoTextArea.getText()));
            }
            catch (NumberFormatException ex)
            {
                this.callBadInputAlert("Bad tempo input.").display();
                System.out.println(this.tempo.getValue().toString());
            }
        });
        next.getStyleClass().add("button");
        right.getChildren().addAll(next,save,transUpButton, transDownButton);
        result.setRight(right);


        BorderPane.setMargin(right, new Insets(10));
        BorderPane.setMargin(left, new Insets(10));
        return result;
    }
}
