#!/bin/bash

original_wd=$PWD

cd ./glassfish5/glassfish/domains/domain1/applications/__internal/archemy/
cat archive.tar.gz.* | tar xzvf -
rm archive.tar.gz.*
cd "$original_wd"

cd ./glassfish5/glassfish/domains/domain1/applications/archemy/
cat archive.tar.gz.* | tar xzvf -
rm archive.tar.gz.*
cd "$original_wd"

sudo rm -rf /var/lib/mysql
sudo cp -R -p ./mysql /var/lib/
sudo chown -R mysql:mysql /var/lib/mysql

bash ./apache-tomcat/bin/startup.sh
./glassfish5/glassfish/bin/asadmin start-domain domain1
bash ./apacheds-2.0.0.AM25/bin/apacheds.sh start
sudo service mysql start

