#!/bin/expect
spawn scp -P 56358 bistoury-proxy/target/bistoury-proxy-bin.tar.gz root@192.168.106.82:/home/data/webroot/bistoury
expect "password:"
set timeout 30
send "pJui#c!7\r"
interact

