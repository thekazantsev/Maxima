package com.example.demo.data.entity;

import jakarta.persistence.*;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    private Long id;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }

        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity other)) {
            return false;
        }

        if (getId() != null) {
            return getId().equals(other.getId());
        }

        return super.equals(other);
    }
}
