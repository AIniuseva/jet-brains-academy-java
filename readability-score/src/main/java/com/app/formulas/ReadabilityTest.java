package com.app.formulas;

import java.util.HashMap;

public class ReadabilityTest {
    private static final HashMap<Integer, Integer> gradeLevel = new HashMap<>();

    static {
        gradeLevel.put(1, 5);
        gradeLevel.put(2, 7);
        gradeLevel.put(3, 9);
        gradeLevel.put(4, 10);
        gradeLevel.put(5, 11);
        gradeLevel.put(6, 12);
        gradeLevel.put(7, 13);
        gradeLevel.put(8, 14);
        gradeLevel.put(9, 15);
        gradeLevel.put(10, 16);
        gradeLevel.put(11, 17);
        gradeLevel.put(12, 18);
        gradeLevel.put(13, 24);
        gradeLevel.put(14, 30);
    }

    private ReadabilityTest() {
    }

    public static double automatedReadabilityIndex(int totalWords, int totalSentences, int totalCharacters) {
        return 4.71 * ((double) totalCharacters / totalWords) + 0.5 * ((double) totalWords / totalSentences) - 21.43;
    }

    public static double fleschKincaidReadabilityTest(int totalWords, int totalSentences, int totalSyllables) {
        return 0.39 * ((double) totalWords / totalSentences) + 11.8 * ((double) totalSyllables / totalWords) - 15.59;
    }

    public static double simpleMeasureOfGobbledygook(int totalSentences, int totalPolysyllables) {
        return 1.043 * Math.sqrt((double) totalPolysyllables * (double) 30 / totalSentences) + 3.1291;
    }

    public static double colemanLiauIndex(double averageCharactersPer100words, double averageSentencesFer100words) {
        return 0.0588 * averageCharactersPer100words - 0.296 * averageSentencesFer100words - 15.8;
    }

    public static int ageOfUnderstanding(double score) {
        int roundScore = (int) Math.ceil(score);
        if (roundScore > 14) {
            return gradeLevel.get(14);
        }
        return gradeLevel.get(roundScore);
    }
}

