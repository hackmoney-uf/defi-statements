## DeFi Statements

### Frontend
All commands executed in `frontend` directory

Install dependencies:
```shell
yarn
```
Run local:
```shell
yarn dev
```

### Backend
All commands executed in `backend` directory

Requirements:
* `Java 17`
* `Docker`

1. Update config params in `src/main/java/org/example/Main.java`
```Java
public static final String COVALENT_API_KEY="covalent api key";
public static final String NODE_PROVIDER_URL="node provider url";
public static final String CONTRACT_ADDRESS="address of deployed contract";
public static final String APP_PRIVATE_KEY="private key of the app";
public static final int CHAIN_ID=80001; // id of the chain to work with
public static final String IPFS_USER="username for IPFS provider";
public static final String IPFS_PASSWORD="password for IPFS provider";
public static final String IPFS_UPLOAD_URL="IPFS upload url";
```

2. To run locally
```shell
# build the app
./gradlew clean build shadowJar
# build docker image
docker build -f Dockerfile -t 'block-rep' ./
# run the app via docker
docker run block-rep java -jar app.jar
```

### Contracts
All commands executed in `contract` directory
Install dependencies:
```shell
yarn
```
Run tests:
```shell
yarn test
```
Compile:
```shell
yarn build
```
Deploy
```shell
yarn deploy --network [localhost|polygonMumbai]
```