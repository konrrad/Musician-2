package Music;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public abstract class TreeNode extends ObjectBinding {
    protected StringProperty title;
    protected IntegerProperty number;

    public TreeNode(int number) {
        this.number=new SimpleIntegerProperty();
        this.number.setValue(number);
        this.title=new SimpleStringProperty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return title.equals(treeNode.title) &&
                number.equals(treeNode.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, number);
    }
}
