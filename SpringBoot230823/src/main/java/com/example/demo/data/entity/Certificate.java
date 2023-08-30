package com.example.demo.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@Table(name = "certificate")
public class Certificate extends AbstractEntity{

    private Integer serial;
    private Integer number;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
