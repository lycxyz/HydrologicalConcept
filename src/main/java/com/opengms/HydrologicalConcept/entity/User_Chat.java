package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User_Chat {
    UUID id;
    String name;
    String avatarUrl;
    UUID roomId;
    String type;
    String deviceType;
    String address;
    Long loginTime;
    Boolean draw;
}
