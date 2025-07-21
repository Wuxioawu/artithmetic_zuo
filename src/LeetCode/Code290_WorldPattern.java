package LeetCode;

import java.util.HashMap;

public class Code290_WorldPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        HashMap<Character, String> letter_to_words = new HashMap<>();
        HashMap<String, Character> words_to_letter = new HashMap<>();

        int letter_index = 0;
        int word_index = 0;

        while (letter_index < pattern.length() && word_index < words.length) {
            char letter = pattern.charAt(letter_index);
            String word = words[word_index];
            if (!letter_to_words.containsKey(letter) && !words_to_letter.containsKey(word)) {
                letter_to_words.put(letter, word);
                words_to_letter.put(word, letter);
            }
            letter_index++;
            word_index++;
        }

        word_index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (word_index < words.length) {
            stringBuilder.append(words_to_letter.get(words[word_index++]));
        }
        return stringBuilder.toString().equals(pattern);

    }

    public static void main(String[] args) {

    }
}
