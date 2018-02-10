import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int i, minCount[] = new int[n + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (i = 2; i <= n; i++) {// 9 = 2
            int temp = minCount[i - 1];
            if (i % 3 == 0 && i % 2 == 0) {
                temp = Integer.min(temp, Integer.min(minCount[i / 3], minCount[i / 2]));
            } else if (i % 3 == 0 && i % 2 != 0) {
                temp = Integer.min(temp, minCount[i / 3]);
            } else if (i % 3 != 0 && i % 2 == 0) {
                temp = Integer.min(temp, minCount[i / 2]);
            }
            if (temp == minCount[i - 1]) {
                map.put(i, i - 1);
            } else if (temp == minCount[i / 3]) {
                map.put(i, i / 3);
            } else {
                map.put(i, i / 2);
            }
            minCount[i] = temp + 1;
        }
        i = n;
        sequence.add(n);
        while (i != 1) {
            sequence.add(map.get(i));
            i = map.get(i);
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

