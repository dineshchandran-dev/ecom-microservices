#!/bin/bash

echo "Starting ecom-microservices-user..."
mvn -pl ecom-microservices-user spring-boot:run &
USER_PID=$!

echo "Starting ecom-microservices-product..."
mvn -pl ecom-microservices-product spring-boot:run &
PRODUCT_PID=$!

echo "Starting ecom-microservices-order..."
mvn -pl ecom-microservices-order spring-boot:run &
ORDER_PID=$!

echo ""
echo "All services started:"
echo "  User Service PID: $USER_PID"
echo "  Product Service PID: $PRODUCT_PID"
echo "  Order Service PID: $ORDER_PID"
echo ""
echo "To stop all services, press CTRL+C."
wait
