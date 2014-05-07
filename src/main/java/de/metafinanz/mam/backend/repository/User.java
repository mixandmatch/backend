package de.metafinanz.mam.backend.repository;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RooJavaBean
@RooToString
@RooEquals
@RooJson
@RooJpaActiveRecord(entityName = "User", finders = { "findUsersByUsernameLike", "findUsersByUsernameEquals" })
public class User {

    static Logger logger = LoggerFactory.getLogger(User.class);

    /**
     */
    @NotNull
    @Column(unique = true)
    private String username;

    /**
     * Helper field used by the userService to return the correct HTTP Status.
     */
    @Transient
    @JsonIgnore
    private boolean getOrCreateUserCreated = false;

    /**
     * Return the user from the database if it exists or create a new one.
     *
     * @param aUser
     *            User object with at least the id or username.
     * @return The user from the database.
     * @throws IllegalArgumentException
     *             Thrown if the aUser is null.
     */
    public static User getOrCreateUser(User aUser) throws IllegalArgumentException {
        logger.trace("entering getOrCreateUser");
        if (aUser == null) {
            throw new IllegalArgumentException("Null is not a user");
        }
    
        User dbUser;
        if (aUser.getId() != null && aUser.getId() > 0L) {
            // The user json had an ID, directly get the User from the db.
            dbUser = User.findUser(aUser.getId());
            if (dbUser == null) {
                throw new IllegalArgumentException("User with ID: " + aUser.getId() + " not found in database");
            }
            if(!dbUser.getPassword().equals(aUser.getPassword())){
            	throw new AuthorizationServiceException("Authorization not allowed");
            }
        } else {
            // The user had no ID. Let's try the username:
            List<User> userResult = User.findUsersByUsernameEquals(aUser.getUsername()).getResultList();
            if (userResult.isEmpty()) {
                // User does not exist: Create it
                dbUser = new User();
                dbUser.setUsername(aUser.getUsername());
                dbUser.setPassword(aUser.getPassword());
                dbUser.setEnabled(true);
                dbUser.setGetOrCreateUserCreated(true);
                dbUser.persist();
            } else {
                dbUser = userResult.get(0);
                if(!dbUser.getPassword().equals(aUser.getPassword())){
                	throw new AuthorizationServiceException("Authorization not allowed");
                }
            }
        }
        return dbUser;
    }

    /**
     */
    @NotNull
    private String password;

    /**
     */
    @NotNull
    private Boolean enabled;
}
