package com.example.SpringConcepts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {
    @Autowired
    CacheManager cacheManager;
    public void printContents(){
        Cache cache = cacheManager.getCache("singleOrder");
        if(cache != null){
            System.out.println(Objects.requireNonNull(cache.getNativeCache()).toString());
        }
    }
}
