package dp.states;

public interface State {

    boolean accepts(); // czy stan jest akceptujący
    State readChar(char c); // jaki będzie nastepny stan
}
