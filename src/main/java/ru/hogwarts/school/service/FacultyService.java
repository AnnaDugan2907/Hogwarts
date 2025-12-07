package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long currentId = 1L;

    public Faculty createFaculty(String name, String color) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя факультета не может быть пустым");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Цвет факультета не может быть пустым");
        }

        Long id = currentId++;  // Сначала присвоить, потом увеличить
        Faculty faculty = new Faculty(id, name.trim(), color.trim());
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return faculties.get(id);
    }

    public Faculty updateFaculty(Long id, String newName, String newColor) {
        Faculty faculty = getFaculty(id);
        if (faculty == null) {
            return null;
        }

        if (newName != null && !newName.trim().isEmpty()) {
            faculty.setName(newName.trim());
        }
        if (newColor != null && !newColor.trim().isEmpty()) {
            faculty.setColor(newColor.trim());
        }

        return faculty;
    }

    public boolean deleteFaculty(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        return faculties.remove(id) != null;
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();  // Возвращаем только данные, а не Map
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            return faculties.values();
        }
        return faculties.values().stream()
                .filter(f -> f.getColor().equalsIgnoreCase(color.trim()))
                .toList();
    }
}
