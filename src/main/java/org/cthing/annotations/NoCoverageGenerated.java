/*
 * Copyright 2023 C Thing Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cthing.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation to indicate to test coverage tools that the annotated item should be excluded from analysis.
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
