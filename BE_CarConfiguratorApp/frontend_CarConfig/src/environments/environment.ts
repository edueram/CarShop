//const serverUrl = 'http://ec2-51-21-129-236.eu-north-1.compute.amazonaws.com';

//const apiUrl='http://gateway-service:9000' //deployment
const apiUrl='http://localhost:9000' //deployment
//const keycloakUrl= 'http://keycloak:8080' // deployment
const keycloakUrl= 'http://localhost:8080' // deployment

export const environment = {
  production: true,
  apiUrl: apiUrl,
  keycloakUrl: keycloakUrl
};

