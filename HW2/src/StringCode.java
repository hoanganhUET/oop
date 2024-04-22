import java.util.HashSet;
import java.util.Scanner;

public class StringCode {
    public static void main(String[] str) {
        StringBlowup(str);
        maxRun(str);
        booleanStringIntersect(str);
    }
    public static void StringBlowup(String[] args){
        StringBuilder result = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String s = sc.nextLine();
        for (int i=0;i<s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                int count = c - '1';
                for (int j=0; j<count; j++) {
                    System.out.print(s.charAt(i+1));
                }
            } else {
                System.out.print(c);
            }
        }
    }
    public static void maxRun(String[] args) {
        String s;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        s = sc.nextLine();
        int maxRun = 1;
        int currentRun = 1;
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                currentRun++;
                if (currentRun > maxRun) {
                    maxRun = currentRun;
                }
            } else {
                currentRun = 1;
            }
        }
        System.out.println(maxRun);
    }
    public static void booleanStringIntersect(String[] args) {
        String s1,s2;
        Scanner sc = new Scanner(System.in);
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        HashSet<Character> set1 = new HashSet<Character>();
        for (char c : s1.toCharArray()) {
            set1.add(c);
        }
        HashSet<Character> set2 = new HashSet<Character>();
        for (char c : s2.toCharArray()) {
            set2.add(c);
        }
        if (set1.size() == set2.size()) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}