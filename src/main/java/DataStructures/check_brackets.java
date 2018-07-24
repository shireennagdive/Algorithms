package DataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int flag = 0, pos = 0;
        for (int position = 0; position < text.length() && flag == 0; ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                opening_brackets_stack.push(new Bracket(next, position));
                pos = position + 1;
                // Process opening bracket, write your code here
            } else if (next == ')' || next == ']' || next == '}') {
                if (opening_brackets_stack.empty()) {
                    flag = 1;
                    pos = position + 1;
                } else {
                    Bracket value = opening_brackets_stack.pop();
                    if (!value.Match(next)) {
                        flag = 1;
                        pos = position + 1;
                    } else {
                        pos--;
                    }
                    // Process closing bracket, write your code here
                }
            }
        }
        if (flag == 1) {
            System.out.println(pos);
        } else {
            if (opening_brackets_stack.empty()) {
                System.out.println("Success");
            } else {
                System.out.println(pos);
            }
        }
        // Printing answer, write your code here
    }
}
