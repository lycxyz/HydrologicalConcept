package com.opengms.HydrologicalConcept.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 19:52
 */
@Data
@Document(collection = "User")
public class LoginDTO {
    private String email;
    private String password;
}
