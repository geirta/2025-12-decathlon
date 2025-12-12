package ee.geir.decathlon.util;

public class Calculations {

    public static int calculatePoints(String category, double result) {
        int score = 0;
        if (category.equalsIgnoreCase("100M_SPRINT")) {
            score = trackEvent(25.4347, 18, 1.81, result);
        } else if (category.equalsIgnoreCase("LONG_JUMP") ) {
            score = fieldEvent(0.14354, 220, 1.4, result);
        } else if (category.equalsIgnoreCase("SHOT_PUT") ) {
            score = fieldEvent(51.39, 1.5, 1.05, result);
        } else if (category.equalsIgnoreCase("HIGH_JUMP") ) {
            score = fieldEvent(0.8465, 75, 1.42, result);
        } else if (category.equalsIgnoreCase("400_METRES") ) {
            score = trackEvent(1.53775, 82, 1.81, result);
        } else if (category.equalsIgnoreCase("110M_HURDLES") ) {
            score = trackEvent(5.74352, 28.5, 1.92, result);
        } else if (category.equalsIgnoreCase("DISCUS") ) {
            score = fieldEvent(12.91, 4, 1.1, result);
        } else if (category.equalsIgnoreCase("POLE_VAULT") ) {
            score = fieldEvent(0.2797, 100, 1.35, result);
        } else if (category.equalsIgnoreCase("JAVELIN") ) {
            score = fieldEvent(10.14, 7, 1.08, result);
        } else if (category.equalsIgnoreCase("1500_METRES") ) {
            score = trackEvent(0.036768, 480, 1.85, result);
        }
        return score;
    }

    private static int trackEvent(double A, double B, double C, double P) {
        return Math.toIntExact((long) (A * Math.pow(B - P, C)));
    }

    private static int fieldEvent(double A, double B, double C, double P) {
        return Math.toIntExact((long) (A * Math.pow(P - B, C)));
    }
}
