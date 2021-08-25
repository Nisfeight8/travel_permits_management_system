package travel.permits.management.system.api.model.enums;

public enum PermitDuration {
	DAILY,
	WEEKLY,
	EXTRAORDINARY;
	public static PermitDuration getByRemovalDuration(String duration) {
        for (PermitDuration type : PermitDuration.values()) {
            if (type.name().equals(duration)) {
                return type;
            }
        }
        return null;
    }

}
