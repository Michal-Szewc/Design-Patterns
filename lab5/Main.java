import dp.GrammarChecker;

public class Main {

    public static void checkGrammar(String s) {
        char[] toCheck = s.toCharArray();

        GrammarChecker g = new GrammarChecker();

        for (char c : toCheck) {
            g.readChar(c);
        }

        if (g.isInGrammar())
            System.out.println(s + " is in grammar");
        else
            System.out.println(s + " is not in grammar");
    }

    public static void main(String[] args) {
        checkGrammar("aaaaaaaaaaaaaaa");
        checkGrammar("aaaaabbbbba");
        checkGrammar("aaaaabbbbb");
        checkGrammar("baaacbb");
        checkGrammar("");
        checkGrammar("c");
        checkGrammar("bbb");
        checkGrammar("baaaacbba");
        checkGrammar("baaaacbbaaa");
        checkGrammar("aaaaabbbbaaaa");
    }
}