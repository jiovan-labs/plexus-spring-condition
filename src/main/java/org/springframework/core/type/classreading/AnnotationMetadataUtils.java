/*
 * Copyright (c) 2024 Jiovan Ltd. All rights reserved.
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

package org.springframework.core.type.classreading;


import java.util.Arrays;
import java.util.Set;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadataUtils;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import com.jiovan.plexus.common.toolkit.util.ArrayUtilsWraps;


/**
 * Utilities for {@link org.springframework.core.type.AnnotationMetadata}
 *
 * @author David Hsing
 *
 * @see org.springframework.core.type.AnnotationMetadata
 * @see org.springframework.core.type.classreading.SimpleAnnotationMetadata
 * @see org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor
 * @see org.springframework.core.type.StandardAnnotationMetadata
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "UnusedReturnValue"})
public abstract class AnnotationMetadataUtils {
    /**
     * Creates simple class metadata from the supplied structure.
     *
     * @param className the fully qualified class name
     * @param access the access flags
     * @param enclosingClassName the name of the enclosing class, if any
     * @param superClassName the name of the superclass, if any
     * @param independentInnerClass whether the inner class is independent
     * @param interfaceNames the names of the implemented interfaces
     * @param memberClassNames the names of the member classes
     * @param declaredMethods the metadata of the declared methods
     * @param annotations the merged annotations to apply
     *
     * @return the newly created class metadata
     */
    @Nonnull
    @SuppressWarnings("DataFlowIssue")
    public static AnnotationMetadata createSimpleMetadata(@Nonnull String className, int access, @Nullable String enclosingClassName, @Nullable String superClassName, boolean independentInnerClass, @Nonnull String[] interfaceNames, @Nonnull String[] memberClassNames, @Nonnull Set<MethodMetadata> declaredMethods, @Nonnull MergedAnnotations annotations) {
        return new SimpleAnnotationMetadata(className, access, enclosingClassName, superClassName, independentInnerClass, ArrayUtilsWraps.asSet(interfaceNames), ArrayUtilsWraps.asSet(memberClassNames), declaredMethods, annotations);
    }

    /**
     * Creates a copy of the given class metadata with its annotations replaced by the supplied ones.
     *
     * @param metadata the original class metadata
     * @param annotations the merged annotations to apply
     *
     * @return the copied class metadata with the new annotations, or {@code null} if it cannot be created
     *
     * @throws IllegalAccessException if a required internal field cannot be accessed
     */
    @Nullable
    public static AnnotationMetadata renewMetadataAnnotation(@Nonnull AnnotationMetadata metadata, @Nonnull MergedAnnotation<?>... annotations) throws IllegalAccessException {
        return renewMetadataAnnotation(metadata, MergedAnnotations.of(Arrays.asList(annotations)));
    }

    /**
     * Creates a copy of the given class metadata with its annotations replaced by the supplied ones.
     *
     * @param metadata the original class metadata
     * @param annotations the merged annotations to apply
     *
     * @return the copied class metadata with the new annotations, or {@code null} if it cannot be created
     *
     * @throws IllegalAccessException if a required internal field cannot be accessed
     */
    @Nullable
    public static AnnotationMetadata renewMetadataAnnotation(@Nonnull AnnotationMetadata metadata, @Nonnull MergedAnnotations annotations) throws IllegalAccessException {
        if (metadata instanceof SimpleAnnotationMetadata) {
            int access = (int) FieldUtils.readDeclaredField(metadata, "access", true);    // $NON-NLS-1$
            boolean independentInnerClass = (boolean) FieldUtils.readDeclaredField(metadata, "independentInnerClass", true);    // $NON-NLS-1$
            return createSimpleMetadata(metadata.getClassName(), access, metadata.getEnclosingClassName(), metadata.getSuperClassName(), independentInnerClass, metadata.getInterfaceNames(), metadata.getMemberClassNames(), metadata.getDeclaredMethods(), annotations);
        } else if (metadata instanceof StandardAnnotationMetadata instance) {
            Class<?> introspectedClass = instance.getIntrospectedClass();
            boolean nestedAnnotationsAsMap = (boolean) FieldUtils.readDeclaredField(metadata, "nestedAnnotationsAsMap", true);    // $NON-NLS-1$
            StandardAnnotationMetadata result = ClassMetadataUtils.createStandardAnnotationMetadata(introspectedClass);
            if (result != null) {
                FieldUtils.writeDeclaredField(result, "nestedAnnotationsAsMap", nestedAnnotationsAsMap, true);    // $NON-NLS-1$
                FieldUtils.writeDeclaredField(result, "mergedAnnotations", annotations, true);    // $NON-NLS-1$
            }
            return result;
        }
        return null;
    }

    /**
     * Creates a copy of the given class metadata with its annotations replaced by the supplied ones, returning {@code null} if reflection fails.
     *
     * @param metadata the original class metadata
     * @param annotations the merged annotations to apply
     *
     * @return the copied class metadata with the new annotations, or {@code null} if it cannot be created
     */
    @Nullable
    public static AnnotationMetadata renewMetadataAnnotationQuietly(@Nonnull AnnotationMetadata metadata, @Nonnull MergedAnnotation<?>... annotations) {
        return renewMetadataAnnotationQuietly(metadata, MergedAnnotations.of(Arrays.asList(annotations)));
    }

    /**
     * Creates a copy of the given class metadata with its annotations replaced by the supplied ones, returning {@code null} if reflection fails.
     *
     * @param metadata the original class metadata
     * @param annotations the merged annotations to apply
     *
     * @return the copied class metadata with the new annotations, or {@code null} if it cannot be created
     */
    @Nullable
    public static AnnotationMetadata renewMetadataAnnotationQuietly(@Nonnull AnnotationMetadata metadata, @Nonnull MergedAnnotations annotations) {
        try {
            return renewMetadataAnnotation(metadata, annotations);
        } catch (Exception ignored) {
        }
        return null;
    }
}
