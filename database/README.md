# Shared PostgreSQL schema

The database is intentionally empty while the application is being initialized.
Subsystem owners add migrations and catalog entries only when implementation
starts.

## Change process

1. Check whether another subsystem already owns the data.
2. Get lead and affected-owner approval for new tables or shared columns.
3. Add a timestamped migration named `V<UTC timestamp>__<subsystem>__description.sql`.
4. Add or update the matching file in `catalog/` in the same change.

Never alter an applied migration. Add a corrective migration instead.
