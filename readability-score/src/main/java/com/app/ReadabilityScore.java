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

        int totalWords = 0;
        int totalSentences = 0;
        int totalCharacters = 0;
        String textLine = "";

        try (Scanner scanner = new Scanner(textFile)) {
            while (scanner.hasNext()) {
                textLine = scanner.nextLine();
                System.out.println(textLine);
                String[] words = textLine.split("\\s+");
                totalWords += words.length;

                String checker;
                checker = textLine.replaceAll("[^.!?]", "");
                totalSentences += checker.length();
                checker = textLine.replaceAll("\\s+", "");
                totalCharacters += checker.length();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + textFile);
        }

        if (String.valueOf(textLine.charAt(textLine.length() - 1)).matches("[^.!?]")) {
            totalSentences++;
        }

        double score = ReadabilityTest.automatedReadabilityIndex(totalWords, totalSentences, totalCharacters);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        System.out.println();
        System.out.println("Words: " + totalWords);
        System.out.println("Sentences: " + totalSentences);
        System.out.println("Characters: " + totalCharacters);
        System.out.println("The score is: " + decimalFormat.format(score));

    }
}
