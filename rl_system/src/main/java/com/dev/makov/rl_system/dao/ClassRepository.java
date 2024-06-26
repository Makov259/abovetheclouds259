package com.dev.makov.rl_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.dev.makov.rl_system.entity.Class;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO class_students (class_id, student_id) VALUES (:classId, :studentId)", nativeQuery = true)
    void saveClassStudent(Long classId, Long studentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM class_students WHERE class_id = :classId AND student_id = :studentId", nativeQuery = true)
    void removeStudentFromClass(Long classId, Long studentId);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO class_teachers (class_id, teacher_id) VALUES (:classId, :teacherId)", nativeQuery = true)
    void addTeacherToClass(@Param("classId") Long classId, @Param("teacherId") Long teacherId);


    @Transactional
    @Modifying
    @Query("UPDATE Class c SET c.teacher.id = :teacherId WHERE c.id = :classId")
    void addTeacherToClass2(@Param("classId") Long classId, @Param("teacherId") Long teacherId);

    Optional<Class> findByTeacherId(Long teacherId);

}

