package de.metafinanz.mam.backend.repository;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@RooEquals
@RooJpaActiveRecord(entityName = "Office", 
					finders = { "findOfficesByName", "findOfficesByNameLike" })
public class Office extends Location {
}
