package org.example.learning.date.dto;

import lombok.Data;

import java.util.List;

@Data
public class BusinessHourByDateDTOM {
    /**
     * 日期
     */
    private String date;
    /**
     * 营业时间
     */
    private List<TimePeriodsM> timePeriod;
}
