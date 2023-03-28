package fr.epita.assistants.data.model;

import javax.persistence.*;
import java.util.List;

@Entity @Table(name= "course_model")
public class CourseModel {
    @Column(name= "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @JoinColumn(name = "id")
    @Column(name = "name")
    public String name;
    public @ElementCollection @CollectionTable(name = "course_model_tags", joinColumns = @JoinColumn(name = "course_id")) List<String> tag;
}
