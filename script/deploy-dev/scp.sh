#!/bin/bash

function agent() {
  expect script/deploy-dev/scp-agent.sh
}

function proxy() {
  expect script/deploy-dev/scp-proxy.sh
}

function ui() {
  expect script/deploy-dev/scp-ui.sh
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


