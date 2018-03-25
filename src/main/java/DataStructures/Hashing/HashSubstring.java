package DataStructures.Hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {


    private static FastScanner in;
    private static PrintWriter out;
    private static int prime = 1000000007;
    private static int random = (int) (Math.random() * (prime)) + 1;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static long generatePolyHash(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * random + s.charAt(i)) % prime;
        return hash;
    }

    private static long[] preComputeHashes(Data input) {
        int t = input.text.length(), p = input.pattern.length();
        long hashes[] = new long[t - p + 1];
        long hash;
        hashes[t - p] = generatePolyHash(input.text.substring(t - p));
        long y = 1;
        for (int i = 0; i < p; i++) {
            y = (y * random) % prime;
        }
        for (int i = t - p - 1; i >= 0; i--) {
            hash = hashes[i + 1] * random + input.text.charAt(i) - y * input.text.charAt(i + p);
            while (hash < 0) {
                hash += prime;
            }
            hashes[i] = hash % prime;
        }
        return hashes;
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length(), i;
        List<Integer> occurrences = new ArrayList<Integer>();
        long patternHash = generatePolyHash(input.pattern);
        long hashes[] = preComputeHashes(input);
        for (i = 0; i + m <= n; i++) {
            if (patternHash == hashes[i]) {
                if (s.equals(t.substring(i, i + m))) {
                    occurrences.add(i);
                }
            }
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

