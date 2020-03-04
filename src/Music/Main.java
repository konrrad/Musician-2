package Music;

import Boxes.AddSetAlertBox;
import Boxes.AddSongAlertBox;
import Boxes.AlertBox;
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
        final String title="Musician 2";
        BandSongs bandSongs=new BandSongs();
        BorderPane borderPane=new BorderPane();
        TreeView<TreeNode> treeView=bandSongs.treeView;
        borderPane.setLeft(treeView);

        //menu
        MenuBar menuBar=new MenuBar();
        Menu fileMenu=new Menu("File");
        MenuItem saveItem=new MenuItem("Save Work");
        MenuItem loadItem=new MenuItem("Load data");
        fileMenu.getItems().addAll(saveItem,loadItem);
        loadItem.setOnAction(e->bandSongs.loadData());
        saveItem.setOnAction(e->bandSongs.saveData());
        Menu addMenu=new Menu("Add");
        MenuItem addSetMenuItem=new MenuItem("Add Set");
        addSetMenuItem.setOnAction(e->{
            AlertBox ab=new AddSetAlertBox("Add Set",bandSongs);
            ab.display();
        });

        MenuItem addSongMenuItem=new MenuItem("Add Song");
        addSongMenuItem.setOnAction(e->{
                    AlertBox ab=new AddSongAlertBox("Add Song", bandSongs);
                    ab.display();
                }
        );
        addMenu.getItems().addAll(addSetMenuItem,addSongMenuItem);
        menuBar.getMenus().addAll(fileMenu,addMenu);
        borderPane.setTop(menuBar);
        VBox mainScreen=new VBox();
        mainScreen.setAlignment(Pos.CENTER);
        Text welcomeText= new Text(title);
        welcomeText.getStyleClass().add("welcome-text");
        mainScreen.getChildren().add(welcomeText);
        borderPane.setCenter(mainScreen);
        bandSongs.setBorderPane(borderPane);
        BorderPane.setMargin(mainScreen,new Insets(10));
        borderPane.setMinSize(1300,800);
        borderPane.getStylesheets().add("CSSFILES/borderPaneStyles.css");
        Scene scene=new Scene(borderPane);
        stage.setMinHeight(800);
        stage.setMinWidth(1300);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
