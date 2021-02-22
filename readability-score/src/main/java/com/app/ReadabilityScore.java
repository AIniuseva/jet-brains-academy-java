package com.app;

import java.util.Scanner;

public class ReadabilityScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String textForEstimation = scanner.nextLine();

        String[] words = textForEstimation.split("\\s+");
        int totalWords = words.length;
        String sentences = textForEstimation.replaceAll("[^.!?]", "");
        int totalSentences = sentences.length();

        String lastWord = String.valueOf(textForEstimation.charAt(textForEstimation.length() - 1)).replaceAll("\\w+", "");
        if(!lastWord.matches("[.!?]")){
            totalSentences++;
        }

        double averageWordsInSentence = (double)totalWords / (double)totalSentences;
        if(averageWordsInSentence > 10){
            System.out.println("HARD");
        }else{
            System.out.println("EASY");
        }
    }
}
