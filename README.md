# javaappmigratedtojakarta
Created by: Camilo P. R.

AppValidation is a Java Maven WAR application, deployed as a REST API using Java 1.7 (Java 7), an older version of Java. It is optimized to run on GlassFish servers.

AppValidationJakarta is an upgraded version of AppValidation. It is a Java Spring Boot Maven WAR, migrated to Java 17 and transitioned from javax to jakarta. It provides the same functionality as AppValidation but has been modified to ensure compatibility with GlassFish servers.

Important Note: The file src/main/webapp/WEB-INF/beans.xml is required, even if it is empty. Without it, the application will not deploy on the server.

To generate the WAR file, use the following Maven command:

mvn clean install compile package -e -X -U -DskipTests
in visual studio code select custom maven commands and insert: clean install compile package -e -X -U -DskipTests
