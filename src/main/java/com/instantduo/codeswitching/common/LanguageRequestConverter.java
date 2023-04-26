package com.instantduo.codeswitching.common;

import com.instantduo.codeswitching.common.type.Language;
import org.springframework.core.convert.converter.Converter;


public class LanguageRequestConverter implements Converter<String, Language> {

    @Override
    public Language convert(String source) {
        return Language.inputToEnum(source);
    }
}
