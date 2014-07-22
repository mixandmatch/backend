// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package de.metafinanz.mam.backend.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Canteen_Roo_Finder {
    
    public static Long Canteen.countFindCanteensByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Canteen.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Canteen AS o WHERE o.name = :name", Long.class);
        q.setParameter("name", name);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Canteen.countFindCanteensByNameLike(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Canteen.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Canteen AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Long.class);
        q.setParameter("name", name);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Canteen> Canteen.findCanteensByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Canteen.entityManager();
        TypedQuery<Canteen> q = em.createQuery("SELECT o FROM Canteen AS o WHERE o.name = :name", Canteen.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Canteen> Canteen.findCanteensByName(String name, String sortFieldName, String sortOrder) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Canteen.entityManager();
        String jpaQuery = "SELECT o FROM Canteen AS o WHERE o.name = :name";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Canteen> q = em.createQuery(jpaQuery, Canteen.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Canteen> Canteen.findCanteensByNameLike(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Canteen.entityManager();
        TypedQuery<Canteen> q = em.createQuery("SELECT o FROM Canteen AS o WHERE LOWER(o.name) LIKE LOWER(:name)", Canteen.class);
        q.setParameter("name", name);
        return q;
    }
    
    public static TypedQuery<Canteen> Canteen.findCanteensByNameLike(String name, String sortFieldName, String sortOrder) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        EntityManager em = Canteen.entityManager();
        String jpaQuery = "SELECT o FROM Canteen AS o WHERE LOWER(o.name) LIKE LOWER(:name)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Canteen> q = em.createQuery(jpaQuery, Canteen.class);
        q.setParameter("name", name);
        return q;
    }
    
}
