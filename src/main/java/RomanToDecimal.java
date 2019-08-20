package com.company;

import java.util.Scanner;

public class RomanToDecimal{
            private static int convertRec(String convertedRoman){
                if (convertedRoman.isEmpty()) return 0;
                if (convertedRoman.startsWith("M")) return 1000 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("CM")) return 900 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("D")) return 500 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("CD")) return 400 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("C")) return 100 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("XC")) return 90 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("L")) return 50 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("XL")) return 40 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("X")) return 10 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("IX")) return 9 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("V")) return 5 + convertRec(convertedRoman.substring(1));
                else if (convertedRoman.startsWith("IV")) return 4 + convertRec(convertedRoman.substring(2));
                else if (convertedRoman.startsWith("I")) return 1 + convertRec(convertedRoman.substring(1));
                throw new IllegalArgumentException("Unexpected roman numerals");
            }

            public static int convert(String convertedRoman){
                if (convertedRoman == null || convertedRoman.isEmpty() || !convertedRoman.matches("^(M{0,2})(CM|CD|D?C{0,2})(XC|XL|L?X{0,2})(IX|IV|V?I{0,2})$")) {
                    return -1;
                }
                return convertRec(convertedRoman);
            }
        }
