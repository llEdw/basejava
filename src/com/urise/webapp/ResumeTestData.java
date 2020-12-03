package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ResumeTestData {
   public static Resume fillResume(String uuid, String fullName) {
       Resume resume = new Resume(uuid, fullName);

       resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
       resume.addContact(ContactType.SKYPE, "grigory.kislin");
       resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
       resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
       resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
       resume.addContact(ContactType.STATCKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");

       resume.addSection(SectionType.OBJECTIVE, new TextSection
           ("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

       resume.addSection(SectionType.PERSONAL, new TextSection
           ("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

       resume.addSection(SectionType.ACHIEVEMENT, new ListSection(new ArrayList<>(Arrays.asList
           ("\b\u2022С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
               "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
               "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
           "\n\u2022Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
               "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
           "\n\u2022Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, " +
               "CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. " +
               "Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
           "\n\u2022Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, " +
               "ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
           "\n\u2022Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
               "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. " +
               "Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
           "\n\u2022Реализация протоколов по приему платежей всех основных платежных системы России " +
               "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."))));

       resume.addSection(SectionType.QUALIFICATIONS, new ListSection(new ArrayList<>(Arrays.asList
           ("\b\u2022JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
           "\n\u2022Version control: Subversion, Git, Mercury, ClearCase, Perforce",
           "\n\u2022DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,",
           "\n\u2022MySQL, SQLite, MS SQL, HSQLDB",
           "\n\u2022Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,",
           "\n\u2022XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,",
           "\n\u2022Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot)," +
               " JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, " +
               "Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
           "\n\u2022Python: Django.",
           "\n\u2022JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js", "\nScala: SBT, Play2, Specs2, Anorm, Spray, Akka",
           "\n\u2022Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB," +
               " JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
           "\n\u2022Инструменты: Maven + plugin development, Gradle, настройка Ngnix,", "\nадминистрирование Hudson/Jenkins, " +
               "Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
           "\n\u2022Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
           "\n\u2022Родной русский, английский \"upper intermediate\""))));

       resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new ArrayList<>(Arrays.asList
           (new Organization("Java Online Projects", "https://javaops.ru/",new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2013, 10, 1), LocalDate.now(),
                    "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")))),
           new Organization("Wrike", "https://www.wrike.com/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2014, 10, 1),
                    LocalDate.of(2016, 1, 1), "Старший разработчик (backend)",
                    "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                     "(Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                     "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")))),
           new Organization("RIT Center", null, new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2012, 4, 1),
                    LocalDate.of(2014, 10, 1), "Java архитектор",
                    "Организация процесса разработки системы ERP для разных окружений: " +
                    "релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                    "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. " +
                    "Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения " +
                    "(почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. " +
                    "Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, " +
                    "Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")))),
           new Organization("Luxoft (Deutsche Bank)", "https://career.luxoft.com/locations/russia/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2010, 12, 1),LocalDate.of(2012, 4, 1),
                   "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, " +
                   "GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                   "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. " +
                   "JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")))),
           new Organization("Yota", "https://www.yota.ru/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2008, 6, 1),
                    LocalDate.of(2010, 12, 1),
                   "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы" +
                   "\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                   "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")))),
           new Organization("Enkata", "https://www.pega.com/products/robotic-process-automation", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2007, 3, 1),
                    LocalDate.of(2008, 6, 1), "Разработчик ПО",
                    "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей " +
                    "кластерного J2EE приложения (OLAP, Data mining).")))),
           new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2005, 1, 1),
                    LocalDate.of(2007, 2, 1), "Разработчик ПО",
                    "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")))),
           new Organization("Alcatel", "http://www.alcatel.ru/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(1997, 9, 1),
                    LocalDate.of(2005, 1, 1),
                   "Инженер по аппаратному и программному тестированию",
                   "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))))))));

       resume.addSection(SectionType.EDUCATION, new OrganizationSection(new ArrayList<>(Arrays.asList
            (new Organization("Coursera", "https://www.coursera.org/learn/progfun1", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2013, 3, 1),
                    LocalDate.of(2013, 5, 1), null,
                    "\"Functional Programming Principles in Scala\" by Martin Odersky")))),
            new Organization("Luxoft", "https://www.luxoft-training.ru", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2011, 3, 1),
                    LocalDate.of(2011, 4, 1),
                    null, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")))),
            new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(2005, 1, 1),
                    LocalDate.of(2005, 4, 1), null, "3 месяца обучения мобильным IN сетям (Берлин)")))),
            new Organization("Alcatel", "http://www.alcatel.ru/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(1997, 9, 1),
                    LocalDate.of(1998, 3, 1), null, "6 месяцев обучения цифровым телефонным сетям (Москва)")))),
            new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                    "https://itmo.ru/ru/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1),
                   null, "Аспирантура (программист С, С++)"),
                    new OrganizationDescription(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1),
                   null, "Инженер (программист Fortran, C)")))),
            new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", new ArrayList<>(Arrays.asList(
                    new OrganizationDescription(LocalDate.of(1984, 9, 1),
                    LocalDate.of(1987, 6, 1), null, "Закончил с отличием"))))))));
       return resume;
   }
}