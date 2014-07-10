// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import de.metafinanz.mam.backend.repository.Office;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Office_Roo_Finder {
    
    public static Long Office.countFindOfficesByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Office.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Office AS o WHERE o.name = :name", Long.class);
        q.setParameter("name", name);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Office.countFindOfficesByNameLike(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Office.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Office AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Long.class);
        q.setParameter("name", name);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Office> Office.findOfficesByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Office.entityManager();
        TypedQuery<Office> q = em.createQuery("SELECT o FROM Office AS o WHERE o.name = :name", Office.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Office> Office.findOfficesByName(String name, String sortFieldName, String sortOrder) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Office.entityManager();
        String jpaQuery = "SELECT o FROM Office AS o WHERE o.name = :name";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Office> q = em.createQuery(jpaQuery, Office.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Office> Office.findOfficesByNameLike(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Office.entityManager();
        TypedQuery<Office> q = em.createQuery("SELECT o FROM Office AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Office.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Office> Office.findOfficesByNameLike(String name, String sortFieldName, String sortOrder) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Office.entityManager();
        String jpaQuery = "SELECT o FROM Office AS o WHERE LOWER(o.name) LIKE LOWER(:name)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Office> q = em.createQuery(jpaQuery, Office.class);
        q.setParameter("name", name);
        return q;
    }
    
}
