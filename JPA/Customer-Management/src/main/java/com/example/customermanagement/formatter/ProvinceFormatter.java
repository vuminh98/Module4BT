package com.example.customermanagement.formatter;

import com.example.customermanagement.model.Province;
import com.example.customermanagement.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ProvinceFormatter implements Formatter<Province> {

    private IProvinceService provinceService;

    @Autowired
    public ProvinceFormatter(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> provinceOptional;
        try {
            provinceOptional = provinceService.findById(Long.parseLong(text));
            return provinceOptional.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
