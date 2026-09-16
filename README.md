# Personal Banking & Bill Pay Management

## Project overview

Personal Banking & Bill Pay Management is a single place for a user to understand recurring expenses, reserve money for upcoming bills, and manage bill payments. It supports checking and savings-style account activity, links to a third-party financial institution, and user-provided bank statements.

The project should help a user answer three practical questions:

1. What bills and recurring expenses do I have?
2. Do I have enough money allocated for each upcoming bill?
3. What should I pay next, and when?

## Problem being solved

Bills often come from many vendors, use different due dates, and are paid from different accounts. For example, one user may manage a mortgage, utilities, insurance policies, subscriptions, and several credit cards. This makes it difficult to see the full cost of living or avoid missed payments.

The application centralizes those obligations and gives the user a clearer view of income, spending, upcoming payments, and available funds.

## Product scope

The first version should support the following capabilities:

- Create and view checking and savings-style accounts.
- Record deposits and withdrawals.
- Link a third-party financial institution or upload a bank statement.
- Import statement activity and identify possible recurring payments.
- Let a user confirm, edit, add, or remove recurring bill items.
- Track vendors, amounts, due dates, payment schedules, and payment status.
- Allocate available funds toward upcoming bills.
- Show upcoming obligations, account balances, and spending visibility in one place.
- Schedule and record bill-payment actions.

## Architecture direction

The frontend is organized as small reusable libraries. The backend is organized as independently owned modules that package as JARs; the main application assembles those JARs into one deployable WAR.

## Proposed repository hierarchy

This is a planning hierarchy only. It defines where future work belongs; 

```text
bank-bill-pay/
├── README.md
├── discovery/
│   ├── product-scope.md
│   ├── user-stories.md
│   ├── domain-glossary.md
│   ├── api-contracts.md
│   ├── data-model.md
│   ├── acceptance-criteria.md
│   └── team-ownership.md
├── frontend/
│   ├── apps/
│   │   └── mobile-app/
│   │       ├── app-shell/
│   │       ├── navigation/
│   │       └── app-integration/
│   ├── libraries/
│   │   ├── ui-kit/
│   │   │   ├── components/
│   │   │   ├── tokens/
│   │   │   └── styles/
│   │   ├── accounts-feature/
│   │   │   ├── screens/
│   │   │   ├── components/
│   │   │   ├── state/
│   │   │   └── api/
│   │   ├── bills-feature/
│   │   │   ├── screens/
│   │   │   ├── components/
│   │   │   ├── state/
│   │   │   └── api/
│   │   └── shared-types/
│   └── tests/
│       ├── ui-kit/
│       ├── accounts-feature/
│       └── bills-feature/
├── backend/
│   ├── modules/
│   │   ├── accounts-module/
│   │   │   ├── api/
│   │   │   ├── application/
│   │   │   ├── domain/
│   │   │   ├── infrastructure/
│   │   │   └── tests/
│   │   ├── bills-payments-module/
│   │   │   ├── api/
│   │   │   ├── application/
│   │   │   ├── domain/
│   │   │   ├── infrastructure/
│   │   │   └── tests/
│   │   ├── statement-ingestion-module/
│   │   │   ├── api/
│   │   │   ├── application/
│   │   │   ├── domain/
│   │   │   ├── infrastructure/
│   │   │   └── tests/
│   │   └── persistence-module/
│   │       ├── database-access/
│   │       ├── migrations-support/
│   │       ├── integration/
│   │       └── tests/
│   ├── contracts/
│   │   ├── api/
│   │   ├── events/
│   │   └── shared-models/
│   ├── main-war/
│   │   ├── assembly/
│   │   ├── configuration/
│   │   └── integration-tests/
│   └── architecture/
│       ├── module-boundaries.md
│       └── dependency-rules.md
└── database/
    ├── migrations/
    │   ├── accounts/
    │   ├── bills-payments/
    │   └── statements/
    ├── reference-data/
    ├── schema/
    │   ├── logical-model.md
    │   └── physical-model.md
    └── database-change-log.md
```

### Structure rules

- `discovery/` contains the shared decisions that developers need before implementing a feature. It is the agreed source for terms, user behavior, API expectations, and data definitions.
- `frontend/libraries/` contains small, independently owned feature libraries. A feature library must not import another feature library directly; use `shared-types/` or an approved backend contract instead.
- Each folder in `backend/modules/` produces one JAR. A module contains its own API, business rules, infrastructure adapter, and tests.
- `backend/main-war/` contains only application composition, deployment configuration, and end-to-end integration work. It consumes the module JARs and produces the deployable WAR. Business logic does not belong here.
- `backend/contracts/` is the deliberate boundary between frontend and backend, and between backend modules. It holds versioned request/response shapes, event definitions, and shared models.
- `database/migrations/` is append-only. A released migration must never be edited or renamed; corrections are new migrations.