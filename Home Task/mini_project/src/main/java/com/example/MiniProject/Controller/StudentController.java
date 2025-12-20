package com.example.MiniProject.Controller;

import com.example.MiniProject.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final Map<Integer, Student> studentMap = new HashMap<>();

    @PostMapping
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty() ||
                student.getCourse() == null || student.getCourse().trim().isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name and Course must not be null or empty");
        }

        if (studentMap.containsKey(student.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with ID " + student.getId() + " already exists");
        }

        studentMap.put(student.getId(), student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student registered successfully");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = new ArrayList<>(studentMap.values());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        Student student = studentMap.get(id);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
        }

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        if (!studentMap.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found");
        }

        studentMap.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
    }
}