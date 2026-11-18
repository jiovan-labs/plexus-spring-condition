/*
 * Copyright (c) 2026 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiovan.plexus.spring.condition;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.jiovan.plexus.common.toolkit.constant.AssertMessageConst;
import com.jiovan.plexus.spring.toolkit.util.BeanFactoryWraps;


@SpringBootTest(classes = {MockApplicationInitializer.class, MockApplicationConfiguration.class}, properties = {
    "mock.condition.enabled=true",
    "mock.condition.mode=on",
    "mock.condition.flag=true",
    "mock.condition.disabled=false"
}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("mock")
class MockApplicationTest {
    @Autowired
    private BeanFactory beanFactory;

    @Test
    void conditionalOnAllClasses() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_ALL_CLASSES_BEAN);
    }

    @Test
    void conditionalOnProperties() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_ALL_PROPERTIES_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_ALL_PROPERTIES_MISMATCH_BEAN);
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_ANY_PROPERTIES_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_ANY_PROPERTIES_MISMATCH_BEAN);
    }

    @Test
    void conditionalOnMissingProperties() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_MISSING_ALL_PROPERTIES_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_MISSING_ALL_PROPERTIES_MISMATCH_BEAN);
    }

    @Test
    void conditionalOnBooleanProperties() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_MISMATCH_BEAN);
    }

    @Test
    void conditionalOnExpressions() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_EXPRESSIONS_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_EXPRESSIONS_MISMATCH_BEAN);
    }

    @Test
    void conditionalOnClasses() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_CLASSES_BEAN);
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_MISSING_ALL_CLASSES_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_CLASSES_MISMATCH_BEAN);
    }

    @Test
    void conditionalOnActiveProfile() {
        assertBeanPresent(MockApplicationConfiguration.CONDITIONAL_ON_ACTIVE_PROFILE_BEAN);
        assertBeanAbsent(MockApplicationConfiguration.CONDITIONAL_ON_ACTIVE_PROFILE_MISMATCH_BEAN);
    }

    private void assertBeanPresent(String beanName) {
        Assertions.assertNotNull(BeanFactoryWraps.getBean(beanFactory, beanName), AssertMessageConst.NOT_NULL);
    }

    private void assertBeanAbsent(String beanName) {
        Assertions.assertNull(BeanFactoryWraps.getBean(beanFactory, beanName), AssertMessageConst.IS_NULL);
    }
}
