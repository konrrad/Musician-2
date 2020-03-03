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
    private final static Transposer transposer=new Transposer();

    public Song(int number,String title, String text) {
        super(number);
        this.text = new SimpleStringProperty();
        this.text.setValue(text);
        this.title= new SimpleStringProperty();
        this.title.setValue(title);
        this.chords=new SimpleStringProperty();
        this.chords.setValue("");
    }

    @Override
    protected Object computeValue() {
        return this.title;
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
        HBox titleHBox=new HBox();
        TextArea titleTextArea=new TextArea(this.title.getValue());
        titleTextArea.setPrefHeight(100);
        titleTextArea.setWrapText(true);
        titleTextArea.getStyleClass().add("titleTextArea");
        //Text title=new Text(this.title.getValue());
        titleHBox.getStyleClass().add("titleHBox");
        titleHBox.getChildren().add(titleTextArea);
        titleHBox.setAlignment(Pos.CENTER);
        result.setTop(titleHBox);


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
        //Text text=new Text(this.text.getValue());
        textVBox.getStyleClass().add("textVBox");
        //textVBox.getChildren().add(text);
        textVBox.getChildren().addAll(chordsTextArea,textArea);
        result.setCenter(textVBox);
        textVBox.setAlignment(Pos.TOP_CENTER);




        //BUTTONS
            //LEFT
        VBox left=new VBox();
        left.setSpacing(100);
        Button prev=new Button("Back");
        prev.getStyleClass().add("button");
        Button listenToTempo=new Button("Tempo");
        listenToTempo.getStyleClass().add("button");
        left.getChildren().addAll(prev,listenToTempo);
        result.setLeft(left);
            //RIGHT
        VBox right=new VBox();
        right.setSpacing(20);
        Button transUpButton=new Button("+1");
        Button transDownButton=new Button("-1");
        transUpButton.setOnAction(e-> {
            this.chords.setValue(transposer.transpose(this.chords.getValue(),true));
            chordsTextArea.setText(this.chords.getValue());
        });
        transDownButton.setOnAction(e-> {
            this.chords.setValue(transposer.transpose(this.chords.getValue(),false));
            chordsTextArea.setText(this.chords.getValue());
        });
        Button next=new Button("Next");
        Button save=new Button("Save");
        save.getStyleClass().add("button");
        save.setOnAction(e->{
            this.text.setValue(textArea.getText());
            this.title.setValue(titleTextArea.getText());
            this.chords.setValue(chordsTextArea.getText());
        });
        next.getStyleClass().add("button");
        right.getChildren().addAll(next,save,transUpButton, transDownButton);
        result.setRight(right);


        BorderPane.setMargin(right, new Insets(10));
        BorderPane.setMargin(left, new Insets(10));
        return result;
    }
}
