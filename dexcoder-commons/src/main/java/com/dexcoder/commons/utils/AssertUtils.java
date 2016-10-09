package com.dexcoder.commons.utils;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.dexcoder.commons.enums.IEnum;
import com.dexcoder.commons.exceptions.CommonsAssistantException;

/**
 * Created by liyd on 2015-8-24.
 */
public class AssertUtils {

    public static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertNotNull(Object obj, IEnum iEnum) {
        if (obj == null) {
            throw new CommonsAssistantException(iEnum);
        }
    }

    public static void assertFalse(Boolean b, String message) {
        if (b != null && b) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertFalse(Boolean b, IEnum iEnum) {
        if (b != null && b) {
            throw new CommonsAssistantException(iEnum);
        }
    }

    public static void assertTrue(Boolean b, String message) {
        if (b == null || !b) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertTrue(Boolean b, IEnum iEnum) {
        if (b == null || !b) {
            throw new CommonsAssistantException(iEnum);
        }
    }

    public static void assertEquals(int i1, int i2, IEnum message) {
        if (i1 != i2) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEquals(long i1, long i2, IEnum message) {
        if (i1 != i2) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEquals(String str1, String str2, String message) {
        if (!StringUtils.equals(str1, str2)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEquals(String str1, String str2, IEnum message) {
        if (!StringUtils.equals(str1, str2)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEquals(Object obj1, Object obj2, IEnum message) {
        if (obj1 != null) {
            if (!obj1.equals(obj2)) {
                throw new CommonsAssistantException(message);
            }
        } else if (obj2 != null) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertNotBlank(String str, IEnum message) {
        if (StringUtils.isBlank(str)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertNotEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertNotEmpty(Collection<?> collection, IEnum message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEmpty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertEmpty(Collection<?> collection, IEnum message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMinLength(String str, int length, String message) {
        if (StringUtils.length(str) < length) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMinLength(String str, int length, IEnum message) {
        if (StringUtils.length(str) < length) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMaxVal(int i, int val, String message) {
        if (i > val) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMaxVal(int i, int val, IEnum message) {
        if (i > val) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMinVal(int i, int val, String message) {
        if (i < val) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertMinVal(int i, int val, IEnum message) {
        if (i < val) {
            throw new CommonsAssistantException(message);
        }
    }

    public static void assertRegex(String str, String regex, String message) {
        AssertUtils.assertNotBlank(str, message);
        boolean matches = str.matches(regex);
        if (!matches) {
            throw new CommonsAssistantException(message);
        }
    }
}
