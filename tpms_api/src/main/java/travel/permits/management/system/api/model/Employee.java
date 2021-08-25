package travel.permits.management.system.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity@Table(name = "employees")
public class Employee {
	@Id
	@Column(name="id")
	int id;
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name="id", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "employee",cascade=CascadeType.REMOVE)
	private TravelRequest travelRequest;

	@PrePersist
	public void onCreate(){
		user.setEnabled(true);
	}
	@PreRemove
	public void onDelete(){
		user.setEnabled(false);
		user.setEmployee(null);
	}

}
