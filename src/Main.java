import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int q = 0;
    public static int r = 0;
    public static boolean chek;
    public static char turn = 'X';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("add");
        char[] arr = new char[9];
        Arrays.fill(arr, ' ');
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %c %c %c |%n", arr[i], arr[i + 1], arr[i + 2]);
        }
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();

        System.out.println("Leg's go \nEnter coordinates. For example 1 2 or 3 1");
        boolean y = true;
        while (y) {
            char[] index = scanner.nextLine().toCharArray();
            boolean a, b;
            a = Character.isDigit(index[0]);
            b = Character.isDigit(index[2]);
            if (!a && !b) {
                System.out.println("You should enter numbers!");
            } else {
                if ((((int) index[0] <= 48) || ((int) index[0] >= 52)) || (((int) index[2] <= 48) || ((int) index[2] >= 52))) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    chek = move(arr, index[0], index[2]);
                    if (chek) {

                        for (int i = 0; i < 9; i++) {
                            System.out.print("-");
                        }
                        System.out.println();
                        for (int i = 0; i < 9; i += 3) {
                            System.out.printf("| %c %c %c |%n", arr[i], arr[i + 1], arr[i + 2]);
                        }
                        for (int i = 0; i < 9; i++) {
                            System.out.print("-");
                        }
                        System.out.println();
                        turn = swap(turn);
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }

                }
            }

            boolean poss = ifposs(arr);
            if (!poss) {
                System.out.println("Impossible");
                y = !y;
            } else {
                char win = ' ';
                for (int i = 0; i < 9; i += 3) {
                    win = ifwon(arr[i], arr[i + 1], arr[i + 2]);
                    if (win == 'x' || win == 'o') {
                        if (win == 'x') {
                            System.out.println("X wins");
                            y = !y;
                            break;
                        } else {
                            System.out.println("O wins");
                            y = !y;
                            break;
                        }

                    }
                }
                if (win == ' ') {
                    for (int i = 0; i < 3; i += 1) {
                        win = ifwon(arr[i], arr[i + 3], arr[i + 6]);
                        if (win == 'x' || win == 'o') {
                            if (win == 'x') {
                                System.out.println("X wins");
                                y = !y;
                                break;
                            } else {
                                System.out.println("O wins");
                                y = !y;
                                break;
                            }
                        }
                    }
                }
                if (win == ' ') {
                    win = ifwon(arr[0], arr[4], arr[8]);
                    if (win == 'x' || win == 'o') {
                        if (win == 'x') {
                            System.out.println("X wins");
                            y = !y;
                            break;
                        } else {
                            System.out.println("O wins");
                            y = !y;
                            break;
                        }
                    } else {
                        win = ifwon(arr[2], arr[4], arr[6]);
                        if (win == 'x' || win == 'o') {
                            if (win == 'x') {
                                System.out.println("X wins");
                                y = !y;
                                break;
                            } else {
                                System.out.println("O wins");
                                y = !y;
                                break;
                            }
                        } else {
                            for (char c : arr) {
                                if (c == '_') {
                                    System.out.println("Game not finished");
                                    break;
                                }
                            }
                            if (win == ' ') {
                                boolean draw = ifdraw(arr);
                                if (draw)
                                    continue;
                                if (!draw) {
                                    System.out.println("draw");
                                    y = !y;
                                    break;

                                }
                            }

                        }
                    }
                }
            }
        }
    }

    public static boolean ifdraw(char[] a) {
        for (int i = 0; i < 9; i++) {
            if (a[i] == ' ')
                return true;
        }
        return false;
    }

    public static char ifwon(char a, char b, char c) {
        if (a == b && b == c && a == 'X') {
            return 'x';
        } else {
            if (a == b && b == c && a == 'O') {
                return 'o';
            }
        }
        return ' ';
    }

    public static boolean ifposs(char[] a) {
        char p1 = ifwon(a[0], a[1], a[2]);
        char p2 = ifwon(a[3], a[4], a[5]);
        char p3 = ifwon(a[6], a[7], a[8]);
        if ((p1 == 'x' && (p2 != ' ' || p3 != ' ')) || (p1 == 'o' && (p2 != ' ' || p3 != ' ')) ||
                (p2 == 'x' && (p1 != ' ' || p3 != ' ')) || (p2 == 'o' && (p1 != ' ' || p3 != ' ')) ||
                (p3 == 'x' && (p2 != ' ' || p1 != ' ')) || (p3 == 'o' && (p2 != ' ' || p1 != ' '))) {
            return false;
        } else {
            p1 = ifwon(a[0], a[3], a[6]);
            p2 = ifwon(a[1], a[4], a[7]);
            p3 = ifwon(a[2], a[5], a[8]);
            if ((p1 == 'x' && (p2 != ' ' || p3 != ' ')) || (p1 == 'o' && (p2 != ' ' || p3 != ' ')) ||
                    (p2 == 'x' && (p1 != ' ' || p3 != ' ')) || (p2 == 'o' && (p1 != ' ' || p3 != ' ')) ||
                    (p3 == 'x' && (p2 != ' ' || p1 != ' ')) || (p3 == 'o' && (p2 != ' ' || p1 != ' '))) {
                return false;
            } else {
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == 'X') {
                        q++;
                    } else {
                        if (a[i] == 'O') {
                            r++;
                        }
                    }
                }
            }
            if (q - r >= 2 || r - q <= -2) {
                return false;
            } else {
                q = 0;
                r = 0;
            }
        }
        return true;
    }

    public static boolean move(char[] arr, int a, int b) {
        if (a == '1') {
            a = 0;
            if (b == '1') b = 1;
            if (b == '2') b = 2;
            if (b == '3') b = 3;
            b--;
        }
        if (a == '2') {
            a = 1;
            if (b == '1') b = 1;
            if (b == '2') b = 2;
            if (b == '3') b = 3;
            b++;
        }
        if (a == '3') {
            a = 3;
            if (b == '1') b = 1;
            if (b == '2') b = 2;
            if (b == '3') b = 3;
            b += 2;
        }
        if (arr[a + b] != ' ') {

            return false;
        } else {
            arr[a + b] = turn;
        }
        return true;
    }

    public static char swap(char a) {
        if (a == 'X')
            return 'O';
        if (a == 'O') ;
        return 'X';
    }
}