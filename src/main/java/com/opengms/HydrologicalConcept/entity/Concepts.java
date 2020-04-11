package com.opengms.HydrologicalConcept.entity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/23 14:47
 */
@Data
@Document(collection = "ConceptSemantic")
public class Concepts implements Serializable {
    String conceptID;
    String name;
    String definition;
    String conceptType;
}
