/*
 * Copyright (c) 2021 Jiovan Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.condition;


import java.util.List;
import jakarta.annotation.Nonnull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import com.jiovan.plexus.spring.condition.annotation.ConditionalOnAnyBeans;
import com.jiovan.plexus.spring.condition.evaluator.AbstractArrayWrapCondition;


/**
 * Condition being active when matching any of the {@link org.springframework.boot.autoconfigure.condition.ConditionalOnBean}
 *
 * @author David Hsing
 */
@Order(value = Ordered.LOWEST_PRECEDENCE - 1000)
@SuppressWarnings("unused")
public class OnAnyBeansCondition extends AbstractArrayWrapCondition<ConditionalOnAnyBeans, ConditionalOnBean> {
    /**
     * Matches when at least one nested condition matches.
     *
     * @param matched the messages of the nested conditions that matched
     * @param unmatched the messages of the nested conditions that did not match
     *
     * @return a match outcome when something is matched, otherwise a no-match outcome
     */
    @Nonnull
    @Override
    protected ConditionOutcome determineOutcome(@Nonnull List<ConditionMessage> matched, @Nonnull List<ConditionMessage> unmatched) {
        return CollectionUtils.isEmpty(matched) ? ConditionOutcome.noMatch(ConditionMessage.of(unmatched)) : ConditionOutcome.match(ConditionMessage.of(matched));
    }
}
