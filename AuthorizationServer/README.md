# OAuth2 Authorization Server

This service can be used as a Spring Boot Authorization Server application.

## Getting Started

### Database Setup
SQL schema and test data insert scripts are available in the `src/resources` directory.

### Authentication Example

#### Request
```bash
curl --location 'localhost:9000/oauth2/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic dGVzdC1jbGllbnQ6c2VjcmV0' \
--header 'Cookie: JSESSIONID=C9901EBE35D846D100DD18B68D4BBAE8' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=read write'
```

#### Response
```json
{
    "access_token": "eyJraWQiOiIzNGEzNTUzMS00ZDA5LTRmNTUtOWZhOS04MzhlNmRkMmU1ZmQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0LWNsaWVudCIsImF1ZCI6InRlc3QtY2xpZW50IiwibmJmIjoxNzM1OTM3NTQ1LCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzM1OTU1NTQ1LCJpYXQiOjE3MzU5Mzc1NDUsImp0aSI6IjE0YzQ5ZTRkLWE3ZGItNDE3Yi1iZGMzLWJmNGRmN2E2MDkzNCJ9.d9M66CxpTqbwmP5HKmiUSrIt1hvHN3qtw-4MCrogJhsC91BrOxfnbabjt5MgT3mxFXQSzOHKgcx73E186bCS0Bm-KfnV3tU64g3pGA4YWgSmjOgNMIfa-uDchsfcpPhZSjNB_sYNP8VnmIAhspwLHcZrMarIkiCvl6CyfK59LthvZ35asT_lNVbHlY66fgt3xXqy1djOoMZKsbnCTAoeqxPPSoYLxND0cfXePGDV9dB5Iu4TlKR60ZQ5ja6RqmEeJUFyghphTeg3ChppH9kJR5112IWbdWl8O5rHDU45l-zjAZTHP9yMUALdsa-f-i06TjuvdGcuaQFqt3nV7TEAjQ",
    "scope": "read write",
    "token_type": "Bearer",
    "expires_in": 17999
}
```

## Features
- OAuth2 token generation
- Client credentials flow support
- Configurable token expiration
- Scope-based authorization

## Requirements
- Java 17 or higher
- Spring Boot 3.x
- H2 Database (for development)

## Configuration
The application runs on port 9000 by default. You can modify this in `application.properties`.

## Security
- Uses Basic Authentication for client credentials
- Supports JWT tokens
- Implements scope-based access control

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
For more information, please refer to the [Spring Authorization Server documentation](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/).
