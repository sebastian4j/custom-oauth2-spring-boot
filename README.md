POST:
http://localhost:8080/oauth/token

x-www-form-urlencoded:

```
grant_type=client_credentials
client_id=savila
username=savila
password=savila
```

----


POST 
http://localhost:8080/oauth/introspect

x-www-form-urlencoded:
```
token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXZpbGEiLCJwcmYiOlsxMSwyMiwzM10sInJvbGVzIjpbImEiLCJiIiwiYyJdLCJpc3MiOiJTZWJhc3Rpw6FuIMOBdmlsYSBTLkEuIiwiZ3JvdXBzIjpbImEiLCJiIiwiYyJdLCJwcm0iOlsxLDIsM10sInR5cCI6IkJlYXJlciIsImNsdCI6WzM0LDksOTBdLCJzY29wZSI6WyJjdXN0b21fbW9kIl0sImV4cCI6MTU3NDI1MzA5MSwiaWF0IjoxNTc0MjE3MDkxLCJqdGkiOiIzYjc2NWZkYy0zMTRiLTRkMDItYTdjYi0xOGM3MjkwMGY5ZDAiLCJubSI6IlNlYmFzdGlhbiBBdmlsYSBBLiJ9.vJ_qliWgzvZ0eTlB3FMen38MMZD-jYFX5Nq352SqhNka
```

```
{
  "sub": "86dc8999-45f6-46e1-ae4e-d0ff85b78d5f",
  "email_verified": false,
  "iss": "https://server",
  "custom:id": "4035",
  "active": true,
  "phone_number_verified": false,
  "clt": [
    1808,
    161
  ],
  "cognito:username": "savila",
  "aud": "2p68s4ofsh7316ohiguem0ifir",
  "hbl": "true",
  "event_id": "f8c2aad2-62b8-4fe0-9def-85887460c79a",
  "token_use": "id",
  "scope": [
    "scope-1",
    "1:P:2",
    "1:F:1",
    "1:P:7",
    "1:P:8",
    "1:P:5",
    "1:P:6",
    "1:P:3",
    "1:P:4"
  ],
  "auth_time": 1579003813,
  "name": "savila",
  "exp": 1579007413,
  "iat": 1579003813,
  "family_name": "savila",
  "email": "sss@sss.cl"
}
```

sin token:
```
http://localhost:8081/no-seguro
http://localhost:8082/publico/chao
```

usando bearer token:
```
http://localhost:8082/privado/hola
...
```

no autorizado:
```
http://localhost:8081/roles
```


