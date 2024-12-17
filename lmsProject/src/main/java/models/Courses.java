package models;

public class Courses {
    private int id;
    private String name;
    private int trainerId;
    private String trainerName;
    private String courseDescription;  // Added field for description

    // Constructor
    public Courses(int id, String name, int trainerId, String courseDescription) {
        this.id = id;
        this.name = name;
        this.trainerId = trainerId;
        this.courseDescription = courseDescription;
    }

    public Courses() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Courses [id=" + id + ", name=" + name + ", trainerId=" + trainerId + ", trainerName=" + trainerName + ", courseDescription=" + courseDescription + "]";
    }
}
