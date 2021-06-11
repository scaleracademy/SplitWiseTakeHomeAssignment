package com.splitwise.models;

import java.util.Date;
import java.util.Objects;

public abstract class Auditable {
    private Long id;
    private Date created;
    private Date modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auditable auditable = (Auditable) o;
        return id.equals(auditable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
