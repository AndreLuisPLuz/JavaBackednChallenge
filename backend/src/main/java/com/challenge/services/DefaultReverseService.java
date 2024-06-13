package com.challenge.services;

import com.challenge.datatransfer.ReverseResult;
import com.challenge.interfaces.services.ReverseService;

public class DefaultReverseService implements ReverseService {
    public ReverseResult reverse(String wordToReverse) {
        if (wordToReverse == null)
            return new ReverseResult(null, false);

        if (wordToReverse.length() == 0)
            return new ReverseResult(null, false);

        String reversedWord = "";
        for (int i = wordToReverse.length()-1; i >= 0; i--) {
            reversedWord += wordToReverse.charAt(i);
        }

        Boolean isPalindrome = wordToReverse.equals(reversedWord);

        return new ReverseResult(reversedWord, isPalindrome);
    }
}
