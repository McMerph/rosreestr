package ru.mcmerphy.rosreestr.controllers.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * User-friendly formatter.
 */
public class Formatter {

    public static final String NO_CONTENT = "✖";

    /**
     * @return user-friendly number.
     */
    public static String format(BigDecimal property) {
        String result = NO_CONTENT;
        if (Objects.nonNull(property)) {
            DecimalFormat formatter = new DecimalFormat("#,###.00");
            result = formatter.format(property);
        }

        return result;
    }

    /**
     * @return user-friendly property.
     */
    public static String format(String property) {
        if (Objects.nonNull(property) && !property.isEmpty()) {
            return property;
        } else {
            return NO_CONTENT;
        }
    }

    /**
     * @param delimiter  delimiter to divide properties.
     * @param properties properties to handle.
     * @return user-friendly properties divided by specified delimiter.
     */
    public static String format(String delimiter, String... properties) {
        StringJoiner joiner = new StringJoiner(delimiter);
        Arrays.asList(properties).forEach(property -> joiner.add(format(property)));
        return joiner.toString();
    }

}
