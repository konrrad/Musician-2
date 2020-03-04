package Music;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SetOfSongs extends TreeNode {
    private ObservableList<Song> songs;
    private TreeItem<TreeNode>  setTreeStructure;
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


    public SetOfSongs(int number)
    {
        super(number);
        this.title="Set "+number;

    }

    public void setSongs(ObservableList<Song> songs)
    {
        this.songs=songs;
        this.songs.addListener(changeListener);


    }

    @Override
    protected Object computeValue() {
        return this.title;
    }

    public String toString()
    {
        return this.title;
    }
    public List<TreeItem<TreeNode>> tree()
    {
        return songs.stream().map((Function<Song, TreeItem<TreeNode>>) TreeItem::new).collect(Collectors.toList());
    }

    public TreeItem<TreeNode> getSetTreeStructure() {
        setTreeStructure=new TreeItem<>(this);
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
    public SetOfSongsJSON toJSON()
    {
        List<SongJSON> mapped=songs.stream().map(Song::toJSON).collect(Collectors.toList());
        return new SetOfSongsJSON(this.title, this.number,mapped);
    }
}
