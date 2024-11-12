package com.project.mygg.enums;

public enum Role {
    ADMIN,MEMBER;

    @Override
    public String toString() {
        return name();
    }
}
