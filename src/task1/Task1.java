package task1;

public class Task1
{

    public static void main(String[] args)
    {
        while (true) {
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("");

            java.util.Scanner sc = new java.util.Scanner(System.in);
            String ans;

            System.out.println("Enter Text for Task 1");
            System.out.println("or type A1, A2, B1, B2 to check the text ");
            System.out.print(">");
            ans = sc.nextLine();

            if (ans.toLowerCase().equals("exit")) {
                break;
            } else if (ans.toLowerCase().equals("a1")) {
                ans = "1. int a;\n"
                        + "2. int a; /* random comment */\n"
                        + "3. int a; /* for storing width * height */\n"
                        + "4. int a = b*c;\n"
                        + "5. int a = b / c;\n"
                        + "6. int a = 55; // This is a comment / [\n"
                        + "7. public void doIt(int x) {System.out.println(x*100);}\n"
                        + "8. int []arr = new int[10];\n"
                        + "9. /* */ {}";
                System.out.println(ans);
            } else if (ans.toLowerCase().equals("a2")) {
                ans = "1. int [arr = new int[10];\n"
                        + "2. int b = 5; /* this is a comment /\n"
                        + "3. {a=b;\n"
                        + "4. }";
                System.out.println(ans);
            } else if (ans.toLowerCase().equals("b1")) {
                ans = "1. if(a == b) {a++;}\n"
                        + "2. if(a < (b*c)) {t = 5;}\n"
                        + "3. int []b = new int[5];\n"
                        + "4. [](){}\n"
                        + "5. int a = 5; // init a to 5";
                System.out.println(ans);
            } else if (ans.toLowerCase().equals("b2")) {
                ans = "1. for(int i=0;i<10;i++] {a+= b;} 2. {abc)";
                System.out.println(ans);
            }

            Stack<String> l;
            l = new Stack<String>();

            char[] t = ans.toCharArray();
            boolean forCase = false;
            int forBarcket = 0;
            
            CHECK_TEXT:
            for (int i = 0; i < t.length; i++) {
                String c = Character.toString(t[i]);
                if (c.equals("/") & i + 1 < t.length) { //not the last char
                    String c2 = Character.toString(t[i + 1]);
                    if (c2.equals("/")) { //ignore until new line
                        i++; //move to the second /
                        do {
                            i++; //move until new line
                        } while (i < t.length && Character.getType(t[i]) != Character.LINE_SEPARATOR);
                        continue;
                    } else if (c2.equals("*")) { //ignore until */
                        i++; //move to the second *
                        do {
                            i++; //move until */
                        } while (i < t.length - 1 && Character.toString(t[i]) != "*" & Character.toString(t[i + 1]) != "/");
                        continue;
                    }
                } else if (c.equals("f") & i + 2 < t.length) { //check if there is "for" command
                    if (Character.toString(t[i + 1]).equals("o") & Character.toString(t[i + 2]).equals("r")) {
                        forCase = true;
                        i += 2;
                    }
                } else if (c.equals(";")) { //stackk must be empty of ) or ] except the case of for(;;)
                    if (!l.isEmpty()) {
                        if (l.peek().equals("[")) {
                            System.out.println("Text not Valid Expect " + closingOf(l.peek()));
                            break CHECK_TEXT;
                        } else if (l.peek().equals("(") & !forCase) {
                            System.out.println("Text not Valid Expect " + closingOf(l.peek()));
                            break CHECK_TEXT;
                        }
                    }
                } else if (c.equals("[") | c.equals("{") | c.equals("(")) {
                    l.push(c);
                    if (c.equals("(") & forCase){
                        forBarcket++;
                    }
                } else if (c.equals("]")) {
                    String p = l.pop();
                    if (!p.equals("[")) {
                        System.out.println("Text not Valid Expect " + closingOf(p));
                        break CHECK_TEXT;
                    }
                } else if (c.equals("}")) {
                    String p = l.pop();
                    if (!p.equals("{")) {
                        System.out.println("Text not Valid Expect " + closingOf(p));
                        break CHECK_TEXT;
                    }
                } else if (c.equals(")")) {
                    String p = l.pop();
                    if (!p.equals("(")) {
                        System.out.println("Text not Valid Expect " + closingOf(p));
                        break CHECK_TEXT;
                    }else if(forCase){
                        forBarcket--;
                        if (forBarcket <= 0){
                            forCase = false;
                            forBarcket = 0;
                        }
                    }
                }
            }
            if (!l.isEmpty()) { // some openning not close
                System.out.println("Text not Valid still need closing for " + l.pop());
            }

        }

    }

    private static String closingOf(String s)
    {
        switch (s) {
        case "(":
            return ")";

        case "[":
            return "]";

        case "{":
            return "}";

        }
        return null;
    }
}
