import DP.*;

// Wszystkie błędy podczas tworzenia / obliczania są rzucane
// Rozmiary macierzy są sprawdzane podczas tworzenia operacji w grafie
// Wartości jak wyznacznik jest dopiero sprawdzany podczas wywoływania funkcji graph.calc


public class Main {
    public static void main(String[] args) {
        try {
            Graph g = new Graph();


            double[][] m1_arr = {{ 1, 2},
                                { 2, 1}};
            Matrix m1 = new Matrix("m1", m1_arr);


            double[][] m2_arr = {{ 1, 0},
                                { 0, 1}};
            Matrix m2 = new Matrix("m2", m2_arr);


            Matrix m3 = g.multiply(m1, g.add(m1, m2));
            // m3 = m1 * [2  2] = [6  6]
            //           [2  2]   [6  6]


            Scalar s = new Scalar("s", 2);

            Matrix m4 = g.inv(g.multiply(g.sub(m2, m3), s));
            // m2 - m3 = [-5 -6], sub * 2 = [-10 -12]  inv = [5/22 -3/11]
            //           [-6 -5]            [-12 -10]        [-3/11 5/22]


            Scalar w = g.det(m4);
            // det = -1/44



            g.show(m3);
            g.calc(m3);
            // powinno być [6  6]
            //             [6  6]

            g.show(m4);
            g.calc(m4);
            // powinno być [5/22 -3/11]
            //             [-3/11 5/22]

            g.show(w);
            g.calc(w);
            // wynik to powinno być -1/44

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}