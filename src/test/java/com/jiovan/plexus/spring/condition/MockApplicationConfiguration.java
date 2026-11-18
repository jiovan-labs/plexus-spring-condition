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


import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnActiveProfile;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAllBooleanProperties;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAllClasses;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAllExpressions;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAllProperties;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAnyClasses;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAnyExpressions;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAnyProperties;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnMissingAllClasses;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnMissingAllProperties;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnMissingProperty;


@TestConfiguration
class MockApplicationConfiguration {
    static final String CONDITIONAL_ON_ALL_CLASSES_BEAN = "conditionalOnAllClassesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ALL_PROPERTIES_BEAN = "conditionalOnAllPropertiesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ALL_PROPERTIES_MISMATCH_BEAN = "conditionalOnAllPropertiesMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ANY_PROPERTIES_BEAN = "conditionalOnAnyPropertiesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ANY_PROPERTIES_MISMATCH_BEAN = "conditionalOnAnyPropertiesMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_MISSING_ALL_PROPERTIES_BEAN = "conditionalOnMissingAllPropertiesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_MISSING_ALL_PROPERTIES_MISMATCH_BEAN = "conditionalOnMissingAllPropertiesMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_BEAN = "conditionalOnAllBooleanPropertiesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_MISMATCH_BEAN = "conditionalOnAllBooleanPropertiesMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_EXPRESSIONS_BEAN = "conditionalOnExpressionsBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_EXPRESSIONS_MISMATCH_BEAN = "conditionalOnExpressionsMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_CLASSES_BEAN = "conditionalOnClassesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_MISSING_ALL_CLASSES_BEAN = "conditionalOnMissingAllClassesBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_CLASSES_MISMATCH_BEAN = "conditionalOnClassesMismatchBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ACTIVE_PROFILE_BEAN = "conditionalOnActiveProfileBean";    // $NON-NLS-1$
    static final String CONDITIONAL_ON_ACTIVE_PROFILE_MISMATCH_BEAN = "conditionalOnActiveProfileMismatchBean";    // $NON-NLS-1$

    @Bean(name = CONDITIONAL_ON_ALL_CLASSES_BEAN)
    @ConditionalOnAllClasses(value = {
        @ConditionalOnClass(name = "org.springframework.boot.test.context.SpringBootTest"),
        @ConditionalOnClass(value = MockApplicationInitializer.class)
    })
    public TestingStruct conditionalOnAllClasses() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ALL_PROPERTIES_BEAN)
    @ConditionalOnAllProperties(value = {
        @ConditionalOnProperty(name = "mock.condition.enabled", havingValue = "true"),
        @ConditionalOnProperty(prefix = "mock.condition", name = "mode", havingValue = "on")
    })
    public TestingStruct conditionalOnAllProperties() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ALL_PROPERTIES_MISMATCH_BEAN)
    @ConditionalOnAllProperties(value = {
        @ConditionalOnProperty(name = "mock.condition.enabled", havingValue = "true"),
        @ConditionalOnProperty(name = "mock.condition.mode", havingValue = "off")
    })
    public TestingStruct conditionalOnAllPropertiesMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ANY_PROPERTIES_BEAN)
    @ConditionalOnAnyProperties(value = {
        @ConditionalOnProperty(name = "mock.condition.absent"),
        @ConditionalOnProperty(name = "mock.condition.mode", havingValue = "on")
    })
    public TestingStruct conditionalOnAnyProperties() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ANY_PROPERTIES_MISMATCH_BEAN)
    @ConditionalOnAnyProperties(value = {
        @ConditionalOnProperty(name = "mock.condition.absent"),
        @ConditionalOnProperty(name = "mock.condition.flag", havingValue = "false")
    })
    public TestingStruct conditionalOnAnyPropertiesMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_MISSING_ALL_PROPERTIES_BEAN)
    @ConditionalOnMissingAllProperties(value = {
        @ConditionalOnMissingProperty(name = "mock.condition.absent"),
        @ConditionalOnMissingProperty(prefix = "mock.condition", name = "alsoAbsent")
    })
    public TestingStruct conditionalOnMissingAllProperties() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_MISSING_ALL_PROPERTIES_MISMATCH_BEAN)
    @ConditionalOnMissingAllProperties(value = {
        @ConditionalOnMissingProperty(name = "mock.condition.enabled"),
        @ConditionalOnMissingProperty(name = "mock.condition.absent")
    })
    public TestingStruct conditionalOnMissingAllPropertiesMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_BEAN)
    @ConditionalOnAllBooleanProperties(value = {
        @ConditionalOnBooleanProperty(name = "mock.condition.flag"),
        @ConditionalOnBooleanProperty(name = "mock.condition.enabled")
    })
    public TestingStruct conditionalOnAllBooleanProperties() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ALL_BOOLEAN_PROPERTIES_MISMATCH_BEAN)
    @ConditionalOnAllBooleanProperties(value = {
        @ConditionalOnBooleanProperty(name = "mock.condition.disabled")
    })
    public TestingStruct conditionalOnAllBooleanPropertiesMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_EXPRESSIONS_BEAN)
    @ConditionalOnAllExpressions(value = {
        @ConditionalOnExpression("${mock.condition.enabled:false}"),
        @ConditionalOnExpression("'${mock.condition.mode:}' == 'on'")
    })
    public TestingStruct conditionalOnExpressions() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_EXPRESSIONS_MISMATCH_BEAN)
    @ConditionalOnAnyExpressions(value = {
        @ConditionalOnExpression("${mock.condition.absent:false}"),
        @ConditionalOnExpression("${mock.condition.disabled:false}")
    })
    public TestingStruct conditionalOnExpressionsMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_CLASSES_BEAN)
    @ConditionalOnAnyClasses(value = {
        @ConditionalOnClass(name = "no.such.Class"),
        @ConditionalOnClass(name = "org.springframework.boot.SpringApplication")
    })
    public TestingStruct conditionalOnClasses() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_MISSING_ALL_CLASSES_BEAN)
    @ConditionalOnMissingAllClasses(value = {
        @ConditionalOnMissingClass("no.such.First"),
        @ConditionalOnMissingClass("no.such.Second")
    })
    public TestingStruct conditionalOnMissingAllClasses() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_CLASSES_MISMATCH_BEAN)
    @ConditionalOnMissingAllClasses(value = {
        @ConditionalOnMissingClass("no.such.First"),
        @ConditionalOnMissingClass("org.springframework.boot.SpringApplication")
    })
    public TestingStruct conditionalOnClassesMismatch() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ACTIVE_PROFILE_BEAN)
    @ConditionalOnActiveProfile(profile = "MOCK")
    public TestingStruct conditionalOnActiveProfile() {
        return new TestingStruct();
    }

    @Bean(name = CONDITIONAL_ON_ACTIVE_PROFILE_MISMATCH_BEAN)
    @ConditionalOnActiveProfile(profile = "prd")
    public TestingStruct conditionalOnActiveProfileMismatch() {
        return new TestingStruct();
    }

    static class TestingStruct {
    }
}
