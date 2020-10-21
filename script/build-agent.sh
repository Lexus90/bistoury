#!/bin/bash

cd "${0%/*}"
cd ..

PROFILR='prod'

#打包agent
echo "================ starting to build bistoury agent ================"
./mvnw clean package -am -pl bistoury-dist -P$PROFILR -Dmaven.test.skip -Denforcer.skip=true
echo "================ building bistoury agent finished ================"
