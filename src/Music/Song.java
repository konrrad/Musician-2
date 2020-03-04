package Music;

import Boxes.AlertBox;
import Boxes.BadInputAlertBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Song extends TreeNode {
    private String text;
    private String chords;
    private Integer tempo;
    private final static Transposer transposer=new Transposer();
    private final static Metronome metronome=new Metronome();
    private SetOfSongs set;

    public Song(int number,String title, SetOfSongs set) {
        super(number);
        this.text=("");
        this.title=title;
        this.chords=("");
        this.tempo=60;
        this.set=set;
    }

    public Song(int number,String title,int tempo,String text,String chords)
    {
        super(number);
        this.text=text;
        this.title=title;
        this.chords=chords;
        this.tempo=tempo;

    }

    public void setSet(SetOfSongs set) {
        this.set=set;
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
        return this.number+". "+this.title;
    }

    public Node present()
    {

        //GENERAL
        BorderPane result=new BorderPane();

        result.getStylesheets().add("CSSFILES/SongStyles.css");
        result.getStyleClass().add("songPresentation");


        //TITLE HBOX
        VBox titleHBox=new VBox();
        titleHBox.setSpacing(10);
        TextArea titleTextArea=new TextArea(this.title);
        titleTextArea.setPromptText("TITLE");
        titleTextArea.setPrefHeight(100);
        titleTextArea.setWrapText(true);
        titleTextArea.getStyleClass().add("titleTextArea");
        titleHBox.getStyleClass().add("titleHBox");
        titleHBox.getChildren().add(titleTextArea);
        titleHBox.setAlignment(Pos.CENTER);
        result.setTop(titleHBox);

        //TEMPO
        TextArea tempoTextArea=new TextArea(this.tempo.toString());
        tempoTextArea.setPromptText("TEMPO");
        HBox tempoHBox=new HBox(tempoTextArea);
        titleHBox.getChildren().add(tempoHBox);
        tempoTextArea.setWrapText(true);
        tempoTextArea.getStyleClass().add("tempoTextArea");
        tempoTextArea.setMaxSize(30,15);
        tempoHBox.setAlignment(Pos.CENTER_RIGHT);


        //CHORDS
        TextArea chordsTextArea=new TextArea();
        chordsTextArea.setPromptText("CHORDS");
        chordsTextArea.setText(this.chords);
        chordsTextArea.setWrapText(true);

        //TEXT VBOX
        VBox textVBox=new VBox();
        textVBox.setSpacing(20);
        TextArea textArea=new TextArea(this.text);
        textArea.setPromptText("TEXT");
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
                //PREV
        Button prev=new Button("Back");
        prev.getStyleClass().addAll("button","button-left");

                //TEMPO
        Button listenToTempo=new Button("Tempo");
        listenToTempo.getStyleClass().addAll("button","button-left");
        listenToTempo.setOnAction(e->metronome.play(this.tempo));

                //DELETE
        Button deleteButton=new Button("Delete");
        deleteButton.getStyleClass().addAll("button","button-left","delete-button");
        deleteButton.setAlignment(Pos.BOTTOM_LEFT);
        deleteButton.setOnAction(e->{
            this.set.deleteSong(this);
            result.setVisible(false);
        });

        left.getChildren().addAll(prev,listenToTempo,deleteButton);
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
                this.chords=transposer.transpose(this.chords,true);
                chordsTextArea.setText(this.chords);
            }
            catch (RuntimeException ex)
            {
                callBadInputAlert("Bad chords input.").display();
            }
        });
        transDownButton.setOnAction(e-> {
            try {
                this.chords=transposer.transpose(this.chords,false);
                chordsTextArea.setText(this.chords);
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
            this.text=textArea.getText();
            this.title=titleTextArea.getText();
            this.chords=chordsTextArea.getText();
            try
            {
                this.tempo=Integer.parseInt(tempoTextArea.getText());
            }
            catch (NumberFormatException ex)
            {
                this.callBadInputAlert("Bad tempo input.").display();
            }
        });
        next.getStyleClass().add("button");
        right.getChildren().addAll(next,save,transUpButton, transDownButton);
        result.setRight(right);


        BorderPane.setMargin(right, new Insets(10));
        BorderPane.setMargin(left, new Insets(10));
        return result;
    }
    public SongJSON toJSON()
    {
        return new SongJSON(this.title,this.number,this.text,this.chords,this.tempo);
    }
}
