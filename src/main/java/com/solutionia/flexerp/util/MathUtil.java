package com.solutionia.flexerp.util;

import org.springframework.stereotype.Component;

@Component
public class MathUtil {

    public Double round(Double value) {

        if (value != null) {
            System.out.println("value: "+value);
            System.out.println("value to String: "+value.toString());
            String[] splitter = value.toString().split("\\.");
            System.out.println("decimals: "+splitter[1].length());
            if (splitter[1].length() <= 2) {
                return value;
            } else {
                return (long) (value * 1e2) / 1e2;
            }

        } else {
            return 1d;
        }
    }

    public Double round(Double value, Integer decimals) {
        if (value != null) {

            System.out.println("value: "+value);
            System.out.println("value to String: "+value.toString());
            String[] splitter = value.toString().split("\\.");
            System.out.println("decimals: "+splitter[1].length());

            if (splitter[1].length() <= decimals) {
                return value;
            }
            if (decimals == 3) {
                return (long) (value * 1e3) / 1e3;
            }
            if (decimals == 2) {
                return (long) (value * 1e2) / 1e2;
            }
            if (decimals == 1) {
                return (long) (value * 1e1) / 1e1;
            } else {
                return (long) (value * 1e0) / 1e0;
            }

        } else {
            return null;
        }
    }

}
