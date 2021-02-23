package com.app;

import com.app.formulas.ReadabilityTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadabilityScore {

    public static void main(String[] args) {
        File textFile = new File(args[0]);
        Scanner scanner = new Scanner(System.in);

        int totalWords = 0;
        int totalSentences = 0;
        int totalCharacters = 0;
        int totalSyllables = 0;
        int totalPolysyllables = 0;
        String textLine = "";

        System.out.println("The text is:");
        try (Scanner textScanner = new Scanner(textFile)) {
            while (textScanner.hasNext()) {
                String checker;
                textLine = textScanner.nextLine();
                System.out.println(textLine);
                String[] words = textLine.split("\\s+");

                for (String word : words) {
                    int vowels = 0;
                    word = word.replaceAll("[,.!?]", "");
                    checker = word.replaceAll("[^aeiouyAEIOUY]", "");
                    vowels += checker.length();

                    if (word.matches("\\w*[aeiouyAEIOUY]{2}\\w*")) {
                        checker = word.replaceAll("[aeiouyAEIOUY]{2}", "");
                        vowels -= (word.length() - checker.length()) / 2;
                    }
                    if (String.valueOf(word.charAt(word.length() - 1)).matches("[e]")) {
                        vowels--;
                    }
                    totalSyllables += vowels == 0 ? 1 : vowels;
                    totalPolysyllables += vowels >= 3 ? 1 : 0;
                }
                totalWords += words.length;
                checker = textLine.replaceAll("[^.!?]", "");
                totalSentences += checker.length();
                checker = textLine.replaceAll("\\s+", "");
                totalCharacters += checker.length();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + textFile);
            return;
        }

        if (String.valueOf(textLine.charAt(textLine.length() - 1)).matches("[^.!?]")) {
            totalSentences++;
        }

        double averageNumberOfCharacterPer100words = 100 * (double) totalCharacters / totalWords;
        double averageNumberOfSentencesPer100words = 100 * (double) totalSentences / totalWords;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        System.out.println();
        System.out.println("Words: " + totalWords);
        System.out.println("Sentences: " + totalSentences);
        System.out.println("Characters: " + totalCharacters);
        System.out.println("Syllables: " + totalSyllables);
        System.out.println("Polysyllables: " + totalPolysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        String choice = scanner.nextLine();
        double score;
        switch (choice) {
            case "ARI":
                score = ReadabilityTest.automatedReadabilityIndex(totalWords, totalSentences, totalCharacters);
                System.out.printf("Automated Readability Index: %s (about %d-year-olds).%n",
                        decimalFormat.format(score), ReadabilityTest.ageOfUnderstanding(score));
                break;
            case "FK":
                score = ReadabilityTest.fleschKincaidReadabilityTest(totalWords, totalSentences, totalSyllables);
                System.out.printf("Flesch–Kincaid readability tests: %s (about %d-year-olds).%n",
                        decimalFormat.format(score), ReadabilityTest.ageOfUnderstanding(score));
                break;
            case "SMOG":
                score = ReadabilityTest.SimpleMeasureOfGobbledygook(totalSentences, totalPolysyllables);
                System.out.printf("Simple Measure of Gobbledygook: %s (about %d-year-olds).%n",
                        decimalFormat.format(score), ReadabilityTest.ageOfUnderstanding(score));
                break;
            case "CL":
                score = ReadabilityTest.colemanLiauIndex(averageNumberOfCharacterPer100words, averageNumberOfSentencesPer100words);
                System.out.printf("Coleman–Liau index: %s (about %d-year-olds).%n",
                        decimalFormat.format(score), ReadabilityTest.ageOfUnderstanding(score));
                break;
            case "all":
                double ARIscore = ReadabilityTest.automatedReadabilityIndex(totalWords, totalSentences, totalCharacters);
                double FKscore = ReadabilityTest.fleschKincaidReadabilityTest(totalWords, totalSentences, totalSyllables);
                double SMOGscore = ReadabilityTest.SimpleMeasureOfGobbledygook(totalSentences, totalPolysyllables);
                double CLscore = ReadabilityTest.colemanLiauIndex(averageNumberOfCharacterPer100words, averageNumberOfSentencesPer100words);
                double averageAge = (double) (ReadabilityTest.ageOfUnderstanding(ARIscore) + ReadabilityTest.ageOfUnderstanding(FKscore) +
                        ReadabilityTest.ageOfUnderstanding(SMOGscore) + ReadabilityTest.ageOfUnderstanding(CLscore)) / 4;

                System.out.printf("Automated Readability Index: %s (about %d-year-olds).%n",
                        decimalFormat.format(ARIscore), ReadabilityTest.ageOfUnderstanding(ARIscore));
                System.out.printf("Flesch–Kincaid readability tests: %s (about %d-year-olds).%n",
                        decimalFormat.format(FKscore), ReadabilityTest.ageOfUnderstanding(FKscore));
                System.out.printf("Simple Measure of Gobbledygook: %s (about %d-year-olds).%n",
                        decimalFormat.format(SMOGscore), ReadabilityTest.ageOfUnderstanding(SMOGscore));
                System.out.printf("Coleman–Liau index: %s (about %d-year-olds).%n%n",
                        decimalFormat.format(CLscore), ReadabilityTest.ageOfUnderstanding(CLscore));
                System.out.printf("This text should be understood in average by %s-year-olds.%n", decimalFormat.format(averageAge));
                break;
            default:
                System.out.println("Wrong statement!");
                break;
        }
    }
}
