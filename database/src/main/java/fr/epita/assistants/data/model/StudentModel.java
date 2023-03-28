package fr.epita.assistants.data.model;
import javax.persistence.*;
@Entity @Table(name= "student_model")
public class StudentModel {
    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @JoinColumn (name = "id")
    @Column(name = "name")
    public String name;
    @Column(name = "course_id")
    public long course_id;
}
