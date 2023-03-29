# README #

This README would normally document whatever steps are necessary to get your application up and running.


## Register

curl -X 'POST' \
'http://localhost:8080/api/auth/register' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"email": "salaesp@gmail.com",
"password": "1234",
"repeatPassword": "1234",
"name": "1234"
}'

## LOGIN

curl -X 'POST' \
'http://localhost:8080/api/auth/login' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
"email": "salaesp@gmail.com",
"password": "1234"
}'