/*
 * Copyright 2023 C Thing Software
 * SPDX-License-Identifier: Apache-2.0
 */

package org.cthing.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation to indicate to test coverage tools that the annotated item should be excluded from analysis.
 *
 * <p>
 * Tools such as the <a href="https://www.eclemma.org/jacoco/">Jacoco</a> coverage analyzer and the
 * <a href="https://www.jetbrains.com/idea/">IntelliJ IDEA</a> IDE will automatically exclude from coverage
 * analysis any code annotated with an annotation containing the word "Generated" in its name. A typical
 * use for this annotation is to exclude from coverage analysis the private constructor of a utility class.
 * </p>
 */
@Retention(RetentionPolicy.CLASS)
@Target({
        ElementType.PACKAGE,
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.FIELD,
        ElementType.LOCAL_VARIABLE,
        ElementType.PARAMETER,
        ElementType.RECORD_COMPONENT
})
@Documented
public @interface NoCoverageGenerated {
}
