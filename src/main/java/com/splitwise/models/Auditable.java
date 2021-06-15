package com.splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

public abstract class Auditable {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Date created;
    @Getter
    @Setter
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

    @Override
    public String toString() {
        return "Auditable{" +
                "id=" + id +
                '}';
    }
}
