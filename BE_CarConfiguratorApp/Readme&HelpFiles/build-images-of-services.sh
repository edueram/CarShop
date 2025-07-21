#!/bin/bash
# Diese Datei muss in Root-Ordner sein
# Dieses Skript startet alle Services auf einmal --- Sonst kannst du einzelne Service durch:
# Beispiel: docker build -t order-service:latest ./order-service

services=(order-service car-service carOption-service notification-service api-gateway inventory-service)

for service in "${services[@]}"; do
  echo "Building $service"
  docker build -t "$service:latest" "./$service"
done
