package org.example.learning.date;

import com.google.common.collect.Lists;
import lombok.Data;
import org.example.learning.date.dto.BusinessHourByDateDTOM;
import org.example.learning.date.dto.TimePeriodsM;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class testBusinessDate {
    public static void main(String[] args) {
        List<BusinessHourByDateDTOM> businessHourByDateDTOMS = buildBusinessHourList();
        List<LocalDateTime> timeList = buildTimeList(businessHourByDateDTOMS);
        System.out.println("timeList = " + timeList);
        // LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 8, 22, 1, 1);
        Config config = buildConfig();
        LocalDateTime businessTime = findSuitableLastEndTime(currentDateTime, timeList, config);
        System.out.println("businessTime = " + businessTime.toLocalTime().toString());
    }

    private static Config buildConfig() {
        Config config = new Config();
        config.setPretext("营业至");
        config.setIconShowType(1);
        config.setShopDivisionBusinessTime("06:00");
        config.setFirstStartTime("00:00");
        config.setFirstEndTime("06:00");
        config.setFirstTimeSpanCrossDay(false);
        config.setSecondStartTime("16:00");
        config.setSecondEndTime("06:00");
        config.setSecondTimeSpanCrossDay(true);
        return config;
    }

    private static LocalDateTime findSuitableLastEndTime(LocalDateTime currentDateTime, List<LocalDateTime> timeList, Config config) {
        // 判断三个入参是否为空
        if (currentDateTime == null || CollectionUtils.isEmpty(timeList) || config == null) {
            return null;
        }
        if (config.getFirstStartTime() == null || config.getFirstEndTime() == null || config.getSecondStartTime() == null || config.getSecondEndTime() == null || config.getShopDivisionBusinessTime() == null) {
            return null;
        }
        try {
            LocalDate currentDate = currentDateTime.toLocalDate();
            LocalTime currentTime = currentDateTime.toLocalTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime divisionTime = LocalTime.parse(config.getShopDivisionBusinessTime(), formatter);

            LocalDateTime startTime;
            LocalDateTime endTime;
            if (currentTime.isBefore(divisionTime)) {
                startTime = LocalDateTime.of(currentDate, LocalTime.parse(config.getFirstStartTime(), formatter));
                endTime = LocalDateTime.of(config.isFirstTimeSpanCrossDay() ? currentDate.plusDays(1) : currentDate, LocalTime.parse(config.getFirstEndTime(), formatter));
            } else {
                startTime = LocalDateTime.of(currentDate, LocalTime.parse(config.getSecondStartTime(), formatter));
                endTime = LocalDateTime.of(config.isSecondTimeSpanCrossDay() ? currentDate.plusDays(1) : currentDate, LocalTime.parse(config.getSecondEndTime(), formatter));
            }
            // 寻找合适的最后一个终止营业时间
            List<LocalDateTime> filteredTimes = timeList.stream()
                    .filter(time -> time.isAfter(startTime) && time.isBefore(endTime))
                    .collect(Collectors.toList());

            return filteredTimes.stream().max(LocalDateTime::compareTo).orElse(null);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static List<LocalDateTime> buildTimeList(List<BusinessHourByDateDTOM> businessHourByDateDTOMS) {
        if (CollectionUtils.isEmpty(businessHourByDateDTOMS)) {
            return Lists.newArrayList();
        }
        return businessHourByDateDTOMS.stream()
                .map(businessHourByDateDTOM -> extractEndTimes(businessHourByDateDTOM))
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<LocalDateTime> extractEndTimes(BusinessHourByDateDTOM businessHourByDateDTOM) {
        if (businessHourByDateDTOM == null) {
            return null;
        }
        String date = businessHourByDateDTOM.getDate();
        if (date == null) {
            return null;
        }
        List<TimePeriodsM> timePeriodsMs = businessHourByDateDTOM.getTimePeriod();
        if (timePeriodsMs == null || timePeriodsMs.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return timePeriodsMs.stream()
                .map(timePeriodsM -> {
                    try {
                        String endTime = timePeriodsM.getEndTime();
                        if (endTime == null) {
                            return null;
                        }
                        return LocalDateTime.parse(date + " " + endTime, formatter);
                    } catch (Exception e) {
                        return null;
                    }
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static List<BusinessHourByDateDTOM> buildBusinessHourList() {
        BusinessHourByDateDTOM businessHourByDateDTOM1 = new BusinessHourByDateDTOM();
        businessHourByDateDTOM1.setDate("2024-08-21");
        TimePeriodsM timePeriodsM1 = new TimePeriodsM();
        timePeriodsM1.setStartTime("00:00");
        timePeriodsM1.setEndTime("04:00");
        TimePeriodsM timePeriodsM2 = new TimePeriodsM();
        timePeriodsM2.setStartTime("22:00");
        timePeriodsM2.setEndTime("23:59");
        businessHourByDateDTOM1.setTimePeriod(Lists.newArrayList(timePeriodsM1, timePeriodsM2));
        // businessHourByDateDTOM1.setTimePeriod(Lists.newArrayList());

        BusinessHourByDateDTOM businessHourByDateDTOM2 = new BusinessHourByDateDTOM();
        businessHourByDateDTOM2.setDate("2024-08-22");
        TimePeriodsM timePeriodsM3 = new TimePeriodsM();
        timePeriodsM3.setStartTime("00:00");
        timePeriodsM3.setEndTime("05:00");
        TimePeriodsM timePeriodsM4 = new TimePeriodsM();
        timePeriodsM4.setStartTime("22:00");
        timePeriodsM4.setEndTime("23:59");
        businessHourByDateDTOM2.setTimePeriod(Lists.newArrayList(timePeriodsM3, timePeriodsM4));

        return Lists.newArrayList(businessHourByDateDTOM1, businessHourByDateDTOM2);
    }

    @Data
    public static class Config {
        /**
         * 前置文案
         */
        private String pretext;
        private Integer iconShowType;
        /**
         * 商户分流营业时间，值同 DefaultShopPaddingOpt 的 Config 的 shopDivisionBusinessTime
         * 例如: 06:00
         */
        private String shopDivisionBusinessTime;
        /**
         * 时间段一的开始时间、结束时间、结束时间是否跨天
         * 例如:
         * firstStartTime = "00:00"
         * firstEndTime = "06:00"
         * firstTimeSpanCrossDay = false
         */
        private String firstStartTime;
        private String firstEndTime;
        private boolean firstTimeSpanCrossDay;
        /**
         * 时间段二的开始时间、结束时间、结束时间是否跨天
         * 例如:
         * secondStartTime = "16:00"
         * secondEndTime = "06:00"
         * secondTimeSpanCrossDay = true
         */
        private String secondStartTime;
        private String secondEndTime;
        private boolean secondTimeSpanCrossDay;
    }
}
