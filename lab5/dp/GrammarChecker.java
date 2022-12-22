package dp;

import dp.states.*;

public class GrammarChecker {
    private State currentState;
    private static final String alphabet = "abc";

    public GrammarChecker(){
        reset();
    }

    public void reset(){
        currentState = new StateS();
    }

    public boolean isInGrammar(){
        return currentState.accepts();
    }

    public void readChar(char c){
        if(alphabet.contains(Character.toString(c)))
            this.currentState = currentState.readChar(c);
        else
            this.currentState = new StateFail();
    }
}
