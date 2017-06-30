# demo
Demo

# Start the application
mvn spring-boot:run

# Verify
# This returns a html saying 'It works'
http://localhost:8080/api/v1/
# This returns a JSON showing 2 customer records
http://localhost:8080/api/v1/customer/

# Start the application
mvn spring-boot:run

# Setup Selenium stand alone v 3.4.0 in local and start
java -jar selenium-server-standalone-3.4.0.jar -port 4445

# Run the tests
mvn test
