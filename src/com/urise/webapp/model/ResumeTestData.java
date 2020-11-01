package com.urise.webapp.model;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume.ContactSection.CONTACT.addContact("Тел.:", "+7(921) 855-0482");
        Resume.ContactSection.CONTACT.addContact("Skype:", "grigory.kislin");
        Resume.ContactSection.CONTACT.addContact("Почта:", "gkislin@yandex.ru");
        System.out.println(Resume.ContactSection.CONTACT.getContact());
        System.out.println("=========================================================================================");
        System.out.println(Resume.PersonalObjectiveSection.OBJECTIVE.getTitle());
        Resume.PersonalObjectiveSection.OBJECTIVE.setDescription("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        System.out.println(Resume.PersonalObjectiveSection.OBJECTIVE.getDescription());
        System.out.println("=========================================================================================");
        System.out.println(Resume.PersonalObjectiveSection.PERSONAL.getTitle());
        Resume.PersonalObjectiveSection.PERSONAL.setDescription("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        System.out.println(Resume.PersonalObjectiveSection.PERSONAL.getDescription());
        System.out.println("=========================================================================================");
        System.out.println(Resume.AchievementQualificationsSection.ACHIEVEMENT.getTitle());
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        Resume.AchievementQualificationsSection.ACHIEVEMENT.addList("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        System.out.println(Resume.AchievementQualificationsSection.ACHIEVEMENT.getList());
        System.out.println("=========================================================================================");
        System.out.println(Resume.AchievementQualificationsSection.QUALIFICATIONS.getTitle());
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("MySQL, SQLite, MS SQL, HSQLDB");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Python: Django.");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        Resume.AchievementQualificationsSection.QUALIFICATIONS.addList("Родной русский, английский \"upper intermediate\"");
        System.out.println(Resume.AchievementQualificationsSection.QUALIFICATIONS.getList());
        System.out.println("=========================================================================================");
        System.out.println(Resume.ExperienceEducationSection.EXPERIENCE.getTitle());
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Java Online Projects");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("10/2013 - Сейчас", "Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Wrike");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("10/2014 - 01/2016", "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("RIT Center");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("04/2012 - 10/2014", "\tJava архитектор\n" +
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Luxoft (Deutsche Bank)");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("12/2010 - 04/2012", "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Yota");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("06/2008 - 12/2010", "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Enkata");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("03/2007 - 06/2008", "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Siemens AG");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("01/2005 - 02/2007", "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Resume.ExperienceEducationSection.EXPERIENCE.addOrganisation("Alcatel");
        Resume.ExperienceEducationSection.EXPERIENCE.addMap("09/1997 - 01/2005", "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        System.out.println(Resume.ExperienceEducationSection.EXPERIENCE.getList());
        System.out.println("=========================================================================================");
        System.out.println(Resume.ExperienceEducationSection.EDUCATION.getTitle());
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Coursera");
        Resume.ExperienceEducationSection.EDUCATION.addMap("03/2013 - 05/2013", "\"Functional Programming Principles in Scala\" by Martin Odersky");
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Luxoft");
        Resume.ExperienceEducationSection.EDUCATION.addMap("03/2011 - 04/2011", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Siemens AG");
        Resume.ExperienceEducationSection.EDUCATION.addMap("01/2005 - 04/2005", "3 месяца обучения мобильным IN сетям (Берлин)");
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Alcatel");
        Resume.ExperienceEducationSection.EDUCATION.addMap("09/1997 - 03/1998", "6 месяцев обучения цифровым телефонным сетям (Москва)");
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики");
        Resume.ExperienceEducationSection.EDUCATION.addMap("09/1993 - 07/1996", "Аспирантура (программист С, С++)");
        Resume.ExperienceEducationSection.EDUCATION.addMap("09/1987 - 07/1993", "Инженер (программист Fortran, C)");
        Resume.ExperienceEducationSection.EDUCATION.addOrganisation("Заочная физико-техническая школа при МФТИ");
        Resume.ExperienceEducationSection.EDUCATION.addMap("09/1984 - 06/1987", "Закончил с отличием");
        System.out.println(Resume.ExperienceEducationSection.EDUCATION.getList());
    }
}
