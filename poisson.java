
public class Poisson{

    private static final Double rho = -0.05;
    private static final Integer n = 10;

    public static Double poissonCalc(Double mean, Integer val) {
        return (Math.pow(mean, val) * Math.pow(Math.E, -mean)) / ArithmeticUtils.factorial(val);
    }

    private Double dixonColesMultiplier(int home, int away, Double homeavg, Double awayavg) {
        if (home == 0 && away == 0) {
            return 1.0 - (homeavg * awayavg * rho);
        } else if (home == 1 && away == 0) {
            return 1.0 + (homeavg * rho);
        } else if (home == 0 && away == 1) {
            return 1.0 + (awayavg * rho);
        } else if (home == 1 && away == 1) {
            return 1.0 - rho;
        } else {
            return 1.0;
        }
    }

    public static RealMatrix calcMatrix(Double homeavg, Double awayavg) {
        RealMatrix mtx = new Array2DRowRealMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Double hprob = poissonCalc(homeavg, i);
                Double aprob = poissonCalc(awayavg, j);
                Double mult = dixonColesMultiplier(i, j, hprob, aprob);
                Double prob = hprob * aprob * mult;
                mtx.addToEntry(i, j, prob);
            }
        }
        return mtx;
    }
}

