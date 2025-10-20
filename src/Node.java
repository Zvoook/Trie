import java.util.HashMap;
import java.util.Map;

public class Node {
    Map<Character, Node> children;
    boolean isEnd;

    public Node(){
        children = new HashMap<>();
        isEnd = false;
    }
}