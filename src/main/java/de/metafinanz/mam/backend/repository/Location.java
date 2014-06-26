package de.metafinanz.mam.backend.repository;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@Entity
@RooJavaBean
@RooToString
@RooJson
@RooEquals
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@RooJpaActiveRecord
abstract public class Location {

    /**
     */
    @NotNull
    @Column(unique = true)
    protected String name;

    /**
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", unique = true)
    protected Long id;

    @Size(max=200)
    protected String address;
    
    protected Integer postalCode;
    
    @Size(max=100)
    protected String city;

    @NotNull
    protected Double longitude;

    @NotNull
    protected Double latitude;
    
}
