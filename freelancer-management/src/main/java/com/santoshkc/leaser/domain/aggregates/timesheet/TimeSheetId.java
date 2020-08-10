package com.santoshkc.leaser.domain.aggregates.timesheet;

import java.util.Objects;

class TimeSheetId {
    private final Long id;

    public TimeSheetId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSheetId that = (TimeSheetId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
