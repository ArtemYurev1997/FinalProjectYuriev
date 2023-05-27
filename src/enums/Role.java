package enums;

public enum Role {
    ADMIN("Admin"),
    CLIENT("Client");
    private String description;

    Role(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "description='" + description + '\'' +
                '}';
    }
}
