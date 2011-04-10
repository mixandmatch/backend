package de.metafinanz.mixnmatch.backend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.openjpa.persistence.EntityManagerFactoryImpl;

import de.metafinanz.mixnmatch.backend.model.EventRequest;

public class MixandmatchDaoSimpleDBImpl implements MixandmatchDao {
	
	static Map<String,String> props = new HashMap<String,String>();
	static {

	}
	private static EntityManagerFactoryImpl factory = new EntityManagerFactoryImpl();
	
	public void saveLunchRequest(EventRequest eventRequest) {
        EntityManager em = null;
//      Storage fails if id is an empty string, so nullify it
        if (eventRequest.getId()!=null && eventRequest.getId().equals("")) {
            eventRequest.setId(null);
        }
        try {
            em = factory.createEntityManager();
            em.persist(eventRequest);

        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
	}

	@SuppressWarnings("unchecked")
	public List<EventRequest> getAllRequests() {
        EntityManager em = null;

        try {
            em = factory.createEntityManager();
            Query query = em.createQuery("select eventRequest from de.metafinanz.mixnmatch.backend.model.EventRequest eventRequest");
            return (List<EventRequest>)query.getResultList();
        }
        finally {
            if (em!=null) {
                em.close();
            }
        }
	}

}
