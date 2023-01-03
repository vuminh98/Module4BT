package com.example.boxletter.service;

import com.example.boxletter.model.Languages;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component(value="languagesService")
public class LanguagesService {

    private static final List<Languages> languagess;

    static {
        languagess = new ArrayList<>();
        languagess.add(new Languages(1L,"English"));
        languagess.add(new Languages(2L,"Vietnamese"));
        languagess.add(new Languages(3L,"Japanese"));
        languagess.add(new Languages(4L,"Chinese"));
    }

    public List<Languages> findAll() {
        return languagess;
    }

}
