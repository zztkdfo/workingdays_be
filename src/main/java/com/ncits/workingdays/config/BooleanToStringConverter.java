package com.ncits.workingdays.config;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
    public static final List<String> MAPPING_TRUE_LIST = Collections.unmodifiableList(Arrays.asList("T", "Y", "true", "TRUE"));
    public static final String CONVERT_TEXT_TRUE = "T";
    public static final String CONVERT_TEXT_FALSE = "F";

    @Override
    public String convertToDatabaseColumn(Boolean attribute){
        return (attribute != null && attribute) ? CONVERT_TEXT_TRUE : CONVERT_TEXT_FALSE;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return MAPPING_TRUE_LIST.contains(dbData);
    }

}
