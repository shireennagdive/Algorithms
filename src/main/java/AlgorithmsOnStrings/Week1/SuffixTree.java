package AlgorithmsOnStrings.Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SuffixTree {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    class Node {
        public static final int Letters = 5;
        public static final int NA = -1;
        public int next[];
        public int startPosition;

        Node() {
            next = new int[Letters];
            Arrays.fill(next, NA);
            startPosition = NA;
        }

    }

    int letterToIndex(char letter) {
        switch (letter) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            case '$':
                return 4;
            default:
                assert (false);
                return Node.NA;
        }
    }

    // Build a suffix tree of the string text and return a list
    // with all of the labels of its edges (the corresponding 
    // substrings of the text) in any order.
    public List<String> computeSuffixTreeEdges(String text) {
        List<String> result = new ArrayList<String>();
        // Implement this function yourself
        String text1;
        List<Node> suffixTree = new ArrayList<>();
        Node root = new Node();
        suffixTree.add(root);
        for (int i = 0; i < text.length(); i++) {
            text1 = text.substring(i);
            buildSuffixTree(text1, i, suffixTree);
        }
        return result;
    }

    int suffixTreeMatching(String text, List<Node> trie) {
        char currentSymbol;
        int currentIndex = 0;
        Node currentNode = trie.get(0);
        while (currentIndex < text.length()) {
            if (currentNode.startPosition != Node.NA) {
                return currentNode.startPosition;
            } else {
                currentSymbol = text.charAt(currentIndex);
                if (currentNode.next[letterToIndex((currentSymbol))] != -1) {
                    currentNode = trie.get(currentNode.next[letterToIndex((currentSymbol))]);
                    currentIndex++;
                } else {
                    return Node.NA;
                }
            }
        }
        return currentNode.startPosition;
    }

    void buildSuffixTree(String pattern, int startPosition, List<Node> suffixTree) {
        char currentSymbol;
        Node currentNode, newNode;
        int index;
        currentNode = suffixTree.get(0);
        for (int i = 0; i < pattern.length(); i++) {
            currentSymbol = pattern.charAt(i);
            index = letterToIndex(currentSymbol);
            if (currentNode.next[index] == -1) {
                newNode = new Node();
                suffixTree.add(newNode);
                currentNode.next[index] = suffixTree.size() - 1;
                currentNode = newNode;
                if (i == (pattern.length() - 1)) {
                    currentNode.startPosition = startPosition;
                }
            } else {
                currentNode = suffixTree.get(currentNode.next[index]);
            }
            if (i == (pattern.length() - 1)) {
                currentNode.startPosition = startPosition;
            }
        }
    }


    static public void main(String[] args) throws IOException {
        new SuffixTree().run();
    }

    public void print(List<String> x) {
        for (String a : x) {
            System.out.println(a);
        }
    }


    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        List<String> edges = computeSuffixTreeEdges(text);
        print(edges);
    }
}
