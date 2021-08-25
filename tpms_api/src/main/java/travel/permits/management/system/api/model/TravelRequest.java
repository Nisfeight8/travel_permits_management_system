package travel.permits.management.system.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import travel.permits.management.system.api.model.enums.PermitDuration;

@Setter
@Getter
@NoArgsConstructor
@Entity@Table(name = "travelRequests")
public class TravelRequest {
	@Id
	int id;
	@Column(columnDefinition = "boolean default false", nullable = false)
	private boolean approved;
	@Enumerated(EnumType.STRING)
	private PermitDuration permitDuration;
	@Column(nullable = false)
	private boolean fromWorkPlace;
	@Column(nullable = false)
	private String permitDate;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String address;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id", referencedColumnName="id")
	@MapsId
	private Employee employee;

}
