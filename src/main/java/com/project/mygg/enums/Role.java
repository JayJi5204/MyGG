package com.project.mygg.enums;

public enum Role {
    ADMIN,MANAGER,MEMBER;

    @Override
    public String toString() {
        return name();
    }
}
