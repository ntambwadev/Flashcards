package com.triangleleft.flashcards.service.vocabular.stub;

import com.annimon.stream.Stream;
import com.triangleleft.flashcards.service.settings.SettingsModule;
import com.triangleleft.flashcards.service.settings.UserData;
import com.triangleleft.flashcards.service.vocabular.IVocabularModule;
import com.triangleleft.flashcards.service.vocabular.SimpleVocabularData;
import com.triangleleft.flashcards.service.vocabular.VocabularData;
import com.triangleleft.flashcards.service.vocabular.VocabularWord;
import com.triangleleft.flashcards.service.vocabular.VocabularWordsCache;
import com.triangleleft.flashcards.util.FunctionsAreNonnullByDefault;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

import static com.annimon.stream.Collectors.toList;

@FunctionsAreNonnullByDefault
public class StubVocabularModule implements IVocabularModule {

    private final SettingsModule settingsModule;
    private final VocabularWordsCache provider;

    @Inject
    public StubVocabularModule(SettingsModule settingsModule, VocabularWordsCache provider) {
        this.settingsModule = settingsModule;
        this.provider = provider;
    }

    @Override
    public Observable<List<VocabularWord>> getVocabularWords(boolean refresh) {
        UserData userData = settingsModule.getCurrentUserData().get();
        Observable<List<VocabularWord>> observable =
                Observable.just(buildVocabularData(userData.getUiLanguageId(), userData.getLearningLanguageId()))
                        .subscribeOn(Schedulers.io())
                        .doOnNext(this::updateCache)
                        .map(VocabularData::getWords);

        if (!refresh) {
            observable.startWith(provider.getWords(userData.getUiLanguageId(), userData.getLearningLanguageId()));
        }
        return observable;
    }

    private void updateCache(VocabularData data) {
        List<VocabularWord> words = Stream.of(data.getWords())
                .map(word -> word.withWord("cached_" + word.getWord()))
                .collect(toList());
        provider.putWords(words);
    }

    private VocabularData buildVocabularData(String uiLanguage, String learningLanguage) {
        List<VocabularWord> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(VocabularWord.create(
                    learningLanguage + "_word_" + i,
                    learningLanguage + "_word_" + i,
                    "pos",
                    "gender",
                    (int) (Math.random() * 4),
                    Collections.singletonList(uiLanguage + "_translation_" + i),
                    uiLanguage,
                    learningLanguage)
            );
        }
        return SimpleVocabularData.create(list, uiLanguage, learningLanguage);
    }

}
