package pos.configuration;//package lk.ijse.dep.pos.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//
//@org.springframework.context.annotation.Configuration
//public class Configuration {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3307/jdbc")
//                .username("root")
//                .password("mysql")
//                .build();
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase(Database.MYSQL);
//        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL57Dialect");
//        adapter.setShowSql(true);
//        adapter.setGenerateDdl(env.
//                getRequiredProperty("hibernate.hbm2ddl.auto").
//                equalsIgnoreCase("update"));
//        adapter.set
//        spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
//
//        return adapter;
//    }
//
//}
