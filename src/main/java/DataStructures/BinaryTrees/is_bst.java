package DataStructures.BinaryTrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;
        Integer[] maxValArr;
        Integer[] minValArr;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            maxValArr = new Integer[nodes];
            minValArr = new Integer[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
                maxValArr[i] = null;
                minValArr[i] = null;
            }
        }

        boolean isBinarySearchTree() {
            int len = tree.length;
            if (len > 0) {
                getMax(0);
                getMin(0);
            }
            return checkBST(len == 0 ? -1 : 0);
        }

        boolean checkBST(int index) {
            if (index == -1)
                return true;
            int node = tree[index].key;
            int leftIndex = tree[index].left;
            int rightIndex = tree[index].right;

            if (leftIndex != -1 && maxValArr[leftIndex] > node) {
                return false;
            }
            if (rightIndex != -1 && minValArr[rightIndex] < node) {
                return false;
            }
            return !(!checkBST(leftIndex) || !checkBST(rightIndex));
        }

        public int getMax(int index) {
            if (maxValArr[index] == null) {
                int leftIndex = tree[index].left;
                int rightIndex = tree[index].right;
                if (leftIndex == -1 && rightIndex == -1) {
                    maxValArr[index] = tree[index].key;
                } else if (leftIndex == -1) {
                    maxValArr[index] = Math.max(tree[index].key, getMax(rightIndex));
                } else if (rightIndex == -1) {
                    maxValArr[index] = Math.max(tree[index].key, getMax(leftIndex));
                } else {
                    maxValArr[index] = Math.max(tree[index].key, Math.max(getMax(leftIndex), getMax(rightIndex)));
                }
            }
            return maxValArr[index];
        }

        public int getMin(int index) {
            if (minValArr[index] == null) {
                int leftIndex = tree[index].left;
                int rightIndex = tree[index].right;
                if (leftIndex == -1 && rightIndex == -1) {
                    minValArr[index] = tree[index].key;
                } else if (leftIndex == -1) {
                    minValArr[index] = Math.min(tree[index].key, getMin(rightIndex));
                } else if (rightIndex == -1) {
                    minValArr[index] = Math.min(tree[index].key, getMin(leftIndex));
                } else {
                    minValArr[index] = Math.min(tree[index].key, Math.min(getMin(leftIndex), getMin(rightIndex)));
                }
            }
            return minValArr[index];
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                is_bst node = new is_bst();
                node.run();
            } catch (IOException e) {
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}

