package Music;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import java.util.List;
import java.util.stream.Collectors;

public class BandSongs {
    private ObservableList<SetOfSongs> setsOfSongs=FXCollections.observableArrayList();
    private final TreeItem<TreeNode> root=new TreeItem<>();
    public final TreeView<TreeNode> treeView = new TreeView<>(root);
    private BorderPane borderPane;
    private Node mainScreen;
    private final ListChangeListener<SetOfSongs> changeListener=change -> {
        while(change.next()) {
            //TODO better way to delete sets???
//            if (change.wasRemoved()) {
//                List<TreeItem<Music.TreeNode>> toRemove = change.getRemoved().stream().map(
//                        element -> new TreeItem<Music.TreeNode>()
//                ).collect(Collectors.toList());
//                System.out.println(root.getChildren().removeAll(change.getRemoved()));
//                //root.getChildren().removeAll(change.getRemoved());
//                root.setExpanded(false);
//                System.out.println("usunieto set");
//            }
            //else {
            if(change.wasAdded()||change.wasRemoved())
            {
                this.root.getChildren().clear();
                this.root.getChildren().addAll(createTree());
                if(borderPane!=null)
                    this.borderPane.setCenter(mainScreen);

            }

            //}
        }

    };



    public BandSongs() {
        this.loadData();
        setsOfSongs.addListener(changeListener);
        root.setExpanded(true);
        root.getChildren().addAll(createTree());
        treeView.getSelectionModel().selectedItemProperty().addListener((observableValue, treeNodeTreeItem, t1) -> {
            if(t1!=null&&t1.getValue() instanceof Song) borderPane.setCenter(((Song) t1.getValue()).present());
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
        this.mainScreen=screen.getCenter();
    }

    private List<TreeItem<TreeNode>> createTree()
    {
        return setsOfSongs.stream().map(SetOfSongs::getSetTreeStructure).collect(Collectors.toList());
    }

    public void saveData()
    {
        List<SetOfSongsJSON> sets=this.setsOfSongs.stream().map(SetOfSongs::toJSON).collect(Collectors.toList());
        DataSaver dataSaver=new DataSaver();
        dataSaver.save(sets);
    }

    public void loadData()
    {
        DataSaver dataSaver=new DataSaver();
        this.setsOfSongs=dataSaver.load();
    }

    public void addSet(SetOfSongs setOfSongs)
    {
        this.setsOfSongs.add(setOfSongs);
    }

    public void addSong(int numberOfSet, int numberOfSong, String title)
    {
        List<SetOfSongs> addTo=this.setsOfSongs.stream().filter(e->e.number==numberOfSet).collect(Collectors.toList());
        addTo.forEach(e->e.addSong(new Song(numberOfSong,title,e)));
    }

    private void deleteSet(SetOfSongs set)
    {
        this.setsOfSongs.remove(set);
    }

}

