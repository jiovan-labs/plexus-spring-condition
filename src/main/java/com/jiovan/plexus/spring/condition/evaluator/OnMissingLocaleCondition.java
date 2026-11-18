/*
 * Copyright (c) 2026 Jiovan Ltd. All rights reserved.
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

package com.jiovan.plexus.spring.condition.evaluator;


import java.lang.annotation.Annotation;
import java.util.Locale;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;
import com.jiovan.plexus.common.toolkit.util.StringUtilsWraps;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnMissingLocale;
import com.jiovan.plexus.spring.condition.util.ConditionBecauseUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * Condition being active when missing (not matching) the system default locale
 *
 * @author David Hsing
 */
@Order(value = Ordered.LOWEST_PRECEDENCE - 1000)
@Slf4j
@SuppressWarnings("unused")
public class OnMissingLocaleCondition extends SpringBootCondition {
    private static final Class<? extends Annotation> annotation = ConditionalOnMissingLocale.class;

    /**
     * Determines whether the condition declared by {@link ConditionalOnMissingLocale} matches the given metadata.
     *
     * @param context the condition context providing the environment and bean factory
     * @param metadata the metadata of the annotated class or method being evaluated
     * @return the outcome of the condition evaluation
     */
    @Nonnull
    @Override
    public ConditionOutcome getMatchOutcome(@Nonnull ConditionContext context, @Nonnull AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder builder = ConditionMessage.forCondition(annotation);
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotation.getName()));
        if (CollectionUtils.isEmpty(attributes)) {
            return ConditionOutcome.noMatch(builder.because(ConditionBecauseUtils.emptyAttributes(annotation)));
        }
        String[] languageTags = attributes.getStringArray("languageTag");    // $NON-NLS-1$
        if (ArrayUtils.isEmpty(languageTags)) {
            return ConditionOutcome.noMatch(builder.because(ConditionBecauseUtils.missingAttribute("languageTag")));    // $NON-NLS-1$
        }
        boolean unmatched = !StringUtilsWraps.equalsAnyIgnoreCase(Locale.getDefault().toLanguageTag(), languageTags);
        String delimited = String.format("[%s]", org.springframework.util.StringUtils.arrayToCommaDelimitedString(languageTags));    // $NON-NLS-1$
        return unmatched ? ConditionOutcome.match(builder.notAvailable(delimited)) : ConditionOutcome.noMatch(builder.available(delimited));
    }
}
