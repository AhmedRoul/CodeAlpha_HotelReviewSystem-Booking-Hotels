# Stage 1: Build the React app
FROM node:18 as build-stage

WORKDIR /app

COPY package*.json ./

# Use --legacy-peer-deps flag to resolve dependency conflicts
RUN npm install --legacy-peer-deps

COPY . .

RUN npm run build --prod

# Stage 2: Serve the app with Nginx
FROM nginx:alpine

COPY nginx.conf /etc/nginx/nginx.conf

COPY --from=build-stage /app/dist/hotel-booking-ui /usr/share/nginx/html

EXPOSE 80
