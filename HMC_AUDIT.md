# HMC Smart Fabricator — Audit and Repair Plan

## Scope
This branch is for a safe audit of the existing single-file PWA. Keep the current home-page design and do not merge changes into `main` until tested.

## Findings verified from `index.html`
- Login and registration store accounts in IndexedDB and/or localStorage. This is device/browser-local storage, not shared authentication; a fabricator registered on one device cannot automatically sign in on another.
- Passwords are hashed in the browser, but this local prototype does not provide server-side account recovery or production-grade account administration.
- QR scanning accepts a client-side payload shaped like `HULAS|PIPE|<id>|<kg>`; duplicate detection is also stored locally. A user can potentially construct payloads or reset local storage, so this must not be used to award redeemable rewards without server verification.
- The code itself has one static `.home-header` markup instance. If repeated headers appear in the deployed app, compare the live version/cache with this branch before changing header rendering.
- The repository's `backend/README.md` describes a future Supabase Auth/Postgres/RLS integration and explicitly notes that the current UI remains a browser-only prototype.

## Repair sequence
1. Add automated/manual regression checks for registration, login, logout, refresh, browser storage disabled, wrong password, and repeated taps.
2. Provision a Hulas-controlled Supabase project; do not put service-role keys in browser code.
3. Implement Supabase Auth and restrictive Row Level Security policies before claiming cross-device login works.
4. Implement server-side QR verification, one-time scan consumption, points ledger entries, order creation, and reward redemption. Never trust client-supplied points or weight.
5. Verify pipe-weight data, grade pricing, and VAT calculations against the approved Hulas chart.
6. Test camera scanning on HTTPS, photo QR scanning, duplicate codes, order quantities, WhatsApp payloads, mobile layout, and PWA updates.
7. Merge only after review and a successful test pass.

## Current status
This document records the source audit. It does not claim the app has been fixed or that any runtime tests have passed. Backend work requires Hulas-controlled credentials/configuration and server-side implementation.
