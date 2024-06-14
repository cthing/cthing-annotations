/*
 * Copyright 2023 C Thing Software
 * PDX-License-Identifier: Apache-2.0
 */
package org.cthing.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;


/**
 * Declares that fields, method return types, method parameters, and type parameters within the annotated package
 * are not {@code null} by default. Items that can be null should be annotated with either
 * {@link javax.annotation.CheckForNull} or {@link javax.annotation.Nullable}.
 * <p>
 * This annotation depends on the {@link javax.annotation.Nonnull} annotation defined by the dormant
 * <a href="https://jcp.org/en/jsr/detail?id=305">JSR 305</a> specification. A reference implementation of this
 * specification is provided by
 * <a href="https://central.sonatype.com/artifact/com.google.code.findbugs/jsr305">com.google.code.findbugs:jsr305</a>.
 * </p>
 */
@Documented
@Nonnull
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
@TypeQualifierDefault({
        ElementType.PARAMETER,
        ElementType.TYPE_PARAMETER,
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.RECORD_COMPONENT
})
public @interface PackageNonnullByDefault {
}
