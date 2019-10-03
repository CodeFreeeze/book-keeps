package gloffice.config.database.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.util.CollectionUtils;

//import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JpaDBConfig {
    private final Logger log = LoggerFactory.getLogger(JpaDBConfig.class);

//    @Inject
    public JpaProperties jpaProperties;

    private static Integer dsSerial = 1;



    protected JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }
    protected Properties additionalJpaProperties(DataSource dataSource) {
        Properties properties = new Properties();
        for (Map.Entry<String, String> entry : jpaProperties.getHibernateProperties(dataSource).entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }
        return properties;
    }

    protected HashMap<String, Object> additionalMapPara(DataSource dataSource) {
        HashMap<String, Object> mapPara = new HashMap<String, Object>();
        CollectionUtils.mergePropertiesIntoMap(additionalJpaProperties(dataSource), mapPara);
        return mapPara;
    }

    protected LocalContainerEntityManagerFactoryBean createEntityManager(DataSource dataSource,
                                                                         String[] packagesToScan,
                                                                         JpaVendorAdapter jpaVendorAdapter,
                                                                         String persistentUnitName) {





        HashMap<String, Object> mapPara = new HashMap<String, Object>();
//        mapPara.put("hibernate.transaction.jta.platform","org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform");
//        mapPara.put("hibernate.transaction.jta.platform", ImmDApiJtaPlatform.class.getName());
        mapPara.put("hibernate.transaction.coordinator_class", "jta");
        //mapPara.put("javax.persistence.transactionType", "JTA");
        //mapPara.put("hibernate.transaction.factory_class", "org.hibernine.transaction.internal.jta.CMTTransactionFactory");

        //mapPara.put("hibernate.transaction.manager_lookup_class", TransactionManagerLookup.class.getName());

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(dataSource);
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan(packagesToScan);
        entityManager.setPersistenceUnitName(persistentUnitName);
        entityManager.setJpaPropertyMap(mapPara);
        entityManager.setJpaProperties(additionalJpaProperties(dataSource));
        entityManager.afterPropertiesSet();
        return entityManager;
    }
}
