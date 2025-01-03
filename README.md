# javaappmigratedtojakarta
Created by: Luis Camilo Peña R.
https://www.linkedin.com/in/camilo-pe-reyes-bogota-co/
AppValidation is a java maven War, deployed as a rest API using java 1.7 (one dot seven) or java 7 an old version of java, its also optimized to be deployed on glassfish servers.
AppValidationJakarta is a Java Springboot maven war, upgraded to Java 17(Seventeen) and from javax to jakarta, it provides the same functions than AppValidation and also was modified to be able tu run into glassfish servers. an important thing here, the file src/main/webapp/WEB-INF
/beans.xml is necessary even being empty, otherwise the application wont deploy on the server.

To generate the war file use the next maven command: clean install compile package -e -X -U -Dskiptests
