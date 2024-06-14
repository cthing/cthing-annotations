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


/**
 * A marker annotation to indicate that a method, constructor or type is given increased access for testing purposes.
 * <p>
 * The most common usage of this annotation is to indicate that a method, which could have private access, is given
 * package access so that it can be unit tested. Typically, unit tests are placed in the same package as the code
 * they are testing. This allows tests to access package scope methods and types without having to make them public
 * for testing purposes. However, it may be desirable to provide tests for private methods or private nested classes.
 * The only way to accomplish this is to change those private methods and types to package access. Unfortunately,
 * this makes it unclear to developers unfamiliar to the code whether these methods and types have been purposely
 * made available for production use by other classes in the package. To clarify that the increased access is only
 * for testing purposes, the {@link AccessForTesting} annotation can be applied to methods, constructors and types.
 * </p>
 * <pre>
 *    &#064;AccessForTesting
 *    String groupToPath(final String groupId) {
 *        return groupId.replace('.', '/');
 *    }
 * </pre>
 * <p>
 * In the above example, the {@code groupToPath} method could have {@code private} access but has been given package
 * access so that it can be tested. The application of the {@link AccessForTesting} annotation makes this intention
 * clear.
 * </p>
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE })
@Documented
public @interface AccessForTesting {
}
