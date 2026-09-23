# Bill Pay Application

A small React Native Web, Spring Boot, and PostgreSQL bill payment application. Work is split by
subsystem so each developer can work independently while still reusing another
subsystem's implementation when that is the simplest design. See
[WORK_OWNERSHIP.md](WORK_OWNERSHIP.md) before changing a module or schema used by
another developer.

## Structure

- `backend/modules/` and `frontend/src/modules/` — one folder per subsystem.
- `backend/modules/` — independently owned Spring feature modules. A module may
  depend on and import another module's implementation through a normal Maven
  dependency.
- `backend/application-war/` — composes the backend modules into one deployable app.
- `frontend/src/modules/` — self-contained feature builds. A module's public
  components are imported directly by another feature or by `App.tsx`.
- `frontend/src/api/` — generated transport types, when a feature needs them;
  this is not a frontend component library.
- `database/` — migrations and schema catalog shared by the modules.

Each subsystem owns the matching folder in `frontend/src/modules/` and
`backend/modules/`. Modules may import another module's public implementation
when it is useful. Keep the dependency graph intentional and acyclic; do not
create a parallel copy merely to avoid a direct dependency.

Backend modules are regular Spring JARs. Add a dependency on the module that
owns the implementation, then inject or import its public classes. The
deployable application composes all feature JARs in `backend/application-war/`.

Frontend modules are complete feature builds: UI, state, API calls, tests, and
their public component entry points live together. If Developer 1 needs a
component built by Developer 2, Developer 1 imports it from Developer 2's
module and renders its JSX tag directly. There is no frontend component
library layer.

## Subsystem requirements

These are the intended requirements for the application. Implement only the basic flow
first; stretch items are optional.

### Account overview

- Basic login and user profile.
- Simulated checking account and wallet funding account.
- Account balance display.

### Statement and transactions

- CSV statement upload and validation.
- Duplicate import detection.
- Transaction storage, search, filtering, and details.
- Optional: PDF parsing, live bank connections, and automatic synchronization.

### Bill discovery

- Detect repeated transactions.
- Estimate recurring amount and billing cycle.
- Show possible bills for confirmation, editing, or dismissal.
- Allow manual bill entry.
- Optional: separate subscription management, advanced merchant classification,
  and machine-learning detection.

### Bill management

- Confirmed bill records with name, vendor, amount, due date, and frequency.
- Upcoming bills with paid and overdue status.
- Edit and delete bills.
- Basic bill totals.

### Wallet and funding

- Virtual wallet balance.
- Add virtual funds from the simulated checking account.
- Funding history and reserved funds for upcoming bills.
- Low-balance warning.
- Optional: automatic funding rules, multiple funding sources, and safety buffers.

### Payment operations

- Create a payment for a confirmed bill.
- Basic approval, payment queue, status, and history.
- Simulate successful and failed payments.
- Prevent duplicate payment attempts.
- Optional: real biller payments, retries, and production notifications.

### Financial advisor

- Upcoming-bill total.
- Wallet balance after planned payments.
- Basic projected balance and low-balance warning.
- Simple due-date priority order.
- Explain each warning.

## Quick start

1. Start PostgreSQL: `docker compose up -d postgres`
2. Build and test the backend: `cd backend && mvn verify`
3. Run the web app: `cd frontend && npm install && npm run web`

The app currently provides a shell with placeholder feature modules. Add
subsystem behavior inside its owning module and expose reusable pieces from
that module's entry point.
