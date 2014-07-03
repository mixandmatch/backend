// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Canteen;
import de.metafinanz.mam.backend.repository.CanteenDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect CanteenDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CanteenDataOnDemand: @Component;
    
    private Random CanteenDataOnDemand.rnd = new SecureRandom();
    
    private List<Canteen> CanteenDataOnDemand.data;
    
    public Canteen CanteenDataOnDemand.getNewTransientCanteen(int index) {
        Canteen obj = new Canteen();
        setAddress(obj, index);
        setCity(obj, index);
        setLatitude(obj, index);
        setLongitude(obj, index);
        setName(obj, index);
        setPostalCode(obj, index);
        return obj;
    }
    
    public void CanteenDataOnDemand.setAddress(Canteen obj, int index) {
        String address = "address_" + index;
        if (address.length() > 200) {
            address = address.substring(0, 200);
        }
        obj.setAddress(address);
    }
    
    public void CanteenDataOnDemand.setCity(Canteen obj, int index) {
        String city = "city_" + index;
        if (city.length() > 100) {
            city = city.substring(0, 100);
        }
        obj.setCity(city);
    }
    
    public void CanteenDataOnDemand.setLatitude(Canteen obj, int index) {
        Double latitude = new Integer(index).doubleValue();
        obj.setLatitude(latitude);
    }
    
    public void CanteenDataOnDemand.setLongitude(Canteen obj, int index) {
        Double longitude = new Integer(index).doubleValue();
        obj.setLongitude(longitude);
    }
    
    public void CanteenDataOnDemand.setName(Canteen obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public void CanteenDataOnDemand.setPostalCode(Canteen obj, int index) {
        Integer postalCode = new Integer(index);
        obj.setPostalCode(postalCode);
    }
    
    public Canteen CanteenDataOnDemand.getSpecificCanteen(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Canteen obj = data.get(index);
        Long id = obj.getId();
        return Canteen.findCanteen(id);
    }
    
    public Canteen CanteenDataOnDemand.getRandomCanteen() {
        init();
        Canteen obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Canteen.findCanteen(id);
    }
    
    public boolean CanteenDataOnDemand.modifyCanteen(Canteen obj) {
        return false;
    }
    
    public void CanteenDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Canteen.findCanteenEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Canteen' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Canteen>();
        for (int i = 0; i < 10; i++) {
            Canteen obj = getNewTransientCanteen(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
