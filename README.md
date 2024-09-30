# tributa-perpetuum

This project is a demo service which illustrates how a taxation rest service could work. 
It is in no way perfect, some corner cases could have been handled better (what happens if tax eats all
my earnings?), there should be an admin endpoint allowing to edit taxation types, rates, amounts for different
locations. But it does illustrate how i think such a service should begin its life.

There is one REST endpoint in the `TributaResource` class. It uses JWT authentication. The JWT access token
can be obtained by running the `GenerateToken` test class. It will return an access token for a random `Trader`.
The service uses this simple way of generating an access token because in production whis would be done with 
Keycloak or similar identity provider.

I allowed myself to change the API a bit, mostly I am using UUID as ids (also for traderId) and I'm extracting that from
the JWT token instead of using the value in the request. This is of course open for discussion and totally dependent
on the use case.

Service demonstration is in `TributaResourceTest`. It will run a short set of tax calculations for different traders.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/tributa-perpetuum-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
