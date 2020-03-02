import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.util.List;

import java.util.stream.Collectors;

public class BandSongs {
    private final ObservableList<SetOfSongs> setsOfSongs=FXCollections.observableArrayList();
    public final TreeItem<TreeNode> root=new TreeItem<>();
    TreeView<TreeNode> treeView = new TreeView<>(root);
    private BorderPane borderPane;
    private final ListChangeListener<SetOfSongs> changeListener=change -> {
        while(change.next()) {


            if (change.wasRemoved()) {
                List<TreeItem<TreeNode>> toRemove = change.getRemoved().stream().map(
                        element -> new TreeItem<TreeNode>(element)
                ).collect(Collectors.toList());
                root.getChildren().removeAll(toRemove);
            } /*else if (change.wasAdded()) {
                List<TreeItem<TreeNode>> toAdd = change.getAddedSubList().stream().map(
                        element -> new TreeItem<TreeNode>(element)
                ).collect(Collectors.toList());
                root.getChildren().addAll(toAdd);
            }*/ else {
                this.root.getChildren().clear();
                this.root.getChildren().addAll(createTree());
            }
            System.out.println("dodano set");
            root.setExpanded(false);
            root.setExpanded(true);
        }

    };



    public BandSongs() {
        setsOfSongs.addListener(changeListener);
        //createMock();
        root.setExpanded(true);
        root.getChildren().addAll(createTree());
        treeView.getSelectionModel().selectedItemProperty().addListener((observableValue, treeNodeTreeItem, t1) -> {
            if(t1.getValue() instanceof Song) borderPane.setCenter(((Song) t1.getValue()).present());
        });

    }
    public void setBorderPane(BorderPane screen)
    {
        this.borderPane=screen;
    }

    private List<TreeItem<TreeNode>> createTree()
    {
        return setsOfSongs.stream().map(SetOfSongs::getSetTreeStructure).collect(Collectors.toList());
    }
    private void createMock()
    {
        Song song=new Song(1,"xax","text");
        SetOfSongs setOfSongs=new SetOfSongs(1);
        setOfSongs.addSong(song);
        this.addSet(setOfSongs);
    }
    public void addSet(SetOfSongs setOfSongs)
    {
        this.setsOfSongs.add(setOfSongs);
    }
    public void addSong(int numberOfSet, Song song)
    {
        Integer number= numberOfSet;
        List<SetOfSongs> addTo=this.setsOfSongs.stream().filter(e->e.number.getValue().equals(number)).collect(Collectors.toList());
        addTo.forEach(e->e.addSong(song));
    }

}

