import java.util.*;

//one con is student and 



    // --------- Student Class ----------
    class Student {
        int rollNumber;
        int age;
        String name;
        String fatherName;
        String motherName;
        List<String> subjects;

        public Student(StudentBuilder builder){
            this.rollNumber = builder.rollNumber;
            this.age = builder.age;
            this.name = builder.name;
            this.fatherName = builder.fatherName;
            this.motherName = builder.motherName;
            this.subjects = builder.subjects;
        }

        @Override
        public String toString(){
            return " roll number: " + this.rollNumber +
                    " age: " + this.age +
                    " name: " + this.name +
                    " father name: " + this.fatherName +
                    " mother name: " + this.motherName +
                    " subjects: " + subjects;
        }
    }

    // --------- Abstract StudentBuilder ----------
    abstract class StudentBuilder {
        int rollNumber;
        int age;
        String name;
        String fatherName;
        String motherName;
        List<String> subjects;

        public StudentBuilder setRollNumber(int rollNumber) {
            this.rollNumber = rollNumber;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setFatherName(String fatherName) {
            this.fatherName = fatherName;
            return this;
        }

        public StudentBuilder setMotherName(String motherName) {
            this.motherName = motherName;
            return this;
        }

        abstract public StudentBuilder setSubjects();

        public Student build() {
            return new Student(this);
        }
    }

    // --------- EngineeringStudentBuilder ----------
    class EngineeringStudentBuilder extends StudentBuilder {
        @Override
        public StudentBuilder setSubjects() {
            List<String> subs = new ArrayList<>();
            subs.add("DSA");
            subs.add("OS");
            subs.add("Computer Architecture");
            this.subjects = subs;
            return this;
        }
    }

    // --------- MBAStudentBuilder ----------
    class MBAStudentBuilder extends StudentBuilder {
        @Override
        public StudentBuilder setSubjects() {
            List<String> subs = new ArrayList<>();
            subs.add("Micro Economics");
            subs.add("Business Studies");
            subs.add("Operations Management");
            this.subjects = subs;
            return this;
        }
    }

    // --------- Director ----------
    class Director {
        StudentBuilder studentBuilder;

        Director(StudentBuilder studentBuilder){
            this.studentBuilder = studentBuilder;
        }

        public Student createStudent(){
            if(studentBuilder instanceof EngineeringStudentBuilder){
                return createEngineeringStudent();
            }
            else if(studentBuilder instanceof MBAStudentBuilder){
                return createMBAStudent();
            }
            return null;
        }

        private Student createEngineeringStudent(){
            return studentBuilder
                    .setRollNumber(1)
                    .setAge(22)
                    .setName("Rounak")
                    .setSubjects()
                    .build();
        }

        private Student createMBAStudent(){
            return studentBuilder
                    .setRollNumber(2)
                    .setAge(24)
                    .setName("Sunanda")
                    .setFatherName("MyFatherName")
                    .setMotherName("MyMotherName")
                    .setSubjects()
                    .build();
        }
    }

public class Main {
    // --------- Client (main method) ----------
    public static void main(String[] args){
        Director directorObj1 = new Director(new EngineeringStudentBuilder());
        Director directorObj2 = new Director(new MBAStudentBuilder());

        Student engineerStudent = directorObj1.createStudent();
        Student mbaStudent = directorObj2.createStudent();

        System.out.println(engineerStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}
