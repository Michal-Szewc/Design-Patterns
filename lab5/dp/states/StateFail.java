package dp.states;

public class StateFail implements State {

    @Override
    public boolean accepts() {
        return false;                       // Stan, gdy gramatyka już nie może być akceptowana
    }

    @Override
    public State readChar(char c) {
        return this;                        // Stan się nie zmienia
    }
}
