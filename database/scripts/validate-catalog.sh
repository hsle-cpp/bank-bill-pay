#!/usr/bin/env bash
set -euo pipefail

root="$(cd "$(dirname "$0")/../.." && pwd)"
shopt -s nullglob
for migration in "$root"/database/migrations/V*.sql; do
  catalog=$(sed -n 's/^-- CATALOG ENTRY: //p' "$migration")
  test -n "$catalog" || { echo "Missing CATALOG ENTRY header: $migration"; exit 1; }
  test -f "$root/$catalog" || { echo "Catalog file not found: $catalog"; exit 1; }
done
echo "Migration catalog references are valid."
