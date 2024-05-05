#!/bin/bash

original_wd=$PWD

sudo apt update
sudo apt install openjdk-8-jdk

sudo apt install mysql-client-core-8.0
sudo apt-get install mysql-server-8.0

sudo service mysql stop
sudo rm -rf /var/lib/mysql
sudo cp -R -p ./mysql /var/lib/
sudo chown -R mysql:mysql /var/lib/mysql
sudo service mysql start

cd ./glassfish5/glassfish/domains/domain1/applications/__internal/archemy/
cat archive.tar.gz.* | tar xzvf -
rm archive.tar.gz.*
cd "$original_wd"

cd ./glassfish5/glassfish/domains/domain1/applications/archemy/
cat archive.tar.gz.* | tar xzvf -
rm archive.tar.gz.*
cd "$original_wd"

bash ./apacheds-2.0.0.AM25/bin/apacheds.sh start
bash ./apache-tomcat/bin/startup.sh
./glassfish5/glassfish/bin/asadmin start-domain domain1
