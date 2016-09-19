/*
 *
 * =======================================================================
 *
 * Copyright (c) 2014-2015 Domlex Limited. All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Domlex Limited.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Domlex Limited.
 *
 * =======================================================================
 *
 */

package com.triangleleft.flashcards;

import com.triangleleft.flashcards.test.DrawerTest;
import com.triangleleft.flashcards.test.FlashcardsTest;
import com.triangleleft.flashcards.test.LoginSuccessfulTest;
import com.triangleleft.flashcards.test.LoginTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        LoginSuccessfulTest.class,
        DrawerTest.class,
        //VocabularyTest.class
        FlashcardsTest.class
})
public class PhoneTestSuite {
}
