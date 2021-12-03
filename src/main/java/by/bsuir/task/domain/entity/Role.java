package by.bsuir.task.domain.entity;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
