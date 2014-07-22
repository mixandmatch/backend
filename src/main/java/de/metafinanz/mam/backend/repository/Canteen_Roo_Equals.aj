// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect Canteen_Roo_Equals {
    
    public boolean Canteen.equals(Object obj) {
        if (!(obj instanceof Canteen)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Canteen rhs = (Canteen) obj;
        return new EqualsBuilder().append(address, rhs.address).append(city, rhs.city).append(id, rhs.id).append(latitude, rhs.latitude).append(longitude, rhs.longitude).append(name, rhs.name).append(postalCode, rhs.postalCode).isEquals();
    }
    
    public int Canteen.hashCode() {
        return new HashCodeBuilder().append(address).append(city).append(id).append(latitude).append(longitude).append(name).append(postalCode).toHashCode();
    }
    
}
