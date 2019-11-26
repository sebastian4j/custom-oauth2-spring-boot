POST
http://localhost:8080/oauth/token

grant_type=client_credentials&client_id=savila&username=savila&password=savila

----


POST 
http://localhost:8080/oauth/introspect

token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXZpbGEiLCJwcmYiOlsxMSwyMiwzM10sInJvbGVzIjpbImEiLCJiIiwiYyJdLCJpc3MiOiJTZWJhc3Rpw6FuIMOBdmlsYSBTLkEuIiwiZ3JvdXBzIjpbImEiLCJiIiwiYyJdLCJwcm0iOlsxLDIsM10sInR5cCI6IkJlYXJlciIsImNsdCI6WzM0LDksOTBdLCJzY29wZSI6WyJjdXN0b21fbW9kIl0sImV4cCI6MTU3NDI1MzA5MSwiaWF0IjoxNTc0MjE3MDkxLCJqdGkiOiIzYjc2NWZkYy0zMTRiLTRkMDItYTdjYi0xOGM3MjkwMGY5ZDAiLCJubSI6IlNlYmFzdGlhbiBBdmlsYSBBLiJ9.vJ_qliWgzvZ0eTlB3FMen38MMZD-jYFX5Nq352SqhNka

{
  "client_id"                  : "client_id",
  "active"  : true,
  "scope"                      : ["custom_mod"]
}


/home/sebastian/java/workspace/spring/spring-boot-client-credentials-example/creds-example-server
http://localhost:8081/role
http://localhost:8081/auth
http://localhost:8081/mod


