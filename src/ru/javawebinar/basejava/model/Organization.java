package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String website;
    private final List<Period> periods;

    public Organization(String title, String website, List<Period> periods) {
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(periods, "periods must not be null");
        this.title = title;
        this.website = website;
        this.periods = periods;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!title.equals(that.title)) return false;
        if (!Objects.equals(website, that.website)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\ntitle =" + title + '\n' +
                "website =" + website + '\n' +
                "periods:\n" + periods + "\n";
    }

    public static class Period implements Serializable {
        private final String title;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String description;

        public Period(String title, int startYear, Month startMonth, int endYear, Month endMonth, String description) {
            this(title, DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), description);
        }

        public Period(String title, LocalDate startDate, LocalDate endDate, String description) {
            Objects.requireNonNull(title, "title must not be null");
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!title.equals(period.title)) return false;
            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            return Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "startDate = " + startDate + "\n" +
                    "endDate = " + endDate + '\n' +
                    "description = " + description + '\n';
        }
    }
}
