//const serverUrl = 'http://ec2-51-21-129-236.eu-north-1.compute.amazonaws.com';
const serverUrl = 'http://localhost';

export const environment = {
  production: true,
  apiUrl: `${serverUrl}:9000`,
  keycloakUrl: `${serverUrl}:8084`
};

