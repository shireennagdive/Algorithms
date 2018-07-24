package AlgorithmsOnStrings.Week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Node {
    public static final int Letters = 4;
    public static final int NA = -1;
    public int next[];

    Node() {
        next = new int[Letters];
        Arrays.fill(next, NA);
    }

    boolean isLeaf(){
        for (int pos : next) {
            if (pos != -1) {
                return false;
            }
        }
        return true;
    }
}

public class TrieMatching implements Runnable {
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
            default:
                assert (false);
                return Node.NA;
        }
    }

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Node> trie = buildTrie(patterns);
        List<Integer> result = new ArrayList<>();

        int i = 0;
        String text1 = text;
        while (!text1.isEmpty()) {
            String match = prefixTrieMatching(text1, trie);
            if (patterns.contains(match)) {
                result.add(i);
            }
            i++;
            text1 = text.substring(i);
        }
        return result;
    }

    String prefixTrieMatching(String text, List<Node> trie) {
        char currentSymbol;
        int currentIndex = 0;
        Node currentNode = trie.get(0);
        String match = "";
        while (currentIndex < text.length()) {
            currentSymbol = text.charAt(currentIndex);
            if (currentNode.isLeaf()) {
                return match;
            } else {
                if (currentNode.next[letterToIndex((currentSymbol))] != -1) {
                    currentNode = trie.get(currentNode.next[letterToIndex((currentSymbol))]);
                    match = match.concat(String.valueOf(currentSymbol));
                    currentIndex++;
                } else {
                    return "";
                }
            }
        }
        return currentNode.isLeaf() ? match : "";
    }

    List<Node> buildTrie(List<String> patterns) {
        List<Node> trie = new ArrayList<>();
        char currentSymbol;
        Node currentNode, newNode;
        Node root = new Node();
        trie.add(root);
        int index;
        for (String pattern : patterns) {
            currentNode = root;
            for (int i = 0; i < pattern.length(); i++) {
                currentSymbol = pattern.charAt(i);
                index = letterToIndex(currentSymbol);
                if (currentNode.next[index] == -1) {
                    newNode = new Node();
                    trie.add(newNode);
                    currentNode.next[index] = trie.size() - 1;
                    currentNode = newNode;
                } else {
                    currentNode = trie.get(currentNode.next[index]);
                }
            }
        }
        return trie;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text = in.readLine();
            int n = Integer.parseInt(in.readLine());
            List<String> patterns = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                patterns.add(in.readLine());
            }

            List<Integer> ans = solve(text, n, patterns);

            for (int j = 0; j < ans.size(); j++) {
                System.out.print("" + ans.get(j));
                System.out.print(j + 1 < ans.size() ? " " : "\n");
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        new Thread(new TrieMatching()).start();
    }
}
