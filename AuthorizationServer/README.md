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
#V25.01.000
#### Response
```json
{
    "access_token": "eyJraWQiOiIzNGEzNTUzMS00ZDA5LTRmNTUtOWZhOS04MzhlNmRkMmU1ZmQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0LWNsaWVudCIsImF1ZCI6InRlc3QtY2xpZW50IiwibmJmIjoxNzM1OTM3NTQ1LCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzM1OTU1NTQ1LCJpYXQiOjE3MzU5Mzc1NDUsImp0aSI6IjE0YzQ5ZTRkLWE3ZGItNDE3Yi1iZGMzLWJmNGRmN2E2MDkzNCJ9.d9M66CxpTqbwmP5HKmiUSrIt1hvHN3qtw-4MCrogJhsC91BrOxfnbabjt5MgT3mxFXQSzOHKgcx73E186bCS0Bm-KfnV3tU64g3pGA4YWgSmjOgNMIfa-uDchsfcpPhZSjNB_sYNP8VnmIAhspwLHcZrMarIkiCvl6CyfK59LthvZ35asT_lNVbHlY66fgt3xXqy1djOoMZKsbnCTAoeqxPPSoYLxND0cfXePGDV9dB5Iu4TlKR60ZQ5ja6RqmEeJUFyghphTeg3ChppH9kJR5112IWbdWl8O5rHDU45l-zjAZTHP9yMUALdsa-f-i06TjuvdGcuaQFqt3nV7TEAjQ",
    "scope": "read write",
    "token_type": "Bearer",
    "expires_in": 17999
}
```
#V25.01.001
#### Response
```json
{
    "access_token": "yJraWQiOiIyYjY0Y2JiNy03NjY3LTRhOGUtODdlYi0zYjQ1NzA4MDk0YzMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0LWNsaWVudCIsImF1ZCI6InRlc3QtY2xpZW50IiwibmJmIjoxNzM2MDEyNDIxLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwicm9sZXMiOlsiQ0xJRU5UX0NSRUFUT1IiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzM2MDMwNDIxLCJ0b2tlbl90eXBlIjoiQmVhcmVyIiwiaWF0IjoxNzM2MDEyNDIxLCJqdGkiOiI4ZGVkMjA0MC04ZDdkLTQwMWItOWM1Ny01NmFkYzA1MjY4YjkiLCJ0aW1lc3RhbXAiOiIyMDI1LTAxLTA0VDE3OjQwOjIxLjYwNDE2MFoifQ.e-jC12oofaBeNeF_aCAGvjAJclUghvdJS3LEKSk60Y1Yf411vmxpIQ6eukqiVpVH-S8ODuTx6jLLap0NTrigFbuIr5ZlBw2uIdilu3BlEWXV_-LsE2Cz5932310ixOEftohu2iz1RkrqrKEXgdkkjFEzcqAgmcznao0VPnOigMCURKLviZPaFOxIQPwanwbakeUJCqNcP0NDHm4UQADBF8TGlVqXz4EJhX9rpp9aA3YT6xf1DUtjqQGhkM_gGlfeAf_7PMQ4Ui9UMuZx3fnEOGpMR62re9z1nFfW1usszAL7P2FHdqQyNFV_baEYs6WU5xTA3mcJrMki39DUkHcatge",
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
The application runs on port 9000 by default. You can modify this in `application.yml`.

## Security
- Uses Basic Authentication for client credentials
- Supports JWT tokens
- Implements scope-based access control

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
For more information, please refer to the [Spring Authorization Server documentation](https://docs.spring.io/spring-authorization-server/docs/current/reference/html/).

# OAuth2 Resource Server

This service serves as a Spring Boot Resource Server application.

The project includes a controller for client registration, which requires the `CLIENT_CREATOR` authority to access.

The project can be configured using the data in the `src/main/resources` folder, where all necessary startup settings are provided.