# HMC Smart Fabricator — Supabase setup

This guide is for the project owner or an authorized Hulas Metal Crafts Ltd. administrator.

## Important
- Create the Supabase project under an account controlled by the business/project owner.
- Do not commit database passwords, access tokens, or Supabase service-role keys.
- Never put a service-role key in `index.html` or any browser-delivered JavaScript.
- Keep the production app unchanged until the backend has been tested.

## Initial setup
1. Create a Supabase project in the Supabase dashboard.
2. Open **SQL Editor**.
3. Review and run `backend/schema.sql` in the new project.
4. Review and run `backend/rls.sql` in the same project.
5. In **Authentication → URL Configuration**, set the correct app/site URL and allowed redirect URLs for the actual deployment. Do not guess production URLs.
6. Enable the chosen sign-in method and configure email/mobile verification appropriate to the business.

## Current policy boundaries
- App users can read active product and reward catalogue data and current rates.
- Authenticated users can read only their own profile, orders, order items, points ledger, and reward requests.
- Browser clients cannot directly create or modify orders, profiles, loyalty points, QR status, or reward redemptions.
- Those operations require trusted server-side functions that validate the authenticated user and perform database transactions.

## Before connecting the app
The existing `index.html` uses browser-local account data; it does not become cloud authentication just because a Supabase project exists. The app needs a reviewed integration that replaces local login/register flows and uses the Supabase Auth client.

Use only the project's **publishable/anon key** in browser code. Never use the service-role key in the app.

## Required server-side functions before production
- Fabricator registration/profile creation with pending status and protected fields.
- Order creation with server-calculated product weights, rates, VAT, discounts, and totals.
- QR verification with atomic single-use marking and one-time ledger credit.
- Reward redemption with atomic balance/stock checks.
- Admin-only activation, order status changes, and point adjustments.

## Test plan
1. Register a test user and verify the account requires the expected verification/approval.
2. Sign in and confirm the profile is only visible to that user.
3. Create two test users and verify neither can read the other's profile, orders, or points.
4. Confirm the browser cannot directly insert points or mark a QR code used.
5. Verify order totals are recalculated server-side.
6. Test QR replay and simultaneous scans; only one should award points.
7. Test reward redemption when balance or stock is insufficient.
8. Check phone layout, camera permission, HTTPS, and sign-out/session expiry.

Do not launch the cloud-connected version until these tests pass.
