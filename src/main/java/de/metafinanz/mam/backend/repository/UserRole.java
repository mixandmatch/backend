package de.metafinanz.mam.backend.repository;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserRole {

    /**
     */
    @NotNull
    private String authority;

    /**
     */
    @NotNull
    private String username;
}
