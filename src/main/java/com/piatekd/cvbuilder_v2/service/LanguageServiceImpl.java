package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.ForeignLanguage;
import com.piatekd.cvbuilder_v2.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LanguageServiceImpl {


    private final LanguageRepository repository;

    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    public ForeignLanguage save(ForeignLanguage language) {
        return repository.save(language);
    }
}
