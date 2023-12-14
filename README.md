# Privacy-tool-backend

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