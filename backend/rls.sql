-- HMC Smart Fabricator: restrictive starter RLS policies
-- Apply only after backend/schema.sql in a NEW Supabase project.
-- This is a safe starting point, not a complete production backend.
-- Never put the Supabase service-role key in browser code.

-- Public catalogue data may be read by app users; writes stay server/admin-only.
alter table public.products enable row level security;
alter table public.rates enable row level security;
alter table public.rewards enable row level security;
alter table public.qr_codes enable row level security;

drop policy if exists "Read active products" on public.products;
create policy "Read active products"
  on public.products for select to anon, authenticated
  using (active = true);

drop policy if exists "Read current rates" on public.rates;
create policy "Read current rates"
  on public.rates for select to anon, authenticated
  using (true);

drop policy if exists "Read active rewards" on public.rewards;
create policy "Read active rewards"
  on public.rewards for select to anon, authenticated
  using (active = true);

-- A logged-in fabricator can read only their own profile.
-- Profile creation and changes must go through a trusted server function
-- so users cannot make their own account active or edit protected fields.
drop policy if exists "Read own fabricator profile" on public.fabricators;
create policy "Read own fabricator profile"
  on public.fabricators for select to authenticated
  using (id = (select auth.uid()));

-- Orders and order items are read-only from the client for now.
-- Trusted server functions should validate prices/totals and create orders.
drop policy if exists "Read own orders" on public.orders;
create policy "Read own orders"
  on public.orders for select to authenticated
  using (fabricator_id = (select auth.uid()));

drop policy if exists "Read items from own orders" on public.order_items;
create policy "Read items from own orders"
  on public.order_items for select to authenticated
  using (
    exists (
      select 1
      from public.orders o
      where o.id = order_items.order_id
        and o.fabricator_id = (select auth.uid())
    )
  );

-- Loyalty balances/ledger are not writable by the browser.
drop policy if exists "Read own points ledger" on public.points_ledger;
create policy "Read own points ledger"
  on public.points_ledger for select to authenticated
  using (fabricator_id = (select auth.uid()));

drop policy if exists "Read own reward requests" on public.reward_redemptions;
create policy "Read own reward requests"
  on public.reward_redemptions for select to authenticated
  using (fabricator_id = (select auth.uid()));

-- QR codes intentionally have no client-facing policy.
-- QR verification, single-use marking, points earning, and redemptions
-- must be implemented atomically in trusted server-side functions.

-- Important: no client INSERT/UPDATE/DELETE policies are provided here.
-- Add narrowly scoped server functions before enabling signup, ordering,
-- QR scanning, or reward redemption in production.
