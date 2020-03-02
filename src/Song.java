import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Observable;

public class Song extends TreeNode {
    StringProperty text;

    public Song(int number,String title, String text) {
        super(number);
        this.text = new SimpleStringProperty();
        this.text.setValue(text);
        this.title= new SimpleStringProperty();
        this.title.setValue(title);
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
        Text title=new Text(this.title.getValue());
        titleHBox.getStyleClass().add("titleHBox");
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        result.setTop(titleHBox);

        //TEXT VBOX
        VBox textVBox=new VBox();
        TextArea textArea=new TextArea(this.text.getValue());
        //Text text=new Text(this.text.getValue());
        textVBox.getStyleClass().add("textVBox");
        //textVBox.getChildren().add(text);
        textVBox.getChildren().add(textArea);
        result.setCenter(textVBox);
        textVBox.setAlignment(Pos.TOP_CENTER);

        //BUTTONS
        VBox left=new VBox();
        left.setSpacing(10);
        Button prev=new Button("Previous");
        prev.getStyleClass().add("button");
        Button listenToTempo=new Button("Tempo");
        listenToTempo.getStyleClass().add("button");
        left.getChildren().addAll(prev,listenToTempo);
        result.setLeft(left);

        VBox right=new VBox();
        right.setSpacing(10);
        Button next=new Button("next");
        next.getStyleClass().add("button");
        right.getChildren().addAll(next);
        result.setRight(right);
        return result;
    }
}
