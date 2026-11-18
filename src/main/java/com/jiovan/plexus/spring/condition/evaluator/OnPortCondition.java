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
import java.util.Objects;
import jakarta.annotation.Nonnull;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;
import com.jiovan.plexus.common.toolkit.util.NumberUtilsWraps;
import com.jiovan.plexus.common.toolkit.util.StringUtilsWraps;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnPort;
import com.jiovan.plexus.spring.condition.util.ConditionBecauseUtils;
import com.jiovan.plexus.spring.toolkit.util.ApplicationEnvironmentWraps;


/**
 * Condition being active when matching the server port
 *
 * @author David Hsing
 */
@Order(value = Ordered.LOWEST_PRECEDENCE - 1000)
@SuppressWarnings("unused")
public class OnPortCondition extends SpringBootCondition {
    private static final Class<? extends Annotation> annotation = ConditionalOnPort.class;

    /**
     * Determines whether the condition declared by {@link ConditionalOnPort} matches the given metadata.
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
        Integer expectPort = attributes.getNumber("port");    // $NON-NLS-1$
        if (NumberUtilsWraps.isNotPositive(expectPort)) {
            return ConditionOutcome.noMatch(builder.because(ConditionBecauseUtils.illegalAttribute("port")));    // $NON-NLS-1$
        }
        Integer actualPort = ApplicationEnvironmentWraps.getLocalServerPort(context.getEnvironment());
        String quotation = StringUtilsWraps.quoteDouble(Objects.toString(actualPort));
        return expectPort.equals(actualPort) ? ConditionOutcome.match(builder.available(quotation)) : ConditionOutcome.noMatch(builder.notAvailable(quotation));
    }
}
