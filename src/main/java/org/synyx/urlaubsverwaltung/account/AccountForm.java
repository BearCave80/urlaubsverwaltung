package org.synyx.urlaubsverwaltung.account;

import org.springframework.format.annotation.DateTimeFormat;
import org.synyx.urlaubsverwaltung.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.synyx.urlaubsverwaltung.util.DateFormat.DD_MM_YYYY;
import static org.synyx.urlaubsverwaltung.util.DateFormat.D_M_YY;
import static org.synyx.urlaubsverwaltung.util.DateFormat.D_M_YYYY;

public class AccountForm {

    private int holidaysAccountYear;
    @DateTimeFormat(pattern = DD_MM_YYYY, fallbackPatterns = {D_M_YY, D_M_YYYY})
    private LocalDate holidaysAccountValidFrom;
    @DateTimeFormat(pattern = DD_MM_YYYY, fallbackPatterns = {D_M_YY, D_M_YYYY})
    private LocalDate holidaysAccountValidTo;
    private BigDecimal annualVacationDays;
    private BigDecimal actualVacationDays;
    private BigDecimal remainingVacationDays;
    private BigDecimal remainingVacationDaysNotExpiring;
    private String comment;

    private AccountForm() {
    }

    AccountForm(int year) {
        this.holidaysAccountYear = year;
        this.holidaysAccountValidFrom = DateUtil.getFirstDayOfYear(year);
        this.holidaysAccountValidTo = DateUtil.getLastDayOfYear(year);
    }

    AccountForm(Account holidaysAccount) {
        this.holidaysAccountYear = holidaysAccount.getValidFrom().getYear();
        this.holidaysAccountValidFrom = holidaysAccount.getValidFrom();
        this.holidaysAccountValidTo = holidaysAccount.getValidTo();
        this.annualVacationDays = holidaysAccount.getAnnualVacationDays();
        this.actualVacationDays = holidaysAccount.getVacationDays();
        this.remainingVacationDays = holidaysAccount.getRemainingVacationDays();
        this.remainingVacationDaysNotExpiring = holidaysAccount.getRemainingVacationDaysNotExpiring();
        this.comment = holidaysAccount.getComment();
    }

    public int getHolidaysAccountYear() {
        return holidaysAccountYear;
    }

    public void setHolidaysAccountYear(int holidaysAccountYear) {
        this.holidaysAccountYear = holidaysAccountYear;
    }

    public String getHolidaysAccountValidFromIsoValue() {
        if (holidaysAccountValidFrom == null) {
            return "";
        }

        return holidaysAccountValidFrom.format(DateTimeFormatter.ISO_DATE);
    }

    public LocalDate getHolidaysAccountValidFrom() {
        return holidaysAccountValidFrom;
    }

    public void setHolidaysAccountValidFrom(LocalDate holidaysAccountValidFrom) {
        this.holidaysAccountValidFrom = holidaysAccountValidFrom;
    }

    public String getHolidaysAccountValidToIsoValue() {
        if (holidaysAccountValidTo == null) {
            return "";
        }

        return holidaysAccountValidTo.format(DateTimeFormatter.ISO_DATE);
    }

    public LocalDate getHolidaysAccountValidTo() {
        return holidaysAccountValidTo;
    }

    public void setHolidaysAccountValidTo(LocalDate holidaysAccountValidTo) {
        this.holidaysAccountValidTo = holidaysAccountValidTo;
    }

    public BigDecimal getAnnualVacationDays() {
        return annualVacationDays;
    }

    public void setAnnualVacationDays(BigDecimal annualVacationDays) {
        this.annualVacationDays = annualVacationDays;
    }

    public BigDecimal getActualVacationDays() {
        return actualVacationDays;
    }

    public void setActualVacationDays(BigDecimal actualVacationDays) {
        this.actualVacationDays = actualVacationDays;
    }

    public BigDecimal getRemainingVacationDays() {
        return remainingVacationDays;
    }

    public void setRemainingVacationDays(BigDecimal remainingVacationDays) {
        this.remainingVacationDays = remainingVacationDays;
    }

    public BigDecimal getRemainingVacationDaysNotExpiring() {
        return remainingVacationDaysNotExpiring;
    }

    public void setRemainingVacationDaysNotExpiring(BigDecimal remainingVacationDaysNotExpiring) {
        this.remainingVacationDaysNotExpiring = remainingVacationDaysNotExpiring;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
