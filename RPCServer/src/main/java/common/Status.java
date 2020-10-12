package common;

public enum Status {
    OK("200", "OK"),
    ERROR_VALIDATE("201", "ERROR_VALIDATE");

    private String code;
    private String name;

    Status(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Status getStatusByCode(String code) {
        for (Status status : Status.values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
