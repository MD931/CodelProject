import java.io.IOException;
import java.util.EnumSet;

import org.hibernate.MappingException;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
 
public class HibernateGenerateSchema {
 
    public static void main(String[] args) throws MappingException, IOException {
        ServiceRegistry serviceRegistry = buildCfg();
        MetadataImplementor metadata = (MetadataImplementor) new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setOutputFile("hbm2schema.sql");
        schemaExport.create(EnumSet.of( TargetType.DATABASE ), metadata);
        ( (StandardServiceRegistryImpl) serviceRegistry ).destroy();
    }
     
    public static StandardServiceRegistryImpl buildCfg() {
    	StandardServiceRegistryImpl standardRegistry = 
    		       (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	return standardRegistry;
    }
}