package net.unit8.eavee.entity;

import jakarta.persistence.Id;

public class EavFieldPossibleValue {
    @Id
    private Long id;
    private EavFieldEntity field;
    private String value;
    private Integer position;
}
