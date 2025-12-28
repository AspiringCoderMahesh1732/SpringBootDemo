package com.example.SpringConcepts.component;

import org.springframework.stereotype.Component;

@Component
public class ManualWashingMachine implements WashingMachine{

    @Override
    public String run() {
        return "washing machine running manually";
    }
}
