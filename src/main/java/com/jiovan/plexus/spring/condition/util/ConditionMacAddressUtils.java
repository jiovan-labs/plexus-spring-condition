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

package com.jiovan.plexus.spring.condition.util;


import java.lang.annotation.Annotation;
import java.util.List;
import jakarta.annotation.Nonnull;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.util.CollectionUtils;
import com.jiovan.plexus.common.toolkit.util.InetAddressWraps;
import com.jiovan.plexus.common.toolkit.util.RegexUtilsWraps;
import com.jiovan.plexus.common.toolkit.util.StringUtilsWraps;


/**
 * Utilities for evaluator mac address
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class ConditionMacAddressUtils {
    /**
     * Compares the local MAC addresses with an expected value, optionally as a regular expression.
     *
     * @param conditionClass the annotation class declaring the condition
     * @param attributeName the name of the annotation attribute being checked
     * @param macAddress the expected MAC address or regular expression
     * @param regex whether the expected value is a regular expression
     * @return an outcome indicating whether any local MAC address matches the expected value
     */
    @Nonnull
    public static ConditionOutcome matchMacAddress(@Nonnull Class<? extends Annotation> conditionClass, @Nonnull String attributeName, @Nonnull String macAddress, boolean regex) {
        ConditionMessage.Builder builder = ConditionMessage.forCondition(conditionClass);
        List<String> localAddresses = InetAddressWraps.getLocalMacAddresses();
        if (CollectionUtils.isEmpty(localAddresses)) {
            return ConditionOutcome.noMatch(builder.because("the localhost mac address is empty"));
        }
        String quotation = StringUtilsWraps.quoteDouble(macAddress);
        boolean matched;
        if (regex) {
            if (!RegexUtilsWraps.isCompilable(macAddress)) {
                return ConditionOutcome.noMatch(builder.because(ConditionBecauseUtils.illegalAttribute(attributeName)));
            }
            matched = RegexUtilsWraps.matchAnySequencesIgnoreCase(macAddress, localAddresses);
        } else {
            matched = StringUtilsWraps.equalsAnyIgnoreCase(macAddress, localAddresses);
        }
        return matched ? ConditionOutcome.match(builder.available(quotation)) : ConditionOutcome.noMatch(builder.notAvailable(quotation));
    }
}
