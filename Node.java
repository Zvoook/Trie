public class Node {
    public Node[] children;
    public boolean isEnd;

    public Node() {
        children = new Node[26];
        isEnd = false;
    }
}