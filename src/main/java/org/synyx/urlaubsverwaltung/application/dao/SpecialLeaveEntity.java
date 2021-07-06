package org.synyx.urlaubsverwaltung.application.dao;

import org.synyx.urlaubsverwaltung.application.domain.SpecialLeave;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;

@Entity(name = "special_leave")
public class SpecialLeaveEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Enumerated(STRING)
    private SpecialLeave specialLeave;

    @NotNull
    private int days;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpecialLeave getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(SpecialLeave specialLeave) {
        this.specialLeave = specialLeave;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SpecialLeaveEntity that = (SpecialLeaveEntity) o;
        return null != this.getId() && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}