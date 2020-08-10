package com.santoshkc.leaser;

import java.util.Objects;

public class ProjectId {
    private final Long id;

    public ProjectId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId = (ProjectId) o;
        return Objects.equals(id, projectId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
