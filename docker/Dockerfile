FROM maven:3.8.5-jdk-11
MAINTAINER 'GMS.GILBERTRO'
COPY gms-banks.jar /usr/share/banks.app.jar
WORKDIR /usr/share/
ARG BANKS_PROFILE=local
ENV BANKS_PROFILE=${BANKS_PROFILE}
CMD java -jar /usr/share/banks.app.jar -Dspring.profiles.active=$BANKS_PROFILE
EXPOSE 8080