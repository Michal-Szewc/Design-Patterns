package dp.states;

public class StateC implements State{

    @Override
    public boolean accepts() {
        return true;                        // bo C -> epsilon
    }

    @Override
    public State readChar(char c) {
        return switch (c) {
            case 'a' -> new StateC();       // bo C -> Ac
            case 'b' -> new StateFail();    // bo C nie ma nic do 'b'
            case 'c' -> new StateB();       // bo C -> cB
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
