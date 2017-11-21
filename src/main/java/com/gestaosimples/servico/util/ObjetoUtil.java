package com.gestaosimples.servico.util;

import java.util.Collection;
import java.util.Map;

public final class ObjetoUtil {
    public static boolean isVazio(Object obj) {
        if (obj == null)
            return true;
        if ((obj instanceof CharSequence))
            return ((CharSequence) obj).length() == 0;
        if ((obj instanceof Number))
            return ((Number) obj).longValue() == 0L;
        if ((obj instanceof Collection))
            return ((Collection) obj).isEmpty();
        if ((obj instanceof Map))
            return ((Map) obj).isEmpty();
        if ((obj instanceof Object[])) {
            return ((Object[]) obj).length == 0;
        }
        return false;
    }
}