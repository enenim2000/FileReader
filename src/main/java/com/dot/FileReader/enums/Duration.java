package com.dot.FileReader.enums;

public enum Duration {
    HOURLY("hourly"),
    DAILY("daily");

    String value;

    public String getValue(){
        return value;
    }

    Duration(String value) {
        this.value = value;
    }

    public static boolean isValid(String value) {
        for (Duration status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
