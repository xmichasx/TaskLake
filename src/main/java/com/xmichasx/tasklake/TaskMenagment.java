package com.xmichasx.tasklake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan({"com.xmichasx.tasklake.controller", "com.xmichasx.tasklake.configuration", "com.xmichasx.tasklake.repository"})
@EnableJpaRepositories("com.xmichasx.tasklake.repository")
@EnableTransactionManagement
@EntityScan(basePackages= "com/xmichasx/tasklake/model")
public class TaskMenagment {
    public static void main(String[] args) {
        SpringApplication.run(TaskMenagment.class, args);
    }
}