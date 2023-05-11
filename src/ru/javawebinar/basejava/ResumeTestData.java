package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

       resume.addContact(ContactType.PHONE, "+7 (921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения\n" +
                "по Java Web и Enterprise технологиям"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика,\n" +
                "креативность, инициативность. Пурист кода и архитектуры."));


        List<String> achievements = new ArrayList<>();
        achievements.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков:\n" +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов\n" +
                "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для\n" +
                "комплексных DIY смет");
        achievements.add("\nС 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\",\n" +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное\n" +
                "взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        achievements.add("\nРеализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.\n" +
                "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("\nНалаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция\n" +
                "с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: \n" +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция\n" +
                "CIFS/SMB java сервера.");
        achievements.add("\nРеализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring,\n" +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievements.add("\nСоздание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов\n" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии\n" +
                "через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга \n" +
                "системы по JMX (Jython/ Django).");
        achievements.add("\nРеализация протоколов по приему платежей всех основных платежных системы России (Cyberplat,\n" +
                "Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievements));


        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("\nVersion control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("\nDB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite,\n" +
                "MS SQL, HSQLDB");
        qualifications.add("\nLanguages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("\nXML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("\nJava Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC,\n" +
                "Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin,\n" +
                "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("\nPython: Django.");
        qualifications.add("\nJavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("\nScala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("\nТехнологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM,\n" +
                "XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, \n" +
                "OAuth2, JWT.");
        qualifications.add("\nИнструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.add("\nадминистрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios,\n" +
                "iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.add("\nОтличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования,\n" +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.add("\nРодной русский, английский \"upper intermediate\"");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));


        List<Organization> companies = new ArrayList<>();
        List<Organization.Period> periodsOfWork = new ArrayList<>();
        periodsOfWork.add(new Organization.Period(
                "Автор проекта",
                DateUtil.of(2013, Month.OCTOBER),
                LocalDate.now(),
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        companies.add(new Organization("Java Online Projects", "https://javaops.ru/", periodsOfWork));

        periodsOfWork.clear();
        periodsOfWork.add(new Organization.Period(
                "Старший разработчик (backend)",
                DateUtil.of(2014, Month.OCTOBER),
                DateUtil.of(2016, Month.JANUARY),
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, \n" +
                        "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, \n" +
                        " авторизация по OAuth1, OAuth2, JWT SSO."));
        companies.add(new Organization("Wrike", "https://www.wrike.com/", periodsOfWork));

        periodsOfWork.clear();
        periodsOfWork.add(new Organization.Period(
                "Java архитектор",
                DateUtil.of(2012, Month.APRIL),
                DateUtil.of(2014, Month.OCTOBER),
                "Организация процесса разработки системы ERP для разных окружений: релизная политика,\n" +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование\n" +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка\n" +
                        "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта,\n" +
                        "экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера\n" +
                        "документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security,\n" +
                        "Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote\n" +
                        "scripting via ssh tunnels, PL/Python"));
        companies.add(new Organization("RIT Center", null, periodsOfWork));


        List<Organization.Period> periodsOfStudy = new ArrayList<>();
        periodsOfStudy.add(new Organization.Period(
                "Ведущий программист",
                DateUtil.of(2010, Month.DECEMBER),
                DateUtil.of(2012, Month.APRIL),
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT,\n" +
                        "GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения\n" +
                        "для администрирования, мониторинга и анализа результатов в области алгоритмического\n" +
                        "трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        companies.add(new Organization("Luxoft (Deutsche Bank)", "https://www.luxoft.ru/", periodsOfWork));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Ведущий специалист",
                DateUtil.of(2008, Month.JUNE),
                DateUtil.of(2010, Month.DECEMBER),
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1,\n" +
                        "v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация\n" +
                        "администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента\n" +
                        "(Python/ Jython, Django, ExtJS)"));
        companies.add(new Organization("Yota", "https://www.yota.ru/", periodsOfWork));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Разработчик ПО",
                DateUtil.of(2007, Month.MARCH),
                DateUtil.of(2008, Month.JUNE),
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS)\n" +
                        " частей кластерного J2EE приложения (OLAP, Data mining)."));
        companies.add(new Organization("Enkata", "http://enkata.com/", periodsOfWork));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Разработчик ПО",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2007, Month.FEBRUARY),
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на\n" +
                        " мобильной IN платформе Siemens @vantage (Java, Unix)."));
        companies.add(new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", periodsOfWork));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Инженер по аппаратному и программному тестированию",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2005, Month.JANUARY),
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL,\n" +
                        " ASM)."));
        companies.add(new Organization("Alcatel", "http://www.alcatel.ru/", periodsOfWork));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(companies));


        List<Organization> educationalOrganizations = new ArrayList<>();
        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Functional Programming Principles in Scala' by Martin Odersky",
                DateUtil.of(2013, Month.MARCH),
                DateUtil.of(2013, Month.MAY),
                null));
        educationalOrganizations.add(new Organization("Coursera", "https://www.coursera.org/learn", periodsOfStudy));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Курс Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.",
                DateUtil.of(2011, Month.MARCH),
                DateUtil.of(2011, Month.APRIL),
                null));
        educationalOrganizations.add(new Organization("Luxoft", "http://www.luxoft-training.ru", periodsOfStudy));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "3 месяца обучения мобильным IN сетям (Берлин)",
                DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2005, Month.APRIL),
                null));
        educationalOrganizations.add(new Organization("Siemens AG", "http://www.siemens.ru/", periodsOfStudy));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(1998, Month.MARCH),
                null));
        educationalOrganizations.add(new Organization("Alcatel", "http://www.alcatel.ru/", periodsOfStudy));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Аспирантура (программист С, С++)",
                DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY),
                null));
        periodsOfStudy.add(new Organization.Period(
                "Инженер (программист Fortran, C)",
                DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY),
                null));
        educationalOrganizations.add(new Organization("Санкт-Петербургский национальный исследовательский университет",
                "https://itmo.ru/", periodsOfStudy));

        periodsOfStudy.clear();
        periodsOfStudy.add(new Organization.Period(
                "Закончил с отличием",
                DateUtil.of(1984, Month.SEPTEMBER),
                DateUtil.of(1987, Month.JUNE),
                null));
        educationalOrganizations.add(new Organization("Заочная физико-техническая школа при МФТИ",
                "https://mipt.ru/", periodsOfStudy));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(educationalOrganizations));

       /* for (ContactType type : ContactType.values()) {
            System.out.print("" + type.getTitle() + " : ");
            System.out.println(resume.getContact(type));
        }
        System.out.println();
        for (SectionType type : SectionType.values()) {
            System.out.println("" + type.getTitle() + " : ");
            System.out.println(resume.getSection(type));
            System.out.println();
        }*/
        return resume;
    }
}
