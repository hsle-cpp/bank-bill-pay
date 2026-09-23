# React Native Web feature frontend

Run `npm install` then `npm run web`. The app runs through Expo Web.

## Structure

- `src/modules/` contains one complete feature build per subsystem. Keep the
  feature's UI, state, API calls, tests, and public component exports together.
- `src/api/` contains generated transport types only; it is not a component
  library or a module boundary.

Modules may import one another directly using the normal TypeScript import
path, for example `@/modules/bill-management`. Export reusable components from
the owning module's `index.ts`/`index.tsx` and consume them as ordinary React
tags. There is no frontend library layer.

Run `npm run openapi:generate` after updating the backend OpenAPI document, then
commit the generated output with the feature that consumes it.
