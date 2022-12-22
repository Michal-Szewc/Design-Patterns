package dp.states;

public class StateS implements State{

    @Override
    public boolean accepts() {
        return false;                       // bo nie ma S -> epsilon
    }

    @Override
    public State readChar(char c) {
        return switch (c) {
            case 'a' -> new StateA();       // bo S -> aA
            case 'b' -> new StateC();       // bo S -> bC
            case 'c' -> new StateFail();    // bo nie ma S -> 'c'
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
