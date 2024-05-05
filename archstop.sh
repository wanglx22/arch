#!/bin/bash

original_wd=$PWD

# cd ./glassfish5/glassfish/domains/domain1/applications/__internal/archemy/
# tar czvf - archemy8875342697225254081.ear | split -b 40M - archive.tar.gz.
# cd "$original_wd"

# cd ./glassfish5/glassfish/domains/domain1/applications/archemy/
# tar czvf - Archemy_Project1_webapp.war | split -b 40M - archive.tar.gz.
# cd "$original_wd"

# mkdir ./mysql
# sudo cp -R -p /var/lib/mysql .

bash ./apache-tomcat/bin/shutdown.sh
./glassfish5/glassfish/bin/asadmin stop-domain domain1
bash ./apacheds-2.0.0.AM25/bin/apacheds.sh stop
sudo service mysql stop