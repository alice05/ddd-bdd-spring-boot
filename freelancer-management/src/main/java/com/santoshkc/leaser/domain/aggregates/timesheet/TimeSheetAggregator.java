package com.santoshkc.leaser.domain.aggregates.timesheet;

import com.santoshkc.leaser.UserId;

public class TimeSheetAggregator {
    private final TimeSheetId timeSheetId;
    private final UserId userId;
    private final Integer year;
    private final Integer month;
    private final Integer hoursWorked;

    public TimeSheetAggregator(TimeSheetAggregatorBuilder builder) {
        timeSheetId = new TimeSheetId(builder.timeSheetId);
        userId = new UserId(builder.userId);
        year = builder.year;
        month = builder.month;
        hoursWorked = builder.hoursWorked;
    }

    public Long getTimeSheetId() {
        return timeSheetId.getId();
    }

    public Long getUserId() {
        return userId.getId();
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public static class TimeSheetAggregatorBuilder {
        private final Long timeSheetId;
        private Long userId;
        private Integer year;
        private Integer month;
        private Integer hoursWorked;

        public TimeSheetAggregatorBuilder(Long timeSheetId) {
            this.timeSheetId = timeSheetId;
        }

        public TimeSheetAggregatorBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public TimeSheetAggregatorBuilder year(Integer year) {
            this.year = year;
            return this;
        }

        public TimeSheetAggregatorBuilder month(Integer month) {
            this.month = month;
            return this;
        }

        public TimeSheetAggregatorBuilder hoursWorked(Integer hoursWorked) {
            this.hoursWorked = hoursWorked;
            return this;
        }

        public TimeSheetAggregator build() {
            return new com.santoshkc.leaser.domain.aggregates.timesheet.TimeSheetAggregator(this);
        }

    }

}
