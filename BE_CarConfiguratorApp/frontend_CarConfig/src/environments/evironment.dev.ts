//const serverUrl = 'http://ec2-51-21-129-236.eu-north-1.compute.amazonaws.com';

const apiUrl='http://localhost:9000' // development
//const apiUrl='http://gateway-service:9000' //deployment
const keycloakUrl= 'http://localhost:8084' // development


export const environment = {
  production: true,
  apiUrl: apiUrl,
  keycloakUrl: keycloakUrl
};

