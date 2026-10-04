-- Hulas Smart Fabricator — production RLS baseline
-- Apply after backend/schema.sql in Supabase SQL Editor.
-- This file does NOT expose service-role credentials.

create or replace function public.is_hulas_admin()
returns boolean
language sql
stable
as $$
  select coalesce((auth.jwt() -> 'app_metadata' ->> 'role') = 'admin', false);
$$;

drop policy if exists "fabricators_select_own" on public.fabricators;
create policy "fabricators_select_own" on public.fabricators for select to authenticated
using (id = auth.uid() or public.is_hulas_admin());

drop policy if exists "fabricators_insert_own" on public.fabricators;
create policy "fabricators_insert_own" on public.fabricators for insert to authenticated
with check (id = auth.uid());

drop policy if exists "fabricators_update_own" on public.fabricators;
create policy "fabricators_update_own" on public.fabricators for update to authenticated
using (id = auth.uid() or public.is_hulas_admin())
with check (id = auth.uid() or public.is_hulas_admin());

alter table public.products enable row level security;
alter table public.rates enable row level security;
alter table public.rewards enable row level security;
alter table public.qr_codes enable row level security;

drop policy if exists "products_read_active" on public.products;
create policy "products_read_active" on public.products for select to authenticated
using (active = true or public.is_hulas_admin());
drop policy if exists "products_admin_write" on public.products;
create policy "products_admin_write" on public.products for all to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "rates_read" on public.rates;
create policy "rates_read" on public.rates for select to authenticated using (true);
drop policy if exists "rates_admin_write" on public.rates;
create policy "rates_admin_write" on public.rates for all to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "rewards_read_active" on public.rewards;
create policy "rewards_read_active" on public.rewards for select to authenticated
using (active = true or public.is_hulas_admin());
drop policy if exists "rewards_admin_write" on public.rewards;
create policy "rewards_admin_write" on public.rewards for all to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "orders_select_own" on public.orders;
create policy "orders_select_own" on public.orders for select to authenticated
using (fabricator_id = auth.uid() or public.is_hulas_admin());
drop policy if exists "orders_insert_own" on public.orders;
create policy "orders_insert_own" on public.orders for insert to authenticated
with check (fabricator_id = auth.uid());
drop policy if exists "orders_admin_update" on public.orders;
create policy "orders_admin_update" on public.orders for update to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "order_items_select_own" on public.order_items;
create policy "order_items_select_own" on public.order_items for select to authenticated
using (exists (select 1 from public.orders o where o.id = order_items.order_id and (o.fabricator_id = auth.uid() or public.is_hulas_admin())));
drop policy if exists "order_items_insert_own" on public.order_items;
create policy "order_items_insert_own" on public.order_items for insert to authenticated
with check (exists (select 1 from public.orders o where o.id = order_items.order_id and o.fabricator_id = auth.uid()));
drop policy if exists "order_items_admin_write" on public.order_items;
create policy "order_items_admin_write" on public.order_items for update to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "points_select_own" on public.points_ledger;
create policy "points_select_own" on public.points_ledger for select to authenticated
using (fabricator_id = auth.uid() or public.is_hulas_admin());
drop policy if exists "points_admin_insert" on public.points_ledger;
create policy "points_admin_insert" on public.points_ledger for insert to authenticated
with check (public.is_hulas_admin());
drop policy if exists "points_admin_update" on public.points_ledger;
create policy "points_admin_update" on public.points_ledger for update to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "qr_admin_all" on public.qr_codes;
create policy "qr_admin_all" on public.qr_codes for all to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

drop policy if exists "redemptions_select_own" on public.reward_redemptions;
create policy "redemptions_select_own" on public.reward_redemptions for select to authenticated
using (fabricator_id = auth.uid() or public.is_hulas_admin());
drop policy if exists "redemptions_admin_all" on public.reward_redemptions;
create policy "redemptions_admin_all" on public.reward_redemptions for all to authenticated
using (public.is_hulas_admin()) with check (public.is_hulas_admin());

revoke all on function public.is_hulas_admin() from anon;
grant execute on function public.is_hulas_admin() to authenticated;