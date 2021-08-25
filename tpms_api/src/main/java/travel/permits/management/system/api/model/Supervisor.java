package travel.permits.management.system.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity@Table(name = "supervisors")
public class Supervisor {
	@Id
	@Column(name="id")
	int id;
	@OneToOne(cascade=CascadeType.REFRESH)
	@MapsId
	@JoinColumn(name="id", referencedColumnName = "id")
    private User user;
    @OneToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;

	@PrePersist
	public void onCreate(){
		user.setEnabled(true);
	}
	@PreRemove
	public void onDelete(){
		user.setEnabled(false);
		user.setSupervisor(null);
		department.setSupervisor(null);
	}

}
