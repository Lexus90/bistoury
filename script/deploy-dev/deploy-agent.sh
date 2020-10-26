#!/bin/bash

function deploy {
    module=$1
    rm -rf ${module} && mkdir ${module} && tar zxf bistoury-${module}-bin.tar.gz -C ${module}
}

function agent() {
  deploy agent
}

function proxy() {
  deploy proxy
}

function ui() {
  deploy ui
}

CMD="$1"

case ${CMD} in
agent)
    agent
    exit 0
    ;;
proxy)
    proxy
    exit 0
    ;;
ui)
    ui
    exit 0
    ;;
*)
    echo "Usage: $0 {agent|proxy|proxy}" >&2
    agent
    proxy
    ui
esac

