# OpenLiberty
#FROM open-liberty:microProfile2
#ADD build/libs/myservice.war /config/dropins
#COPY src/main/liberty/config /config/

# Wildfly
FROM jboss/wildfly
ADD build/libs/myservice.war /opt/jboss/wildfly/standalone/deployments/