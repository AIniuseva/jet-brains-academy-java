package com.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class ReadabilityScore {

    private static HashMap<Integer, String> gradeLevel = fillReadabilityIndexAgeMap();

    public static void main(String[] args) {
        File textFile = new File("D:\\Java-projects\\Readability Score\\Readability Score\\task\\src\\readability\\in.txt"); //TODO: CHANGE PATH
        int totalWords = 0;
        int totalSentences = 0;
        int totalCharacters = 0;

        String sentenceCheck;
        String characterCheck;
        String textLine = "";

        System.out.println("The text is:");
        try (Scanner scanner = new Scanner(textFile)) {
            while (scanner.hasNext()) {
                textLine = scanner.nextLine();

                System.out.println(textLine);

                String[] words = textLine.split("\\s+");
                totalWords += words.length;

                sentenceCheck = textLine.replaceAll("[^.!?]", "");
                totalSentences += sentenceCheck.length();

                characterCheck = textLine.replaceAll("\\s+", "");
                totalCharacters += characterCheck.length();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + textFile);
        }

        String lastWord = String.valueOf(textLine.charAt(textLine.length() - 1)).replaceAll("\\w+", "");
        if (!lastWord.matches("[.!?]")) {
            totalSentences++;
        }

        double score = automatedReadabilityIndex(totalWords, totalSentences, totalCharacters);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        System.out.println();
        System.out.println("Words: " + totalWords);
        System.out.println("Sentences: " + totalSentences);
        System.out.println("Characters: " + totalCharacters);
        System.out.println("The score is: " + decimalFormat.format(score));
        System.out.printf("This text should be understood by %s-year-olds.", determineTheAgeOfUnderstanding(score));

    }

    private static HashMap<Integer, String> fillReadabilityIndexAgeMap() {
        HashMap<Integer, String> gradeLevel = new HashMap<>();
        File gradeLevelFile = new File("D:\\Java-projects\\Readability Score\\Readability Score\\task\\src\\readability\\gradeLevel.txt");

        try (Scanner scanner = new Scanner(gradeLevelFile)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                String[] values = line.split("\\s+");
                for (String value : values) {
                    value.replaceAll("\\s+", "");
                }
                gradeLevel.put(Integer.valueOf(values[0]), values[1]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not fount: " + gradeLevelFile);
        }
        return gradeLevel;
    }

    private static double automatedReadabilityIndex(int totalWords, int totalSentences, int totalCharacters) {
        return 4.71 * ((double) totalCharacters / totalWords) + 0.5 * ((double) totalWords / totalSentences) - 21.43;
    }

    private static String determineTheAgeOfUnderstanding(double score) {
        int roundScore = (int) Math.round(score);
        return gradeLevel.get(roundScore);
    }
}
