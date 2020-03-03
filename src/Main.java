import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        BandSongs bs=new BandSongs();
        BorderPane borderPane=new BorderPane();

        //VBox vb=new VBox();
        //borderPane.setLeft(vb);
        //TreeItem<TreeNode> root=new TreeItem<>();
        //SetOfSongs setOfSongs=new SetOfSongs(1);

//        TreeItem<TreeNode> b1=setOfSongs.getSetTreeStructure();
//        root.getChildren().add(b1);
        TreeView<TreeNode> tv=bs.treeView;
        borderPane.setLeft(tv);
//        Song song=new Song(2,"xxx","xxx");
//        Button b5=new Button("Dodaj");
//        b5.setOnAction(e->setOfSongs.songs.add(song));
//        Button b6=new Button("zmiana tytulu");
//        b6.setOnAction(e->{song.title.set("zmieniony");
//        b1.setExpanded(false); b1.setExpanded(true);});
        //vb.getChildren().addAll(tv);


        SetOfSongs setOfSongs=new SetOfSongs(1);
        //Song song=new Song(1,"xax",setOfSongs);
        bs.addSet(setOfSongs);
        bs.addSong(1,1,"xax");

        //menu
        MenuBar menuBar=new MenuBar();
        Menu fileMenu=new Menu("File");
        Menu addMenu=new Menu("Add");
        MenuItem addSetMenuItem=new MenuItem("Add Set");
        addSetMenuItem.setOnAction(e->{
            AlertBox ab=new AddSetAlertBox("Add Set",bs);
            ab.display();
        });

        MenuItem addSongMenuItem=new MenuItem("Add song");
        addSongMenuItem.setOnAction(e->{
                    AlertBox ab=new AddSongAlertBox("Add Song", bs);
                    ab.display();
                }
        );
        addMenu.getItems().addAll(addSetMenuItem,addSongMenuItem);
        menuBar.getMenus().addAll(fileMenu,addMenu);
        borderPane.setTop(menuBar);
        //vb.setMinWidth(200);
        //vb.setPrefHeight(800);
        VBox mainScreen=new VBox();
        mainScreen.setAlignment(Pos.CENTER);
//        Label welcome=new Label("Musician 2");
//        welcome.getStyleClass().add("welcome-text");
//        welcome.setStyle("-fx-font-size: 60px; -fx-color: red;");
//        welcome.setTextAlignment(TextAlignment.CENTER);
//        mainScreen.getChildren().addAll(welcome);
//        mainScreen.getStylesheets().add("mainScreenStyles.css");
        Text welcomeText= new Text("Musician 2");
        welcomeText.getStyleClass().add("welcome-text");
        //welcomeText.setStyle("-fx-fill: #20B2AA; -fx-font-size: 150px; ");
        //welcomeText.setFill(Color.AQUA);
        mainScreen.getChildren().add(welcomeText);
        borderPane.setCenter(mainScreen);
        bs.setBorderPane(borderPane);
        BorderPane.setMargin(mainScreen,new Insets(10));
        borderPane.setMinSize(1300,800);
        borderPane.getStylesheets().add("borderPaneStyles.css");
        Scene s=new Scene(borderPane);
        stage.setMinHeight(800);
        stage.setMinWidth(1300);
        stage.setScene(s);
        stage.show();
    }
}
