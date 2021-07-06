package com.ic.business.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Lxz
 */
@Data
public class DDRSensorTemperatureDTO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String cmdId;

    private String componentId;

    private Date time;

    private Integer unitSum;

    private Integer unitNo;

    private Float lineTemperature;
}
