Mix and Match Backend
=====================

[toc]

Project Setup
=============

Maven Build
-----------

The maven build provides the following two profiles:

 - stage-dev
 - stage-prod

stage-dev is configured as default which means that

    mvn package|install...

is similar to:

    mvn package|intstall -Pstage-dev

Build cmds (just package):

    mvn clean package -Pstage-dev
    mvn clean package -Pstage-prod



To execute Unit Tests from within Eclipse it is necessary to set stage-dev active:

> Project Properties -> Maven -> Active Maven Profiles: stage-dev

### Profile Configuration

The profile specific configuration can be found in the pom.xml profiles section. At the moment it is used to replace values in the `database.properties` and `applicationContext.xml` file.

Some security sensitive settings are read from additional .properties files which should **not be added to the SCM**.  
These files can be found in the `profiles/<profile-id>/` folders.  
The filename is always `config.properties`.

The following steps are needed, prior to the first stage-prod build, to add the stage-prod configuration:

 1. Copy the `config.properties.template` file and rename it to `config.properties`
 2. Fill in the database connection url and credentials