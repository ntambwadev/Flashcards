package com.triangleleft.flashcards.service.vocabular;

import com.triangleleft.flashcards.util.FunctionsAreNonnullByDefault;

import java.util.List;

@FunctionsAreNonnullByDefault
public interface VocabularWordsCache {

    List<VocabularyWord> getWords(String uiLanguageId, String learningLanguageId);

    void putWords(List<VocabularyWord> words);
}
