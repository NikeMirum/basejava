package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResumeTestData {

    public static Resume createResume(String uuid, String fullName) {
        Resume result = new Resume(uuid, fullName);

        result.addContact(ContactType.PHONE_NUMBER, "79112223344");
        result.addContact(ContactType.SKYPE, "skype");
        result.addContact(ContactType.EMAIL, "mail@yandex.ru");
        result.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/linkedin");
        result.addContact(ContactType.GITHUB, "https://github.com/github");
        result.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/stackoverflow");
        result.addContact(ContactType.HOMEPAGE, "http://homepage.ru/");

//        result.addSection(SectionType.OBJECTIVE, new TextSection("Objective"));
//        result.addSection(SectionType.PERSONAL, new TextSection("Personal"));
//
//        List<String> achievements = new LinkedList<>();
//        achievements.add("Achievement1");
//        achievements.add("Achievement2");
//        achievements.add("Achievement3");
//        result.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));
//
//        List<String> qualifications = new ArrayList<>();
//        qualifications.add("Qualification1");
//        qualifications.add("Qualification2");
//        qualifications.add("Qualification3");
//        result.addSection(SectionType.ACHIEVEMENT, new ListSection(qualifications));
//
//        List<Organization> organization = new ArrayList<>();
//        organization.add(new Organization("Organization1",
//                "https://www.organization1.com/",
//                new Organization.Position(DateUtil.of(2021, 1),
//                        DateUtil.NOW,
//                        "java-developer1",
//                        "develop1")));
//        organization.add(new Organization("Organization2",
//                "https://www.organization2.com/",
//                new Organization.Position(DateUtil.of(2016, 1),
//                        DateUtil.of(2021, 1),
//                        "java-developer2",
//                        "develop2")));
//        organization.add(new Organization("Organization3",
//                null,
//                new Organization.Position(DateUtil.of(2011, 1),
//                        DateUtil.of(2016, 1),
//                        "java-developer3",
//                        "develop3")));
//        result.addSection(SectionType.EXPERIENCE, new OrganizationSection(organization));
//
//        List<Organization> education = new ArrayList<>();
//        education.add(new Organization("education1",
//                "https://www.education1.org/course/",
//                new Organization.Position(DateUtil.of(2021, 1),
//                        DateUtil.NOW,
//                        "experience1",
//                        null)));
//        education.add(new Organization("education2",
//                "http://www.education2.ru/",
//                new Organization.Position(DateUtil.of(2006, 1),
//                        DateUtil.of(2011, 1),
//                        "experience2",
//                        null),
//                new Organization.Position(DateUtil.of(2001, 1),
//                        DateUtil.of(2011, 1),
//                        "experience3",
//                        null)));
//        result.addSection(SectionType.EDUCATION, new OrganizationSection(education));

        return result;
    }

    public static void main(String[] args) {
        Resume test = new Resume("Григорий Кислин");

        test.addContact(ContactType.PHONE_NUMBER, "79218550482");
        test.addContact(ContactType.SKYPE, "grigory.kislin");
        test.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        test.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        test.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        test.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        test.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        test.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        test.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievements = new LinkedList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievements.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievements.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        test.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualifications.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualifications.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualifications.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("Родной русский, английский \"upper intermediate\"");
        test.addSection(SectionType.ACHIEVEMENT, new ListSection(qualifications));

        List<Organization> organization = new ArrayList<>();
        organization.add(new Organization("Java Online Projects",
                "http://javaops.ru/",
                new Organization.Position(DateUtil.of(2013, 10),
                        DateUtil.NOW,
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        organization.add(new Organization("Wrike",
                "https://www.wrike.com/",
                new Organization.Position(DateUtil.of(2014, 10),
                        DateUtil.of(2016, 1),
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        organization.add(new Organization("RIT Center",
                null,
                new Organization.Position(DateUtil.of(2012, 4),
                        DateUtil.of(2014, 10),
                        "Java архитектор",
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")));
        organization.add(new Organization("Luxoft (Deutsche Bank)",
                "http://www.luxoft.ru/",
                new Organization.Position(DateUtil.of(2010, 12),
                        DateUtil.of(2012, 4),
                        "Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")));
        organization.add(new Organization("Yota",
                "https://www.yota.ru/",
                new Organization.Position(DateUtil.of(2008, 6),
                        DateUtil.of(2010, 12),
                        "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")));
        organization.add(new Organization("Enkata",
                "http://enkata.com/",
                new Organization.Position(DateUtil.of(2007, 3),
                        DateUtil.of(2008, 6),
                        "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")));
        organization.add(new Organization("Siemens AG",
                "https://www.siemens.com/ru/ru/home.html",
                new Organization.Position(DateUtil.of(2005, 1),
                        DateUtil.of(2007, 2),
                        "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")));
        organization.add(new Organization("Alcatel",
                "http://www.alcatel.ru/",
                new Organization.Position(DateUtil.of(1997, 9),
                        DateUtil.of(2005, 1),
                        "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")));
        test.addSection(SectionType.EXPERIENCE, new OrganizationSection(organization));

        List<Organization> education = new ArrayList<>();
        education.add(new Organization("Coursera",
                "https://www.coursera.org/course/progfun",
                new Organization.Position(DateUtil.of(2013, 3),
                        DateUtil.of(2013, 5),
                        "\"Functional Programming Principles in Scala\" by Martin Odersky",
                        null)));
        education.add(new Organization("Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                new Organization.Position(DateUtil.of(2011, 3),
                        DateUtil.of(2011, 4),
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                        null)));
        education.add(new Organization("Siemens AG",
                "http://www.siemens.ru/",
                new Organization.Position(DateUtil.of(2005, 1),
                        DateUtil.of(2005, 4),
                        "3 месяца обучения мобильным IN сетям (Берлин)",
                        null)));
        education.add(new Organization("Alcatel",
                "http://www.alcatel.ru/",
                new Organization.Position(DateUtil.of(1997, 9),
                        DateUtil.of(2005, 1),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)",
                        null)));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/",
                new Organization.Position(DateUtil.of(1993, 9),
                        DateUtil.of(1996, 7),
                        "Аспирантура (программист С, С++)",
                        null),
                new Organization.Position(DateUtil.of(1987, 9),
                        DateUtil.of(1993, 7),
                        "Инженер (программист Fortran, C)",
                        null)));
        education.add(new Organization("Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/",
                new Organization.Position(DateUtil.of(1984, 9),
                        DateUtil.of(1987, 6),
                        "Закончил с отличием",
                        null)));
        test.addSection(SectionType.EDUCATION, new OrganizationSection(education));

        System.out.println(test);
    }
}