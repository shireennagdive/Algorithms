package DataStructures.BinaryTrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class tree_orders {
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            Stack<Integer> s = new Stack<>();
            int i = 0, index;
            while (i != -1) {
                s.push(i);
                i = left[i];
            }

            while (s.size() > 0) {
                index = s.pop();
                result.add(key[index]);
                if (right[index] != -1) {
                    index = right[index];
                    while (index != -1) {
                        s.push(index);
                        index = left[index];
                    }
                }
            }
            return result;
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int tempIndex;
            Stack<Integer> s = new Stack<>();
            s.push(0);
            while (!s.isEmpty()) {
                tempIndex = s.pop();
                result.add(key[tempIndex]);
                if (right[tempIndex] != -1) {
                    s.push(right[tempIndex]);
                }
                if (left[tempIndex] != -1) {
                    s.push(left[tempIndex]);
                }

            }
            return result;
        }

        public List<Integer> postOrder() { //LR ROOT
            ArrayList<Integer> result = new ArrayList<>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            Stack<Integer> s = new Stack<>();
            int root = 0, child;
            //4 5 2 3 1
            if (right[root] != -1) {
                s.push(right[root]);
            }
            s.push(root);
            root = left[root];

            while (s.size() > 0) {
                while (root != -1) {
                    if (right[root] != -1) {
                        s.push(right[root]);
                    }
                    s.push(root);
                    root = left[root];
                }

                root = s.pop();

                if (right[root] != -1 && !s.isEmpty() && s.peek() == right[root]) {
                    child = s.pop();
                    s.push(root);
                    root = child;
                } else {
                    result.add(key[root]);
                    root = -1;
                }
            }
            return result;
        }

    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
