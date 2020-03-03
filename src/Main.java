import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        BandSongs bs=new BandSongs();
        BorderPane borderPane=new BorderPane();
        TreeView<TreeNode> tv=bs.treeView;
        borderPane.setLeft(tv);
        SetOfSongs setOfSongs=new SetOfSongs(1);
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
        VBox mainScreen=new VBox();
        mainScreen.setAlignment(Pos.CENTER);
        Text welcomeText= new Text("Musician 2");
        welcomeText.getStyleClass().add("welcome-text");
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
