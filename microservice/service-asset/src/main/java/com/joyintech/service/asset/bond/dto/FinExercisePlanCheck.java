package com.joyintech.service.asset.bond.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;
import java.util.Objects;

public class FinExercisePlanCheck {

    /**
     * 计划行权日
     */
    private Date exerciseDate;

    /**
     * 实际行权日
     */
    private Date actExerciseDate;

    /**
     * 取得 计划行权日
     * @return 计划行权日
     */
    public Date getExerciseDate() {
        return exerciseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinExercisePlanCheck that = (FinExercisePlanCheck) o;
        return Objects.equals(exerciseDate,that.exerciseDate) && Objects.equals(actExerciseDate,that.actExerciseDate);

    }


    @Override
    public int hashCode(){
        return Objects.hash(exerciseDate,actExerciseDate);
    }

    /**
     * 设置 计划行权日
     * @param exerciseDate 计划行权日
     */
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    /**
     * 取得 实际行权日
     * @return 实际行权日
     */
    public Date getActExerciseDate() {
        return actExerciseDate;
    }

    /**
     * 设置 实际行权日
     * @param actExerciseDate 实际行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActExerciseDate(Date actExerciseDate) {
        this.actExerciseDate = actExerciseDate;
    }
}