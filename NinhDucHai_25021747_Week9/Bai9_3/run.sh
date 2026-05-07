#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "${BASH_SOURCE[0]}")"
ACTION="${1:-package}"
case "$ACTION" in
  test)    mvn clean test -B ;;
  package) mvn clean package -B ;;
  clean)   mvn clean ;;
  *) echo "Usage: ./run.sh [test|package|clean]"; exit 1 ;;
esac
