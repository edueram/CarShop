# Stage 1: Build Angular app
FROM node:20 AS builder

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build -- --configuration production

# Stage 2: Serve with nginx
FROM nginx:stable-alpine

# Remove default nginx static files
RUN rm -rf /usr/share/nginx/html/*

# Copy compiled Angular app
COPY --from=builder /app/dist/CarConfigApp/browser /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf

# Copy custom nginx config (optional)
# COPY nginx.conf /etc/nginx/nginx.conf

CMD ["nginx", "-g", "daemon off;"]
