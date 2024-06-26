package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.AbsenceRepository;
import com.dev.makov.rl_system.dao.UserRepository;
import com.dev.makov.rl_system.entity.Absence;
import com.dev.makov.rl_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {


    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private UserRepository userRepository;

    public Absence saveAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    public List<Absence> findByStudentId(Long studentId) {
        return absenceRepository.findByStudentId(studentId);
    }

    public Absence findAbsenceById(Long id) {
        return absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence not found with id: " + id));
    }
}
