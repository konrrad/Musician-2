package Music;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SetOfSongs extends TreeNode {
    private ObservableList<Song> songs= FXCollections.observableArrayList();
    private TreeItem<TreeNode>  setTreeStructure=new TreeItem<>(this);
    private ListChangeListener<Song> changeListener= change -> {
        while (change.next())
        {
            if(change.wasRemoved())
            {
                List<TreeItem<TreeNode>> toRemove = change.getRemoved().stream().map(
                        element -> new TreeItem<TreeNode>(element)
                ).collect(Collectors.toList());
                setTreeStructure.getChildren().removeAll(toRemove);
            }
            else
            {
                this.setTreeStructure.getChildren().clear();
                this.setTreeStructure.getChildren().addAll(this.tree());
            }
        }
        setTreeStructure.getChildren().clear();
        setTreeStructure.getChildren().addAll(tree());
    };

    public SetOfSongs(int number) {
        super(number);
        this.title.setValue("Set "+number);
        this.songs.addListener(changeListener);

    }

    @Override
    protected Object computeValue() {
        return this.title;
    }

    public String toString()
    {
        return this.title.getValue();
    }
    public List<TreeItem<TreeNode>> tree()
    {
        return songs.stream().map((Function<Song, TreeItem<TreeNode>>) TreeItem::new).collect(Collectors.toList());
    }

    public TreeItem<TreeNode> getSetTreeStructure() {
        return setTreeStructure;
    }
    public void addSong(Song s)
    {
        this.songs.add(s);
    }
    public void deleteSong(Song s)
    {
        this.songs.remove(s);
    }
}
