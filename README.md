# Privacy-tool-backend

### Docs:
- [Overview](https://docs.google.com/document/d/1S6S1KQrq_viWVEJ1qPQtyqYV57L7UAoWvxt4jd5hKKg/edit#heading=h.9q0iehx7oib0)
- [Encryption Approach](https://quip.com/b5p7Ao0oYkN7/Encryption-Approach)

### Docker build:
Pre-Requisite: mavel install (target/app.jar should exist)
```shell
docker build -t privacy-tool .
```

### Docker run:
Container port: 80
Host port: 8080
```shell
docker run -p 8080:80 privacy-tool
```

### AWS CLI Setup
OS: Mac
```shell
brew install awscli
aws configure
```