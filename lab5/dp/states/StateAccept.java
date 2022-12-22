package dp.states;

public class StateAccept implements State{

    @Override
    public boolean accepts() {
        return true;                        // Stan, gdy gramatyka jest poprawna i zostały tylko symbole terminalne (B -> a) nie przechodzi potem dalej w stany
    }

    @Override
    public State readChar(char c) {
        return new StateFail();             // Po wczytaniu jakiego kolwiek znaku już nie działa, bo mamy same terminalne symbole.
    }
}
