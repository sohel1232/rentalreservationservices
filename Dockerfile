FROM openjdk:17
EXPOSE 8080
ADD target/rentalreservationservices-0.0.1.jar rentalreservationservices-0.0.1.jar
ENTRYPOINT ["java","-jar","rentalreservationservices-0.0.1.jar"]