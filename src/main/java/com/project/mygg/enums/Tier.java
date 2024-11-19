package com.project.mygg.enums;

public enum Tier {
    S1(1),S2(2),S3(3),S4(4),
    A1(5),A2(6),A3(7),A4(8),A5(9),A6(10),
    B1(11),B2(12),B3(13),B4(14),B5(15),B6(16),B7(17),B8(18),
    C1(19),C2(20),C3(21),C4(22),C5(23),C6(24),C7(25),C8(26),
    D1(27),D2(28),D3(29),D4(30),D5(31),D6(32),
    E1(33),E2(34),E3(35),E4(36),
    UNRANKED(0);

    private final int value;

    Tier(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
