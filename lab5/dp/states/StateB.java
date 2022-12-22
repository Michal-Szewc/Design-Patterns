package dp.states;

public class StateB implements State{

    @Override
    public boolean accepts() {
        return true;                        // bo B -> epsilon
    }

    @Override
    public State readChar(char c) {
        return switch (c) {
            case 'a' -> new StateAccept();  // bo B -> a
            case 'b' -> new StateB();       // bo B -> bB
            case 'c' -> new StateFail();    // bo B nie ma nic do 'c'
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
