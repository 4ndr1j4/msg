# Quick start
 * Create msg directory in Idea projects dir and inside it make msg-services and config-repo directories (optional)
 * inside first dir (/msg/msg-services) execute: git clone git@github.com:4ndr1j4/msg.git
 * inside second one (/msg/config-repo): git clone git@github.com:4ndr1j4/msg-repo.git (optional: for running without containers from IntelliJ)
 * run script from /msg-services: ./[docker-compose-build-all-and-start.sh](docker-compose-build-all-and-start.sh)
 * other times if you want to clean everything(jars,images,networks...) and fresh build all run: [clean-all-and-start.sh](clean-all-and-start.sh) 
 * tests should pass while building jars, go to following links to check does it work as expected:
   * http://localhost:9000/msg/vat-rates/eu/reduced/lowest/3 and
   * http://localhost:9000/msg/vat-rates/eu/standard/highest/3
 * That's it! :)


### REST API Endpoints Documentation

* Api documentation is available on the following links
  *  Swagger: http://localhost:9002/swagger-ui/index.html#/vat-rate-controller-impl
  *  Api docs json: http://localhost:9002/api-docs

* We have two exposed endpoints for now (they should not be exposed in production!):
  * http://localhost:9002/vat-rates/eu/reduced/lowest/3
  * http://localhost:9002/vat-rates/eu/standard/highest/3
* We can also access them using api-gateway(currently host ports are exposed for all services, but we don't need them we can use gateway port):
  * http://localhost:9000/msg/vat-rates/eu/reduced/lowest/3
  * http://localhost:9000/msg/vat-rates/eu/standard/highest/3

* It is posible to monitor our instances using eureka service discovery and registry:
  * http://localhost:9001/

  We have also posibility to use other parameters like Country groups for rates, type of rates, sort and limit returned results, 
  also we can choose what format we will use for country codes.
  It is now hardcoded to these values but our generic api can be easily adopted and modified, actually we have commented out
  our generic api in base controller, just comment it out and you can call for example:
    * http://localhost:9002/vat-rates/eu/reduced/highest/10
    * http://localhost:9002/vat-rates/eu/standard/lowest/2
  Vat-rates service is designed for easy future changes, we can use other rates than standard or reduced, even other ISO or EU standard for country code,
  even for other groups of countries, just we need external api and maybe recreating external response with different fields, other than that our basic controller
  can be just extended, mapper and adapter should be generic and almost all other parts of this app.


### How to start and stop msg-microservices

  * to build all jars,images, network and containers first add execution privilege: 
        chmod +x ./docker-compose-build-all-and-start.sh
      then use script: 
        ./docker-compose-build-all-and-start.sh

  * We can also build all without starting containers with this script(first chmod +x ./docker-compose-build-all-and-start.sh if needed):
    ./docker-compose-build-all.sh

    * And we can stop containers without deleting anything using:
        **docker compose stop**
      or to start: **docker compose start**
      More about docker compose commands we can find using 'docker compose -h' command
      and in docker docs: https://docs.docker.com/compose/reference/

  * to stop and delete everything defined in docker.compose.yml including jars do:
      **chmod +x ./docker-compose-stop-and-remove-all.sh** and then run it:
      **./docker-compose-stop-and-remove-all.sh**
    * When we want to be sure we deleted all(jars,images,volumes...) when we want to freshly restart everything we can run this script that 
      combines previous two : clean-all-and-start.sh (don't forget **chmod +x clean-all-and-start.sh**)

### Running microservices without docker from IntelliJ
 * Just use profile 'DEV' because we will use localhost:port instead of SERVICE_NAME:port in this case
   and run them from main classes, the order is not important because services can be re-registered to eureka

### Config server
   * We use spring security so to curl application.yaml we need to specify
     curl http://username:password@localhost:8888/config/{application-name}/{profile}/[{label-branch}]
     {label-branch} is optional.
     For our microservices we use:
     *  curl http://root:1234@localhost:8888/config/api-gateway/dev/config-repo (without docker container)
        curl http://root:1234@localhost:8888/config/api-gateway/default/config-repo

### Config server
   * Our configuration is in separate repo: msg-repo stored in dir msg/config-repo locally
     when we update configuration.yml for one service we must commit and push it to git 4ndr1ja/msg-repo
     then call  curl -X POST  http://localhost:9002/actuator/refresh for example, 9002 port is used for vat-rates service
     config will be updated without need to reboot service or rebuild jar file
   * About actuator we have exposed only GET /actuator, GET /actuator/health (used for simple way for order of starting services in docker compose,
     no need for complex scripts) and POST /actuator/refresh needed for config server or manual update of properties.

### Where is our code
   * Our code is stored at github: https://github.com/4ndr1j4/msg
   * An config for services: https://github.com/4ndr1j4/msg-repo

   * pull project and repo using https:
     * git clone https://github.com/4ndr1j4/msg.git
     * git clone https://github.com/4ndr1j4/msg-repo.git (needed only if we want to start services from IntelliJ)
   * Or using shh: 
     * git clone git@github.com:4ndr1j4/msg.git and 
     * git clone git@github.com:4ndr1j4/msg-repo.git

### To do
  * There are much things that can be improved:
    * enabling spring security
    * using Vault key-value db for storing sensitive data(instead of git repo)
    * improving logs and adding log aggregation (Elasticsearch, Kibana, Logstash.. for example)
    * we could introduce User microservice with DB (Postgres for example) if we would have more sensitive data
    * making docker images smaller and more secure (we use alpine now and it is good but not best solution)
    * compresing jars to make images smaller...

### Contact info
    
   If you encounter any issues with this project be free to contact me: 
   * 1andrija.petrovic@gmail.com
   * +381611889684
