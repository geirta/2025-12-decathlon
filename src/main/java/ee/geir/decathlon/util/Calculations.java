package ee.geir.decathlon.util;

import ee.geir.decathlon.entity.Result;
import ee.geir.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Calculations {


    public static int calculatePoints(String category, double result) {
        int score = 0;
        double A = 0;
        double B = 0;
        double C = 0;
        double P = 0;
        if (category.equalsIgnoreCase("100M_SPRINT")) {
            A = 25.4347;
            B = 18;
            C = 1.81;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(B - P, C)));
        } else if (category.equalsIgnoreCase("LONG_JUMP") ) {
            A = 0.14354;
            B = 220;
            C = 1.4;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("SHOT_PUT") ) {
            A = 51.39;
            B = 1.5;
            C = 1.05;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("HIGH_JUMP") ) {
            A = 0.8465;
            B = 75;
            C = 1.42;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("400_METRES") ) {
            A = 1.53775;
            B = 82;
            C = 1.81;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(B - P, C)));
        } else if (category.equalsIgnoreCase("110M_HURDLES") ) {
            A = 5.74352;
            B = 28.5;
            C = 1.92;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(B - P, C)));
        } else if (category.equalsIgnoreCase("DISCUS") ) {
            A = 12.91;
            B = 4;
            C = 1.1;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("POLE_VAULT") ) {
            A = 0.2797;
            B = 100;
            C = 1.35;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("JAVELIN") ) {
            A = 10.14;
            B = 7;
            C = 1.08;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(P - B, C)));
        } else if (category.equalsIgnoreCase("1500_METRES") ) {
            A = 0.036768;
            B = 480;
            C = 1.85;
            P = result;
            score = Math.toIntExact((long) (A * Math.pow(B - P, C)));
        }
        return score;
    }

}
