package myy803.socialbookstore.model.enums;

import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

/**
 * Enum representing user roles in the application.
 * <p>
 * This enum defines two roles: USER and ADMIN. Each role has a string value associated with it.
 * </p>
 */
@Getter
@ToString
public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    /**
     * @param value the string value of the role
     * @return the role that has the given value, or null if no role has the given value
     */
    public static @Nullable Role fromString(String value) {
        for (Role role : Role.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        return null;
    }

    /**
     * @param value the string value of the role
     * @return the role that has the given value, or the default role if no role has the given value
     */
    public static Role fromStringOrDefault(String value, Role defaultRole) {
        Role role = fromString(value);
        return role != null ? role : defaultRole;
    }

}
