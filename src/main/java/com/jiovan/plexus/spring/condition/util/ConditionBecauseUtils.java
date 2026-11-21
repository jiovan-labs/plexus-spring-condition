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
import jakarta.annotation.Nonnull;
import org.springframework.util.ClassUtils;


/**
 * Utilities for evaluator reasons
 *
 * @author David Hsing
 */
@SuppressWarnings("unused")
public abstract class ConditionBecauseUtils {
    private static final String EMPTY_ATTRIBUTES = "annotation attributes of metadata for '%s' is empty";    // $NON-NLS-1$
    private static final String MISSING_ATTRIBUTE = "annotation attribute '%s' is missing or empty";    // $NON-NLS-1$
    private static final String ILLEGAL_ATTRIBUTE = "annotation attribute '%s' is illegal";    // $NON-NLS-1$

    /**
     * Returns the reason message for empty attributes of the given annotation type.
     *
     * @param annotation the annotation type
     *
     * @return the formatted reason message
     */
    @Nonnull
    public static String emptyAttributes(@Nonnull Class<? extends Annotation> annotation) {
        return emptyAttributes(ClassUtils.getQualifiedName(annotation));
    }

    /**
     * Returns the reason message for empty attributes of the named annotation type.
     *
     * @param className the qualified annotation class name
     *
     * @return the formatted reason message
     */
    @Nonnull
    public static String emptyAttributes(@Nonnull String className) {
        return String.format(EMPTY_ATTRIBUTES, className);
    }

    /**
     * Returns the reason message for a missing or empty annotation attribute.
     *
     * @param attribute the name of the annotation attribute
     *
     * @return the formatted reason message
     */
    @Nonnull
    public static String missingAttribute(@Nonnull String attribute) {
        return String.format(MISSING_ATTRIBUTE, attribute);
    }

    /**
     * Returns the reason message for an illegal annotation attribute.
     *
     * @param attribute the name of the annotation attribute
     *
     * @return the formatted reason message
     */
    @Nonnull
    public static String illegalAttribute(@Nonnull String attribute) {
        return String.format(ILLEGAL_ATTRIBUTE, attribute);
    }
}
