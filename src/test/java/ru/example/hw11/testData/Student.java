package ru.example.hw11.testData;

import java.util.HashMap;

public class Student {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userNumber;
    private String gender;
    private String dateOfBirth;
    private String subjects;
    private String hobbies;
    private String pathImage;
    private String address;
    private String state;
    private String city;
    private HashMap<String, String> expRes = new HashMap<>();

    private Student() {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getPathImage() {
        return pathImage;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public HashMap<String, String> getExpRes() {
        return expRes;
    }

    public static Builder newBuilder() {
        return new Student().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder firstName(String firstName) {
            Student.this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            Student.this.lastName = lastName;
            return this;
        }

        public Builder userEmail(String userEmail) {
            Student.this.userEmail = userEmail;
            Student.this.expRes.put("Student Email", userEmail);
            return this;
        }

        public Builder userNumber(String userNumber) {
            Student.this.userNumber = userNumber;
            Student.this.expRes.put("Mobile", userNumber);
            return this;
        }

        public Builder gender(String gender) {
            Student.this.gender = gender;
            Student.this.expRes.put("Gender", gender);
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            Student.this.dateOfBirth = dateOfBirth;
            String[] date = dateOfBirth.split(" ");
            Student.this.expRes.put("Date of Birth",
                    String.format("%s %s,%s",date[0], date[1], date[2]));
            return this;
        }

        public Builder subjects(String subjects) {
            Student.this.subjects = subjects;
            Student.this.expRes.put("Subjects", subjects);
            return this;
        }

        public Builder hobbies(String hobbies) {
            Student.this.hobbies = hobbies;
            Student.this.expRes.put("Hobbies", hobbies);
            return this;
        }

        public Builder pathImage(String pathImage) {
            Student.this.pathImage = pathImage;
            String[] path = pathImage.split("/");
            Student.this.expRes.put("Picture", path[path.length -1]);
            return this;
        }

        public Builder address(String address) {
            Student.this.address = address;
            Student.this.expRes.put("Address", address);
            return this;
        }

        public Builder state(String state) {
            Student.this.state = state;
            return this;
        }

        public Builder city(String city) {
            Student.this.city = city;
            if (Student.this.state != null) {
                Student.this.expRes.put("State and City",
                        String.format("%s %s",Student.this.state, city));
            }
            return this;
        }

        public Student build() {
            Student.this.expRes.put("Student Name",
                    String.format("%s %s", Student.this.firstName, Student.this.lastName));
            return Student.this;
        }
    }
}
