package org.Mahmoud;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Course {

    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;
    private List<List<Double>> studentScores;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseName = Util.toTitleCase(courseName);

        if (credits > 0) {
            this.credits = credits;
        } else {
            this.credits = 0;
        }

        this.department = department;

        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.studentScores = new ArrayList<>();

        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId);
        nextId++;
    }

    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        List<Double> scores = new ArrayList<>();
        for (int i = 0; i < assignments.size(); i++) {
            scores.add(null);
        }
        studentScores.add(scores);

        student.registerCourse(this);

        return true;
    }

    public void addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment newAssignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(newAssignment);

        for (List<Double> studentScoreList : studentScores) {
            studentScoreList.add(null);
        }
    }

    public double calcStudentsAverage() {
        if (registeredStudents.isEmpty()) {
            return 0.0;
        }

        return 0.0;
    }

    public String toSimplifiedString() {
        return String.format("%s %s %s", courseId, courseName, department.getDepartmentName());
    }

    public void setCourseName(String courseName) {
        this.courseName = Util.toTitleCase(courseName);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department +
                ", assignments=" + assignments +
                ", registeredStudents=" + registeredStudents +
                ", studentScores=" + studentScores +
                '}';
    }
}
