package com.joyintech.tams.exchange.common.db.domain;

public interface KeyMethodInterface<PK> {
    PK getId();

    void setId(PK id);
}
