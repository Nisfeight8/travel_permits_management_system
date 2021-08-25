package travel.permits.management.system.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String place;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department",cascade=CascadeType.ALL)
    @JsonIgnore
    private Supervisor supervisor;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "department",orphanRemoval=true,cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;

}