package com.javastudio.lms.tutorial.model.to;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "SECURITY_ROLE")
@SequenceGenerator(name = "SEQ_GENERATOR", sequenceName = "ROLE_SEQ")
@NamedQueries({
        @NamedQuery(name = Role.FIND_ALL, query = "select t from Role t"),
})
public class Role {

    public static final String FIND_ALL = "Role.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @NotNull
    @Past
    @Column(name = "creation", nullable = false, updatable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Timestamp version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }
}
