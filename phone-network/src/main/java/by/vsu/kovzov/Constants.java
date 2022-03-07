package by.vsu.kovzov;

import by.vsu.kovzov.models.tariffs.CallTariff;
import by.vsu.kovzov.models.tariffs.impl.ATariff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public final static List<String> OUTER_OPERATOR_CODES = Arrays.asList("25", "29", "33", "44");

    public static final Map<Class<? extends CallTariff>, CallTariff> TARIFFS = Map.ofEntries(
            Map.entry(ATariff.class, new ATariff())
    );


}
