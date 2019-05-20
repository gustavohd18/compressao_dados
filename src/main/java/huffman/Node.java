package huffman;

public class Node implements Comparable<Node> {
    private final char ch;
    private final int freq;
    private final Node left, right;

    Node(char ch, int freq, Node left, Node right) {
        this.ch    = ch;
        this.freq  = freq;
        this.left  = left;
        this.right = right;
    }

    // is the node a leaf node?
    private boolean isLeaf() {
        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
        return (left == null) && (right == null);
    }

    // compare, based on frequency
    public int compareTo(Node that) {
        return this.freq - that.freq;
    }
}