package cern.lsa.mapping.example.TestEnums.json;

import cern.lsa.mapping.example.TestEnums.domain.Named;

import java.util.Arrays;

public class EnumsInJsonEnforcer {

    /**
     * Ensures the uniqueness of names
     * @param clazz the class to check
     */
    public void ensureIsJsonable(Class<? extends Enum<?>> clazz) {
        ensureIsNamed(clazz);
        ensureUniqueNamesInEnum(clazz);
    }

    private void ensureUniqueNamesInEnum(Class<? extends Enum<?>> clazz) {
        Named[] values = (Named[])clazz.getEnumConstants();
        long elementCount = Arrays.stream(values).map(Named::getName).distinct().count();
        if (elementCount != values.length) {
            throw new EnumsInJsonException(clazz.getCanonicalName() + " Cannot be used in JSON because some of its members have the same name");
        }
    }

    private void ensureIsNamed(Class<? extends Enum<?>> clazz) {
        if (!Named.class.isAssignableFrom(clazz)) {
            throw new EnumsInJsonException(clazz.getCanonicalName() + " Cannot be used in JSON as it does not implement " + Named.class.getCanonicalName());
        }
    }


}
