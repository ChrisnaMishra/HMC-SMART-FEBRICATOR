# Hulas Smart Fabricator V2 — Backend Foundation

This folder defines the production data foundation for the V2 app.

## Target stack
- Supabase Auth for fabricator authentication
- PostgreSQL for application data
- Row Level Security (RLS) for access control
- Server-side functions for QR verification, points, orders and redemptions

## Current status
The live UI is still a browser-only prototype. This foundation does **not** switch the app to backend authentication by itself.

## Required migration sequence
1. Create a Supabase project owned/controlled by Hulas.
2. Run `schema.sql`.
3. Add restrictive RLS policies for fabricators and admins.
4. Add server-side functions for:
   - registration/profile creation
   - QR verification and one-time use
   - points ledger entries
   - order creation/status changes
   - reward redemption
5. Move the current hard-coded rates, products and rewards into the database.
6. Replace localStorage account/points logic in `index.html` with the API client.
7. Test calculations and security before merging V2 into `main`.

## Security rules
- Never store plaintext passwords.
- Never trust points, order totals or QR payloads sent by the browser.
- Never expose a service-role key in frontend JavaScript.
- Keep admin operations server-side.
