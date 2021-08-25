package travel.permits.management.system.api.dto;

public class LoginResponseDto {
	private String token;
	private String type = "Bearer";
	int id;
	private String username;
	private String fullName;
	private String role;
	public LoginResponseDto(String token, int id, String username, String fullName,
			String role) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


}