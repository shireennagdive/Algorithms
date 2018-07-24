package AlgorithmsOnStrings.Week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NodeExtended {
    public static final int Letters = 4;
    public static final int NA = -1;
    public int next[];
    public boolean patternEnd;

    NodeExtended() {
        next = new int[Letters];
        Arrays.fill(next, NA);
        patternEnd = false;
    }

}

public class TrieMatchingExtended implements Runnable {
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
                return NodeExtended.NA;
        }
    }

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<NodeExtended> trie = buildTrie(patterns);
        List<Integer> result = new ArrayList<>();

        int i = 0;
        String text1 = text;
        while (!text1.isEmpty()) {
            if (prefixTrieMatching(text1, trie)) {
                result.add(i);
            }
            i++;
            text1 = text.substring(i);
        }
        return result;
    }

    boolean prefixTrieMatching(String text, List<NodeExtended> trie) {
        char currentSymbol;
        int currentIndex = 0;
        NodeExtended currentNode = trie.get(0);
        while (currentIndex < text.length()) {
            if (currentNode.patternEnd) {
                return true;
            } else {
                currentSymbol = text.charAt(currentIndex);
                if (currentNode.next[letterToIndex((currentSymbol))] != -1) {
                    currentNode = trie.get(currentNode.next[letterToIndex((currentSymbol))]);
                    currentIndex++;
                } else {
                    return false;
                }
            }
        }
        return currentNode.patternEnd;
    }

    List<NodeExtended> buildTrie(List<String> patterns) {
        List<NodeExtended> trie = new ArrayList<>();
        char currentSymbol;
        NodeExtended currentNode, newNode;
        NodeExtended root = new NodeExtended();
        trie.add(root);
        int index;
        for (String pattern : patterns) {
            currentNode = root;
            for (int i = 0; i < pattern.length(); i++) {
                currentSymbol = pattern.charAt(i);
                index = letterToIndex(currentSymbol);
                if (currentNode.next[index] == -1) {
                    newNode = new NodeExtended();
                    trie.add(newNode);
                    currentNode.next[index] = trie.size() - 1;
                    currentNode = newNode;
                    if (i == (pattern.length() - 1)) {
                        currentNode.patternEnd = true;
                    }
                } else {
                    currentNode = trie.get(currentNode.next[index]);
                }
                if (i == (pattern.length() - 1)) {
                    currentNode.patternEnd = true;
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
        new Thread(new TrieMatchingExtended()).start();
    }
}
