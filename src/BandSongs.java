import com.sun.source.tree.Tree;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import java.util.List;

import java.util.stream.Collectors;

public class BandSongs {
    private final ObservableList<SetOfSongs> setsOfSongs=FXCollections.observableArrayList();
    public final TreeItem<TreeNode> root=new TreeItem<>();
    TreeView<TreeNode> treeView = new TreeView<>(root);
    private BorderPane borderPane;
    private final ListChangeListener<SetOfSongs> changeListener=change -> {
        while(change.next()) {
            //TODO better way to delete sets???
//            if (change.wasRemoved()) {
//                List<TreeItem<TreeNode>> toRemove = change.getRemoved().stream().map(
//                        element -> new TreeItem<TreeNode>()
//                ).collect(Collectors.toList());
//                System.out.println(root.getChildren().removeAll(change.getRemoved()));
//                //root.getChildren().removeAll(change.getRemoved());
//                root.setExpanded(false);
//                System.out.println("usunieto set");
//            }
            //else {
                this.root.getChildren().clear();
                this.root.getChildren().addAll(createTree());
            //}
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
        MenuItem deleteSetMenuItem=new MenuItem("Delete Set");
        deleteSetMenuItem.setOnAction(e->{
            TreeNode candidate=treeView.getSelectionModel().getSelectedItem().getValue();
            if(candidate instanceof SetOfSongs)
                 this.deleteSet((SetOfSongs)candidate);
        });
        treeView.setContextMenu(new ContextMenu(deleteSetMenuItem));

    }
    public void setBorderPane(BorderPane screen)
    {
        this.borderPane=screen;
    }

    private List<TreeItem<TreeNode>> createTree()
    {
        return setsOfSongs.stream().map(SetOfSongs::getSetTreeStructure).collect(Collectors.toList());
    }
//    private void createMock()
//    {
//        Song song=new Song(1,"xax",);
//        SetOfSongs setOfSongs=new SetOfSongs(1);
//        setOfSongs.addSong(song);
//        this.addSet(setOfSongs);
//    }
    public void addSet(SetOfSongs setOfSongs)
    {
        this.setsOfSongs.add(setOfSongs);
    }
    public void addSong(int numberOfSet, int numberOfSong, String title)
    {
        Integer number= numberOfSet;
        List<SetOfSongs> addTo=this.setsOfSongs.stream().filter(e->e.number.getValue().equals(number)).collect(Collectors.toList());
        addTo.forEach(e->e.addSong(new Song(numberOfSong,title,e)));
    }
    private void deleteSet(SetOfSongs set)
    {
        this.setsOfSongs.remove(set);
    }

}

