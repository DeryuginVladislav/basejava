package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    public static final ListSection EMPTY = new ListSection("");
    private List<String> strings;

    public ListSection() {
    }

    public ListSection(String... strings) {
        this(Arrays.asList(strings));
    }

    public ListSection(List<String> strings) {
        Objects.requireNonNull(strings, "items must not be null");
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return strings != null ? strings.hashCode() : 0;
    }

    @Override
    public String toString() {
        return strings.toString();
    }
}
