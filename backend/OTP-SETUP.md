# HMC Smart Fabricator — real OTP backend setup

This branch prepares the HMC app for real passwordless mobile authentication while keeping the approved Home UI unchanged.

## Architecture

- GitHub Pages: hosts the existing HMC frontend.
- Supabase: free-tier database + Auth/session layer.
- SMS provider: delivers the OTP to the fabricator's Nepal mobile number.
- Hulas database: fabricator profiles, products, rates, orders, points, QR records and rewards.

Supabase's browser client uses a project URL and a publishable key. The publishable key is safe to place in the frontend; never place a service-role key in index.html.

## OTP flow

1. Fabricator enters mobile number.
2. HMC calls Supabase phone OTP.
3. Fabricator receives a 6-digit code.
4. HMC verifies the code.
5. Supabase creates/refreshes the authenticated session.
6. HMC loads the fabricator profile from public.fabricators.
7. Logout signs out the Supabase session.
8. The same mobile number can authenticate again with a new OTP.

## First-time registration

OTP authentication should not ask the user to create a password.

After the first successful OTP verification, HMC collects:
- Full name
- Firm name
- District
- Address
- PAN/VAT (optional)

The app then creates the user's row in public.fabricators using the authenticated user's auth.uid().

## Free development stage

The HMC code and Supabase database can be prepared on free tiers. Real SMS delivery is the part that normally needs an SMS provider account/credits.

For development, configure a test flow first. Do not build a fake OTP generator for production.

## Required Supabase settings

1. Create a project.
2. Enable Phone authentication.
3. Configure an SMS provider for production OTP delivery.
4. Add the GitHub Pages site URL to the allowed/auth redirect configuration where required.
5. Run backend/schema.sql.
6. Run backend/rls.sql.
7. Seed the official Hulas product, weight and rate data.
8. Create an admin role using protected app_metadata; never accept an admin role from the browser.

## Values needed by the frontend

Only these two non-secret values are needed in browser code:
- Supabase project URL
- Supabase publishable/anon key

Do NOT send or commit:
- service-role key
- database password
- SMS provider secret
- any private API credential

## Next implementation step

Once the Supabase project exists, replace the current localStorage password login with:
- mobile number screen
- Send OTP
- 6-digit OTP screen
- Verify OTP
- first-time profile setup
- persistent Supabase session
- real logout

The Home layout is intentionally not changed by this backend branch.
