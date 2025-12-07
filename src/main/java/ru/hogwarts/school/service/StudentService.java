package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long currentId = 1L;

    public Student createStudent(String name, int age) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя студента не может быть пустым");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }

        Long id = currentId++;
        Student student = new Student(id, name.trim(), age);
        students.put(id, student);
        return student;
    }

    public Student getStudent(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return students.get(id);
    }

    public Student updateStudent(Long id, String newName, Integer newAge) {
        Student student = getStudent(id);
        if (student == null) {
            return null;
        }

        if (newName != null && !newName.trim().isEmpty()) {
            student.setName(newName.trim());
        }
        if (newAge != null) {
            if (newAge < 0) {
                throw new IllegalArgumentException("Возраст не может быть отрицательным");
            }
            student.setAge(newAge);
        }

        return student;
    }

    public boolean deleteStudent(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return students.remove(id) != null;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Student> getStudentsByAge(int age) {
        return students.values().stream()
                .filter(s -> s.getAge() == age)
                .toList();
    }
}
