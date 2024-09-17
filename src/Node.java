public class Node {
    int index;
    int value;
    boolean checked;
    Node parent;

    public Node(){}
    public Node(int givenIndex, int givenValue)
    {
        index = givenIndex;
        value = givenValue;
        checked = false;
        parent = null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "Node=" + (char) (index+65) +
                ", value=" + value +
                ", checked=" + checked +
                ", parent=" + (parent!=null ? (char) (parent.index+65) : parent) +
                '}';
    }
}