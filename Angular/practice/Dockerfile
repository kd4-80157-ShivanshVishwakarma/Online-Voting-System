# Now we'll serve angular using nginx

FROM nginx:alpine

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY dist/practice/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]