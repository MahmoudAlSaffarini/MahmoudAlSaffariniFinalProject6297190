package org.Mahmoud;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Student {

    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId);
        nextId++;

        this.studentName = Util.toTitleCase(studentName);
        this.gender = gender;
        this.address = address;
        this.department = department;

        this.registeredCourses = new ArrayList<>();
    }

    /**
     *
     * @param course
     * @return
     */
    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);

        return true;
    }

    /**
     *
     * @param course
     * @return
     */
    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);

        return true;
    }

    /**
     *
     * @return
     */
    public String toSimplifiedString() {
        return String.format("%s %s %s", gender, address, department);
    }

    public void setStudentName(String studentName) {
        this.studentName = Util.toTitleCase(studentName);
    }
}
