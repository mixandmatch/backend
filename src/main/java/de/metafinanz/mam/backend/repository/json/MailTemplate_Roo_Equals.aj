// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository.json;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect MailTemplate_Roo_Equals {
    
    public boolean MailTemplate.equals(Object obj) {
        if (!(obj instanceof MailTemplate)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        MailTemplate rhs = (MailTemplate) obj;
        return new EqualsBuilder().append(action, rhs.action).append(crontroller, rhs.crontroller).append(format, rhs.format).append(key, rhs.key).append(recipients, rhs.recipients).append(template, rhs.template).append(version, rhs.version).isEquals();
    }
    
    public int MailTemplate.hashCode() {
        return new HashCodeBuilder().append(action).append(crontroller).append(format).append(key).append(recipients).append(template).append(version).toHashCode();
    }
    
}