public class Node {
    int index;
    int value;
    boolean checked;
    Node parent;
    public Node(int givenIndex, int givenValue)
    {
        index = givenIndex;
        value = givenValue;
        checked = false;
        parent = null;
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
