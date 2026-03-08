package com.airtribe.meditrack.util;

import com.airtribe.meditrack.enums.Specialization;

import java.util.HashMap;
import java.util.Map;

/**
 * Rule-based helper for simple doctor recommendations.
 */
public class AIHelper {
    private static final Map<String, Specialization> SYMPTOM_TO_SPECIALIZATION = new HashMap<>();

    static {
        SYMPTOM_TO_SPECIALIZATION.put("chest pain", Specialization.CARDIOLOGY);
        SYMPTOM_TO_SPECIALIZATION.put("rash", Specialization.DERMATOLOGY);
        SYMPTOM_TO_SPECIALIZATION.put("headache", Specialization.NEUROLOGY);
        SYMPTOM_TO_SPECIALIZATION.put("child", Specialization.PEDIATRICS);
        SYMPTOM_TO_SPECIALIZATION.put("orthopedic", Specialization.ORTHOPEDICS);
    }

    public static Specialization recommendSpecialization(String symptoms) {
        if (symptoms == null || symptoms.isEmpty()) {
            return Specialization.GENERAL_PRACTICE;
        }

        String key = symptoms.toLowerCase();
        for (Map.Entry<String, Specialization> entry : SYMPTOM_TO_SPECIALIZATION.entrySet()) {
            if (key.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return Specialization.GENERAL_PRACTICE;
    }
}
