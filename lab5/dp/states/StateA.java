package dp.states;

public class StateA implements State{


    @Override
    public boolean accepts() {
        return true;                        // bo A -> epsilon
    }

    @Override
    public State readChar(char c) {
        return switch (c) {
            case 'a' -> new StateA();       // bo A -> aA
            case 'b' -> new StateB();       // bo A -> bB
            case 'c' -> new StateFail();    // bo A nie ma nic z 'c'
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
