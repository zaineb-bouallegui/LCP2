FROM node:18.16.1 as build

# Répertoire de travail
WORKDIR /app

# Copiez les fichiers de l'application dans le conteneur
COPY . .

# Installez les dépendances
RUN npm install

# Construisez l'application Angular
RUN npm run build

# Utilisez une image de base Nginx pour servir l'application
FROM nginx:alpine

# Copiez les fichiers construits de l'application Angular dans le répertoire d'Nginx
COPY --from=build /app/dist/admin-template-app /usr/share/nginx/html/

