package de.metafinanz.mixmatchresttest.domain;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
@RooJpaActiveRecord(entityName = "User", finders = { "findUserEsByUsernameLike", "findUserEsByUsernameEquals" })
public class UserE {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String username;
}
