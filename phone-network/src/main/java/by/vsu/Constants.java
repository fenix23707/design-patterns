package by.vsu;

import by.vsu.models.tariffs.CallTariff;
import by.vsu.models.tariffs.impl.*;
import by.vsu.models.tariffs.impl.ATariff;
import by.vsu.models.tariffs.impl.BTariff;
import by.vsu.models.tariffs.impl.GTariff;
import by.vsu.models.tariffs.impl.VTariff;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Constants {
    public final static List<String> OUTER_OPERATOR_CODES = Arrays.asList("25", "29", "33", "44");

    public static final Map<Class<? extends CallTariff>, CallTariff> TARIFFS = Map.ofEntries(
            Map.entry(ATariff.class, new ATariff()),
            Map.entry(BTariff.class, new BTariff()),
            Map.entry(VTariff.class, new VTariff()),
            Map.entry(GTariff.class, new GTariff())
    );


}
